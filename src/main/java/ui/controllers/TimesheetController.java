package ui.controllers;

import com.jfoenix.controls.JFXComboBox;
import data.dao.PointDao;
import data.dao.SchoolClassDao;
import data.dao.StudentDao;
import data.entity.Discipline;
import data.entity.Point;
import data.entity.SchoolClass;
import data.entity.Student;
import data.util.StudentPoints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TimesheetController {
    private List<SchoolClass> classList;
    private List<Student> studentList;

    @FXML
    private JFXComboBox<String> classListView;
    @FXML
    private JFXComboBox<String> studentListView;
    @FXML
    private TableView<StudentPoints> timesheetTable;

    @FXML
    private TableColumn<StudentPoints, String> disciplineNameColumn;
    @FXML
    private TableColumn<StudentPoints, String> firstSemestrColumn;
    @FXML
    private TableColumn<StudentPoints, String> secondSemestrColumn;
    @FXML
    private TableColumn<StudentPoints, String> yearPointColumn;

    @FXML
    protected void initialize()
    {
        studentListView.setDisable(true);

        SchoolClassDao schoolClassDao = new SchoolClassDao();
        classList = schoolClassDao.getAll();

        classList.forEach(c -> {
            classListView.getItems().add(c.getName());
            System.out.println(c.getName());
        });

        disciplineNameColumn.setCellValueFactory(cellData -> cellData.getValue().getDiscipline());
        firstSemestrColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstSemestr());
        secondSemestrColumn.setCellValueFactory(cellData -> cellData.getValue().getSecondSemestr());
        yearPointColumn.setCellValueFactory(cellData -> cellData.getValue().getYearsAwrg());
    }

    public void changeSchoolClass(ActionEvent actionEvent) {
        studentListView.getItems().clear();
        studentListView.setDisable(false);
        studentList = new StudentDao().
                getFromSchoolClass(classList.get(classListView.getSelectionModel().getSelectedIndex()));
        if(!studentList.isEmpty()) {
            studentList.forEach(s -> studentListView.getItems().add(s.getFam() + " " + s.getName() + " " + s.getOtch()));
        } else studentListView.setDisable(true);
    }

    public void setTimesheet(ActionEvent actionEvent) {
        Student studnt = studentList.get(studentListView.getSelectionModel().getSelectedIndex());
        Set<Discipline> disciplineList = classList.get(classListView.getSelectionModel().getSelectedIndex()).getClassDiscipline();
        List<StudentPoints> pointList = new ArrayList<>();

        for(Discipline d: disciplineList) {
            pointList.add(new StudentPoints(d.getName(),
                    getSemestrPoint(1, studnt, d),
                    getSemestrPoint(2, studnt, d)));
        }

        timesheetTable.getItems().clear();
        ObservableList<StudentPoints> sp = FXCollections.observableArrayList(pointList);
        timesheetTable.getItems().addAll(sp);
    }

    private float getSemestrPoint(int semestr, Student student, Discipline discipline) {
        float c=0;
        PointDao pointDao = new PointDao();
        List<Point> pointList = pointDao.getStudentPointWithDiscipline(student, discipline, semestr);
        for(Point p: pointList)
        {
            c+= p.getValue();
        }
        return pointList.size() > 0 ? c/pointList.size() : 0;
    }
}
