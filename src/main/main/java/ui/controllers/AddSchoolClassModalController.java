package ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import data.dao.SchoolClassDao;
import data.entity.SchoolClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddSchoolClassModalController {
    @FXML
    public JFXButton btnCancel;
    @FXML
    public JFXButton btnApply;
    @FXML
    public JFXTextField classNameField;
    @FXML
    public AnchorPane modalPane;

    @FXML
    public void buttonCliCkHandler(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnApply) {
            SchoolClassDao schoolClassDao = new SchoolClassDao();
            SchoolClass schoolClass = new SchoolClass();
            schoolClass.setName(classNameField.getText());
            schoolClassDao.save(schoolClass);
            SchoolClassController.INSTANCE.setTable();
        }
        ((Stage) modalPane.getScene().getWindow()).close();
    }
}
