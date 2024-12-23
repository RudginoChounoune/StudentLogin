package FinalProject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Login extends Application {
	private TextField tfSSN = new TextField();
	private Label lblErrorMessage = new Label("Error: Student not found");
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;

		lblErrorMessage.setVisible(false);

		tfSSN.setPromptText("Enter SSN");
		Button btnLogin = new Button("Login");
		btnLogin.setOnAction(e -> handleLogin());
		Button btnExit = new Button("Exit");
		btnExit.setOnAction(e -> primaryStage.close());

		HBox hBoxSSN = new HBox(10, new Label("SSN:"), tfSSN);
		hBoxSSN.setAlignment(Pos.CENTER);
		HBox hBoxButtons = new HBox(115, btnExit, btnLogin);
		hBoxButtons.setAlignment(Pos.CENTER);

		VBox vBox = new VBox(10, lblErrorMessage, hBoxSSN, hBoxButtons);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(20));

		Scene scene = new Scene(vBox, 300, 150);
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void handleLogin() {
		String ssn = tfSSN.getText();
		if (ssn.isEmpty()) {
			lblErrorMessage.setVisible(true);
			return;
		}
		try {
			if (DBUtil.validateStudentSSN(ssn)) {
				lblErrorMessage.setVisible(false);
				MyClasses myClasses = new MyClasses(ssn, new Stage());
				myClasses.start();
			} else {
				lblErrorMessage.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			lblErrorMessage.setText("Database Error: " + e.getMessage());
			lblErrorMessage.setVisible(true);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
