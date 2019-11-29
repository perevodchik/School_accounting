package ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import data.dao.DisciplineDao;
import data.entity.Discipline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChangeDisciplineModalController {
    @FXML
    public JFXButton changeBtn;
    @FXML
    public JFXButton cancelBtn;
    @FXML
    public JFXTextField disciplineNameField;
    @FXML
    public AnchorPane changeDisciplineModalPane;

    private Discipline discipline;

    public void initData(Discipline discipline) {
        this.discipline = discipline;
        this.disciplineNameField.setText(discipline.getName());
    }

    public void buttonClickHandler(ActionEvent actionEvent) {
        if(actionEvent.getSource() == changeBtn && !disciplineNameField.getText().isEmpty() && !disciplineNameField.getText().equals(discipline.getName())) {
            discipline.setName(disciplineNameField.getText());
            DisciplineDao disciplineDao = new DisciplineDao();
            disciplineDao.update(discipline);
            DisciplineController.getInstance().setList();
        }
        ((Stage) changeDisciplineModalPane.getScene().getWindow()).close();
    }
}
