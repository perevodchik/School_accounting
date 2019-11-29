package ui.controllers;

import com.jfoenix.controls.JFXComboBox;
import data.dao.PointDao;
import data.dao.SchoolClassDao;
import data.dao.StudentDao;
import data.entity.Discipline;
import data.entity.Point;
import data.entity.SchoolClass;
import data.entity.Student;
import data.entity.other.StudentPoints;
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
    private SchoolClass currentClass;
    private Student currentStudent;

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
        SchoolClassDao schoolClassDao = new SchoolClassDao();
        classList = schoolClassDao.getAll();
        timesheetTable.setEditable(true);

        classList.forEach(c -> classListView.getItems().add(c.getName()));

        disciplineNameColumn.setCellValueFactory(cellData -> cellData.getValue().getDiscipline());
        firstSemestrColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstSemestr());
        secondSemestrColumn.setCellValueFactory(cellData -> cellData.getValue().getSecondSemestr());
        yearPointColumn.setCellValueFactory(cellData -> cellData.getValue().getYearsAwrg());
    }

    public void changeSchoolClass(ActionEvent actionEvent) {
        currentStudent = null;
        currentClass = classList.get(classListView.getSelectionModel().getSelectedIndex());

        studentListView.getItems().clear();

        studentList = new StudentDao().getFromSchoolClass(currentClass);

        if(!studentList.isEmpty()) {
            studentList.forEach(s -> studentListView.getItems().add(s.getFam() + " " + s.getName() + " " + s.getOtch()));
        }
    }

    public void setTimesheet(ActionEvent actionEvent) {
        if(studentListView.getItems().size() == studentList.size()) {
            currentStudent = studentList.get(
                    studentListView.getSelectionModel().getSelectedIndex());
            Set<Discipline> disciplineList = currentClass.getClassDiscipline();
            List<StudentPoints> pointList = new ArrayList<>();

            for (Discipline d : disciplineList) {
                pointList.add(new StudentPoints(d.getName(),
                        getSemestrPoint(1, currentStudent, d),
                        getSemestrPoint(2, currentStudent, d)));
            }

            timesheetTable.getItems().clear();
            ObservableList<StudentPoints> sp = FXCollections.observableArrayList(pointList);
            timesheetTable.getItems().addAll(sp);
        }
    }

    private float getSemestrPoint(int semestr, Student student, Discipline discipline) {
        float c = 0;
        PointDao pointDao = new PointDao();
        List<Point> pointList = pointDao.getStudentPointWithDiscipline(student, discipline, semestr);
        int countPoint = pointList.size();

        System.out.println("discipline = " + discipline);
        System.out.println("pointList.size = " + pointList.size());
        System.out.println("c = " + c);
        System.out.println("countPoint = " + countPoint);

        for(Point p: pointList)
        {
            System.out.println(p);
            if(p.getReason().getName().equals("Хворів")) {
                countPoint--;
            } else c += Integer.valueOf(p.getValue());
        }

        return countPoint > 0 ? c/countPoint : 0;
    }
}
