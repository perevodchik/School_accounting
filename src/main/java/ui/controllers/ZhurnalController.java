package ui.controllers;

import com.jfoenix.controls.JFXComboBox;
import data.dao.LessonScheduleDao;
import data.dao.PointDao;
import data.dao.SchoolClassDao;
import data.dao.StudentDao;
import data.entity.*;
import data.entity.other.PointList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import ui.modal.AddPointModal;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class ZhurnalController {
    @FXML
    public JFXComboBox<String> classListView;
    @FXML
    public JFXComboBox<Discipline> disciplineListView;
    @FXML
    public JFXComboBox<Integer> monthListView;
    @FXML
    public TableView<PointList> tableView;
    public JFXComboBox<String> yearListView;

    private SchoolClass currentClass;
    private Discipline currentDiscipline;
    private String currentYear;
    private int currentMonth = 1;

    private List<SchoolClass> classList;
    private ObservableList<Discipline> disciplineList = FXCollections.observableArrayList();
    private List<Student> studentList;
    private ObservableList<PointList> values = FXCollections.observableArrayList();

    private static ZhurnalController INSTANCE;


    @FXML
    protected void initialize()
    {
        classListView.getItems().clear();
        classList = new SchoolClassDao().getAll();
        classList.forEach(c -> classListView.getItems().add(c.getName()));
        tableView.setEditable(true);

        tableView.setRowFactory(rf -> {
            TableRow<PointList> r = new TableRow<>();
            r.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!r.isEmpty()) && tableView.getSelectionModel().getSelectedCells().get(0).getColumn() > 0) {
                    showModal();
                }
            });

            return r;
        });

        for(int c = 1; c < 13; c++)
        {
            monthListView.getItems().add(c);
        }

        monthListView.getSelectionModel().select(0);

        for(int i = 2000; i <= new LocalDate().getYear(); i++)
        {
            yearListView.getItems().add(i-1 + " - " + i);
        }

        INSTANCE = this;
    }

    public void setTable()
    {
        if(currentClass != null && currentDiscipline != null && currentYear != null)
        {
            List<LessonSchedule> l = new LessonScheduleDao().getLessonByWeek(currentClass, currentDiscipline);

            Calendar calendar = new GregorianCalendar();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, currentMonth - 1);
            int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            LocalDate date = new LocalDate().
                    withMonthOfYear(currentMonth).
                    withDayOfMonth(1);

            tableView.getColumns().clear();
            TableColumn <PointList, String> t = new TableColumn<>();
            t.setCellValueFactory(cellData -> cellData.getValue().getStudent().getStringProperty());
            t.setText("Учень");
            tableView.getColumns().add(t);

            for (int i = 1; i <= days; i++)
            {
                for (LessonSchedule lessonSchedule : l)
                {
                    date = date.withDayOfMonth(i);
                    if(lessonSchedule.getDay() == date.getDayOfWeek())
                    {
                        setTableColumns(i);
                    }
                }
            }
            getPoints();

        }
    }

    @FXML
    public void changeSchoolClass(ActionEvent actionEvent)
    {
        tableView.getItems().clear();
        currentClass = classList.get(classListView.
                getSelectionModel().
                getSelectedIndex()
        );
        disciplineListView.getItems().clear();
        disciplineList.addAll(currentClass.getClassDiscipline());
        disciplineListView.setItems(disciplineList);
        if(!disciplineList.contains(currentDiscipline)) currentDiscipline = null;
        setTable();

        StudentDao studentDao = new StudentDao();
        studentList = studentDao.getFromSchoolClass(currentClass);
    }

    @FXML
    public void changeDiscipline(ActionEvent actionEvent) {
        if(!disciplineListView.getItems().isEmpty()) {
            currentDiscipline = disciplineListView.getSelectionModel().getSelectedItem();
        }
        setTable();
    }

    @FXML
    public void changeMonth(ActionEvent actionEvent) {
        currentMonth = monthListView.
                getSelectionModel().
                getSelectedIndex() + 1;
        setTable();
    }

    private void getPoints()
    {
        PointDao pointDao = new PointDao();
        tableView.getItems().clear();

        for(Student student: studentList)
        {

            PointList pointList = new PointList();
            pointList.setStudent(student);

            for(int c = 1; c < tableView.getColumns().size(); c++)
            {
                TableColumn col = tableView.getColumns().get(c);
                DateTime dateTime = new DateTime().
                        withMonthOfYear(currentMonth).
                        withDayOfMonth(Integer.valueOf(col.getText()));

                Date date = null;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    java.util.Date dateUtil =  format.parse ( dateTime.toString() );
                    date = new Date(dateUtil.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                List<Point> pList = pointDao.getPointWithDay(student, currentDiscipline, date, currentClass);
                Point p = !pList.isEmpty() ? pList.get(0) : new Point(
                        //currentMonth < 6 ? 1 : 2,
                        1,
                        currentDiscipline,
                        student);
                pointList.getPointMap().put(Integer.valueOf(col.getText()), p);

            }
            pointList.getPointMap().entrySet().forEach(e -> System.out.println(e.getValue().getValue()));
            tableView.getItems().add(pointList);
        }
    }

    public void changeYear(ActionEvent actionEvent)
    {
        currentYear = yearListView.getSelectionModel().getSelectedItem();
        setTable();
    }

    private void setTableColumns(int day)
    {
        TableColumn<PointList, String> column = new TableColumn<>();

        column.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getValue(day).equals("-1") ? "" : cellData.getValue().getValue(day))
        );

        column.setText(String.valueOf(day));
        tableView.getColumns().add(column);
    }

    private void showModal() {
        PointList pointList = tableView.getSelectionModel().getSelectedItem();
        int index = tableView.getSelectionModel().getSelectedCells().get(0).getColumn();
        Point p = pointList.getPointMap().get(Integer.valueOf(tableView.getColumns().get(index).getText()));
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(tableView.getColumns().get(index).getText()));
        calendar.set(Calendar.MONTH, currentMonth - 1);

        p.setDate(new Date(calendar.getTimeInMillis()));
        new AddPointModal(p).showModal();
    }

    public static ZhurnalController getInstance() {
        return INSTANCE;
    }
}
