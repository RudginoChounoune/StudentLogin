package FinalProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://database-cuny.c4piq2ndsfvh.us-west-1.rds.amazonaws.com:3306/CUNY_DB";
	private static final String USER = "cst3613";
	private static final String PASS = "password1";

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}

	public static boolean validateStudentSSN(String ssn) throws SQLException, ClassNotFoundException {
		String sql = "SELECT COUNT(*) AS count FROM Students WHERE ssn = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, ssn);
			ResultSet rs = pstmt.executeQuery();
			return rs.next() && rs.getInt("count") > 0;
		}
	}

	public static String getStudentName(String ssn) throws SQLException, ClassNotFoundException {
		String sql = "SELECT firstName, lastName FROM Students WHERE ssn = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, ssn);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("firstName") + " " + rs.getString("lastName");
			}
		}
		return "Student";
	}

	public static List<String> getStudentClassesWithGrades(String ssn) throws SQLException, ClassNotFoundException {
		List<String> classDetails = new ArrayList<>();
		String sql = "SELECT Course.courseId, Course.title, Enrollment.grade "
				+ "FROM Enrollment INNER JOIN Course ON Enrollment.courseId = Course.courseId "
				+ "WHERE Enrollment.ssn = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, ssn);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String detail = rs.getString("courseId") + "\t" + rs.getString("title") + "\t" + rs.getString("grade");
				classDetails.add(detail);
			}
		}
		return classDetails;
	}

	public static boolean unregisterClass(String studentSSN, String courseId)
			throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM Enrollment WHERE ssn = ? AND courseId = ?";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, studentSSN);
			pstmt.setString(2, courseId);
			int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;
		}
	}

	public static List<String> getAvailableClasses(String studentSSN) throws SQLException, ClassNotFoundException {
		List<String> availableClasses = new ArrayList<>();
		String sql = "SELECT courseId, title FROM Course WHERE courseId NOT IN (SELECT courseId FROM Enrollment WHERE ssn = ?)";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, studentSSN);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					String classInfo = rs.getString("courseId") + " - " + rs.getString("title");
					availableClasses.add(classInfo);
				}
			}
		}
		return availableClasses;
	}

	public static boolean registerClassForStudent(String studentSSN, String courseId)
			throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO Enrollment (ssn, courseId, dateRegistered) VALUES (?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, studentSSN);
			pstmt.setString(2, courseId);
			pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis())); // Set the current date
			int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;
		}
	}

}
