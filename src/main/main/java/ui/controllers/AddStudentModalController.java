package ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import data.dao.SchoolClassDao;
import data.dao.StudentDao;
import data.entity.SchoolClass;
import data.entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class AddStudentModalController {
    @FXML
    public AnchorPane addStudentModalPane;
    @FXML
    public JFXButton addStudentBtn;
    @FXML
    public JFXButton cancelBtn;
    @FXML
    public JFXComboBox<SchoolClass> schoolClassComboBox;
    @FXML
    public JFXTextField nameValueField;
    @FXML
    public JFXTextField famValueField;
    @FXML
    public DatePicker birthday;
    @FXML
    public JFXTextField otchValueField;

    @FXML
    protected void initialize() {

        SchoolClassDao schoolClassDao = new SchoolClassDao() ;
        ObservableList<SchoolClass> values = FXCollections.observableArrayList();
        values.addAll(schoolClassDao.getAll());
        schoolClassComboBox.setItems(values);
    }

    public void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {

        if(mouseEvent.getSource() == addStudentBtn && validate()) {

            StudentDao studentDao = new StudentDao();
            Student student = new Student();

            student.setName(nameValueField.getText());
            student.setFam(famValueField.getText());
            student.setOtch(otchValueField.getText());
            student.setClass(schoolClassComboBox.getSelectionModel().getSelectedIndex() != -1 ?
                    schoolClassComboBox.getSelectionModel().getSelectedItem() :
                    StudentsController.INSTANCE.schoolClassList.getSelectionModel().getSelectedItem());

            LocalDate localDate = birthday.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            java.util.Date date = java.util.Date.from(instant);
            Timestamp birthdayDate = new Timestamp(date.getTime());
            student.setNarDate(birthdayDate);

            studentDao.save(student);

            if(schoolClassComboBox.getSelectionModel().getSelectedIndex() != -1) StudentsController.INSTANCE.schoolClassList.getSelectionModel().select(schoolClassComboBox.getSelectionModel().getSelectedIndex());

            ((Stage) addStudentModalPane.getScene().getWindow()).close();
        } else if(mouseEvent.getSource() == cancelBtn) {

            ((Stage) addStudentModalPane.getScene().getWindow()).close();
        }
    }

    private boolean validate() {
        return !nameValueField.getText().isEmpty()
                && !famValueField.getText().isEmpty()
                && !otchValueField.getText().isEmpty()
                && (StudentsController.INSTANCE.schoolClassList.getSelectionModel().getSelectedIndex() != -1 || schoolClassComboBox.getSelectionModel().getSelectedIndex() != -1)
                && birthday.getValue() != null;
    }
}
