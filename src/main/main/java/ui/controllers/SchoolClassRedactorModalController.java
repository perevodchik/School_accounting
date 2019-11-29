package ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import data.dao.SchoolClassDao;
import data.entity.SchoolClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SchoolClassRedactorModalController {
    @FXML
    public JFXTextField classNameField;
    @FXML
    public AnchorPane modalPane;
    @FXML
    public JFXButton btnCancel;
    @FXML
    public JFXButton btnApply;

    private SchoolClass schoolClass;

    public void initData(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
        classNameField.setText(schoolClass.getName());
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        ((Stage) modalPane.getScene().getWindow()).close();
    }

    @FXML
    public void apply(ActionEvent actionEvent) {
        SchoolClassDao schoolClassDao = new SchoolClassDao();
        schoolClass.setName(classNameField.getText());
        schoolClassDao.update(schoolClass);
        ((Stage) modalPane.getScene().getWindow()).close();
    }
}
