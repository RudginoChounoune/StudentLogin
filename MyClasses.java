package FinalProject;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClasses {
    private String studentSSN;
    private Stage primaryStage;
    private TableView<Map<String, String>> table;
    public MyClasses(String ssn, Stage primaryStage) {
        this.studentSSN = ssn;
        this.primaryStage = primaryStage;
        this.table = new TableView<>();
    }

    public void start() {
        primaryStage.setTitle("My Classes");

        Label lblWelcome = new Label();
        configureTable(table);

        try {
            String studentName = DBUtil.getStudentName(studentSSN);
            lblWelcome.setText("Welcome, " + studentName);
            List<String> classDetails = DBUtil.getStudentClassesWithGrades(studentSSN);
            table.setItems(convertToObservableList(classDetails));
        } catch (Exception e) {
            lblWelcome.setText("Error loading classes: " + e.getMessage());
        }

        ScrollPane scrollPane = new ScrollPane(table);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(200);

        Menu manageMenu = new Menu("Manage Class");
        MenuItem addMenuItem = new MenuItem("Add");
        addMenuItem.setOnAction(e -> openRegisterWindow());
        MenuItem removeMenuItem = new MenuItem("Remove");
        removeMenuItem.setOnAction(e -> removeSelectedClass(table));
        manageMenu.getItems().addAll(addMenuItem, removeMenuItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(manageMenu);

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> primaryStage.close());

        BorderPane topPane = new BorderPane();
        topPane.setLeft(menuBar);
        topPane.setRight(btnExit);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> openRegisterWindow());

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> removeSelectedClass(table));

        HBox buttonBox = new HBox(10, addButton, removeButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox centerVBox = new VBox(10, lblWelcome, scrollPane, buttonBox);
        centerVBox.setPadding(new Insets(10));
        centerVBox.setAlignment(Pos.CENTER);

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(topPane);
        mainPane.setCenter(centerVBox);

        Scene scene = new Scene(mainPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openRegisterWindow() {
        Register registerWindow = new Register(studentSSN, this);
        registerWindow.showStage();
    }
    private void removeSelectedClass(TableView<Map<String, String>> table) {
        Map<String, String> selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            confirmAndRemoveClass(selected, table);
        }
    }
    public void reloadClasses() {
        try {
            List<String> classDetails = DBUtil.getStudentClassesWithGrades(studentSSN);
            table.setItems(convertToObservableList(classDetails));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configureTable(TableView<Map<String, String>> table) {
        TableColumn<Map<String, String>, String> courseIdColumn = new TableColumn<>("Course ID");
        courseIdColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().get("courseId")));
        courseIdColumn.setSortable(false);

        TableColumn<Map<String, String>, String> courseNameColumn = new TableColumn<>("Course Name");
        courseNameColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().get("courseName")));
        courseNameColumn.setSortable(false);

        TableColumn<Map<String, String>, String> gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().get("grade")));
        gradeColumn.setSortable(false);

        table.getColumns().addAll(courseIdColumn, courseNameColumn, gradeColumn);
    }

    private ObservableList<Map<String, String>> convertToObservableList(List<String> classDetails) {
        ObservableList<Map<String, String>> list = FXCollections.observableArrayList();
        for (String detail : classDetails) {
            String[] parts = detail.split("\t");
            Map<String, String> map = new HashMap<>();
            map.put("courseId", parts[0]);
            map.put("courseName", parts[1]);
            map.put("grade", parts[2]);
            list.add(map);
        }
        return list;
    }

    private void confirmAndRemoveClass(Map<String, String> selected, TableView<Map<String, String>> table) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this class?", ButtonType.YES, ButtonType.NO);
        confirmAlert.setTitle("Confirm Removal");
        confirmAlert.setHeaderText(null);
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    if (DBUtil.unregisterClass(studentSSN, selected.get("courseId"))) {
                        table.getItems().remove(selected);
                        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                        infoAlert.setTitle("Removal Successful");
                        infoAlert.setHeaderText(null);
                        infoAlert.setContentText("The class has been successfully removed.");
                        infoAlert.showAndWait();
                    }
                } catch (Exception e) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("Failed to remove class");
                    errorAlert.setContentText(e.getMessage());
                    errorAlert.showAndWait();
                }
            }
        });
    }
}