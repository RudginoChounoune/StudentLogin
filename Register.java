package FinalProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Register {
	private ComboBox<String> cbClasses;
	private String studentSSN;
	private MyClasses myClassesRef;
	private Stage primaryStage;
	private ObservableList<String> allClasses;

	public Register(String ssn, MyClasses myClassesRef) {
		this.studentSSN = ssn;
		this.myClassesRef = myClassesRef;
		this.primaryStage = new Stage();
		start();
	}

	private void start() {
		primaryStage.setTitle("Register for Classes");

		VBox mainLayout = new VBox(10);
		mainLayout.setPadding(new Insets(15));
		mainLayout.setAlignment(Pos.CENTER);

		Label lblHeader = new Label("Select a class to register:");
		cbClasses = new ComboBox<>();
		cbClasses.setEditable(true);
		cbClasses.setPromptText("Available Classes");
		loadAvailableClasses();

		cbClasses.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.isEmpty()) {
				ObservableList<String> filtered = allClasses
						.filtered(item -> item.toLowerCase().contains(newValue.toLowerCase()));
				cbClasses.setItems(filtered);
			} else {
				cbClasses.setItems(allClasses);
			}
		});

		Button btnRegister = new Button("Register");
		btnRegister.setOnAction(e -> registerClass());

		Button btnCancel = new Button("Cancel");
		btnCancel.setOnAction(e -> primaryStage.close());

		HBox buttonLayout = new HBox(10, btnRegister, btnCancel);
		buttonLayout.setAlignment(Pos.CENTER);

		mainLayout.getChildren().addAll(lblHeader, cbClasses, buttonLayout);

		Scene scene = new Scene(mainLayout, 300, 200);
		primaryStage.setScene(scene);
	}

	private void loadAvailableClasses() {
		try {
			allClasses = FXCollections.observableArrayList(DBUtil.getAvailableClasses(studentSSN));
			cbClasses.setItems(allClasses);
		} catch (SQLException | ClassNotFoundException ex) {
			showAlert("Database Error", "Failed to load available classes: " + ex.getMessage());
		}
	}

	private void registerClass() {
		String selectedClass = cbClasses.getSelectionModel().getSelectedItem();
		if (selectedClass == null || selectedClass.isEmpty()) {
			showAlert("Selection Error", "Please select a class to register.");
			return;
		}

		String courseId = selectedClass.split(" - ")[0];

		try {
			if (DBUtil.registerClassForStudent(studentSSN, courseId)) {
				showAlert("Registration Successful", "You have successfully registered for the class.");
				primaryStage.close();
				myClassesRef.reloadClasses();
			} else {
				showAlert("Registration Failed", "Could not register for the class. Please try again.");
			}
		} catch (SQLException | ClassNotFoundException ex) {
			showAlert("Database Error", "Error registering for class: " + ex.getMessage());
		}
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public void showStage() {
		primaryStage.showAndWait();
	}
}
