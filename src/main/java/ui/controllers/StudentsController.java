package ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import data.dao.SchoolClassDao;
import data.dao.StudentDao;
import data.entity.SchoolClass;
import data.entity.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.modal.AddStudentModal;
import ui.modal.ChangeStudentModal;


public class StudentsController {
    @FXML
    public JFXComboBox<SchoolClass> schoolClassList;
    
    @FXML
    public TableView<Student> studentTable;
    @FXML
    public TableColumn<Student, String> studentName;
    @FXML
    public TableColumn<Student, String> studentFam;
    @FXML
    public TableColumn<Student, String> studentOtch;
    @FXML
    public TableColumn<Student, String> studentBirthday;
    
    @FXML
    public JFXButton addStudent;
    @FXML
    public JFXButton removeStudent;
    @FXML
    public JFXButton changeStudent;

    private SchoolClass currentClass;
    private ObservableList<SchoolClass> values = FXCollections.observableArrayList();

    static StudentsController INSTANCE;

    @FXML
    protected void initialize() {
        SchoolClassDao schoolClassDao = new SchoolClassDao();
        schoolClassList.getItems().clear();

        schoolClassDao.getAll().forEach(s -> schoolClassList.getItems().add(s));
        values.addAll(schoolClassDao.getAll());
        columnValueFactory();
        INSTANCE = this;
    }

    public void setCurrentClass(ActionEvent actionEvent) {
        this.currentClass = schoolClassList.getSelectionModel().getSelectedItem();
        setTableData();

    }

    void setTableData() {
        ObservableList<Student> students = FXCollections.observableArrayList();
        StudentDao studentDao = new StudentDao();
        students.addAll(studentDao.getFromSchoolClass(currentClass));
        studentTable.setItems(students);
    }

    private void columnValueFactory() {
        studentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        studentFam.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFam()));
        studentOtch.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOtch()));
        studentBirthday.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNarDate().toString()));
    }

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == addStudent) {

            new AddStudentModal().showModal();
        } else if (mouseEvent.getSource() == removeStudent) {

            StudentDao studentDao = new StudentDao();
            Student student = studentTable.getSelectionModel().getSelectedItem();
            if(student != null) studentDao.delete(student);
            setTableData();
        } else if (mouseEvent.getSource() == changeStudent) {

            new ChangeStudentModal(studentTable.getSelectionModel().getSelectedItem()).showModal();
        }
    }
}
