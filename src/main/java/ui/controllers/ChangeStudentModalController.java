package ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import data.dao.SchoolClassDao;
import data.dao.StudentDao;
import data.entity.SchoolClass;
import data.entity.Student;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.modal.AddStudentModal;
import ui.modal.ChangeStudentModal;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;


public class ChangeStudentModalController {
    @FXML
    public JFXButton applyChangeBtn;
    @FXML
    public JFXButton defaultBtn;
    @FXML
    public JFXButton cancelBtn;
    @FXML
    public JFXTextField nameValueField;
    @FXML
    public JFXTextField famValueField;
    @FXML
    public JFXTextField otchValueField;
    @FXML
    public DatePicker birthday;
    @FXML
    public JFXComboBox<SchoolClass> schoolClassComboBox;
    @FXML
    public AnchorPane changeStudentModalPane;

    private Student student;

    public void initData(Student student) {
        this.student = student;
    }

    @FXML
    protected void initialize() {

        SchoolClassDao schoolClassDao = new SchoolClassDao();
        schoolClassComboBox.getItems().addAll(schoolClassDao.getAll());
    }

    private void setStudentData() {

        nameValueField.setText(student.getName());
        famValueField.setText(student.getFam());
        otchValueField.setText(student.getOtch());
        schoolClassComboBox.getSelectionModel().select(student.getSchoolClass());
        birthday.setValue(student.getNarDate().toLocalDateTime().toLocalDate());
    }

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {

        if (mouseEvent.getSource() == applyChangeBtn) {

            StudentDao studentDao = new StudentDao();

            student.setName(nameValueField.getText());
            student.setFam(famValueField.getText());
            student.setOtch(otchValueField.getText());
            student.setClass(schoolClassComboBox.getSelectionModel().getSelectedItem());

            LocalDate localDate = birthday.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            java.util.Date date = java.util.Date.from(instant);
            Timestamp birthdayDate = new Timestamp(date.getTime());
            student.setNarDate(birthdayDate);

            studentDao.update(student);
            StudentsController.INSTANCE.setTableData();

        } else if (mouseEvent.getSource() == defaultBtn) {
            setStudentData();
        } else if (mouseEvent.getSource() == cancelBtn) {
            ((Stage) changeStudentModalPane.getScene().getWindow()).close();
        }
    }

}
