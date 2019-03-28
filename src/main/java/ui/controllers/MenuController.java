package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {
    private static AnchorPane parentPane = null;

    @FXML
    public Button btnClasses;
    @FXML
    public Button btnSchedule;
    @FXML
    public Button btnSettings;
    @FXML
    public Button btnTimetable;
    @FXML
    public Button btnStudents;
    @FXML
    public Button btnZhurnal;

    static void setParentPane(Pane _parentPane) {
        parentPane = (AnchorPane) _parentPane;
    }

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnClasses) {
            //loadUI("classes");
        } else if (mouseEvent.getSource() == btnSchedule) {
            loadUI("schedule");
        } else if (mouseEvent.getSource() == btnSettings) {
            //loadUI("settings");
        } else if (mouseEvent.getSource() == btnTimetable) {
            loadUI("timetable");
        } else if (mouseEvent.getSource() == btnStudents) {
            loadUI("students");
        } else if (mouseEvent.getSource() == btnZhurnal) {
            loadUI("zhurnal");
        }
    }

    private void loadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/" + ui + ".fxml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        parentPane.getChildren().set(1, root);
        AnchorPane.setTopAnchor(root, 170.0);
        AnchorPane.setLeftAnchor(root, 150.0);
        AnchorPane.setRightAnchor(root, 150.0);
        AnchorPane.setBottomAnchor(root, 5.0);
    }
}
