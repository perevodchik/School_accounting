package ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import data.dao.DisciplineDao;
import data.entity.Discipline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddDisciplineModalController {
    @FXML
    public JFXButton addBtn;
    @FXML
    public JFXButton cancelBtn;
    @FXML
    public JFXTextField disciplineNameField;
    @FXML
    public AnchorPane addDisciplineModalPane;

    public void buttonClickHandler(ActionEvent actionEvent) {
        if(actionEvent.getSource() == addBtn && !disciplineNameField.getText().isEmpty()) {
            Discipline discipline = new Discipline();
            discipline.setName(disciplineNameField.getText());
            DisciplineDao disciplineDao = new DisciplineDao();
            disciplineDao.save(discipline);
            DisciplineController.getInstance().setList();
        }
        ((Stage) addDisciplineModalPane.getScene().getWindow()).close();
    }
}
