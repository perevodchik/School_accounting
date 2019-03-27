package ui.controllers;

import com.jfoenix.controls.JFXComboBox;
import data.dao.LessonScheduleDao;
import data.dao.SchoolClassDao;
import data.entity.LessonSchedule;
import data.entity.SchoolClass;
import data.entity.other.Schedule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ui.modal.ScheduleRedactorModal;

import java.util.List;

public class ScheduleController {
    private List<SchoolClass> classList;
    private SchoolClass currentClass;
    private static ScheduleController INSTANCE;

    @FXML
    private TableColumn<Schedule, String> columnNumber;
    @FXML
    private TableColumn<Schedule, String> monday;
    @FXML
    private TableColumn<Schedule, String> tuesday;
    @FXML
    private TableColumn<Schedule, String> wednesday;
    @FXML
    private TableColumn<Schedule, String> thursday;
    @FXML
    private TableColumn<Schedule, String> friday;
    @FXML
    private TableColumn<Schedule, String> saturday;
    @FXML
    private TableColumn<Schedule, String> sunday;
    @FXML
    private TableView<Schedule> scheduleTable;
    @FXML
    private JFXComboBox<String> classListView;

    private ObservableList<Schedule> values = FXCollections.observableArrayList();


    @FXML
    protected void initialize()
    {
        classList = new SchoolClassDao().getAll();
        classList.forEach(c -> classListView.getItems().add(c.getName()));

        scheduleTable.setEditable(true);
        scheduleTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        scheduleTable.getSelectionModel().setCellSelectionEnabled(true);

        settableData();

        scheduleTable.setRowFactory(rf -> {
            TableRow<Schedule> r = new TableRow<>();
            r.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!r.isEmpty()) ) {
                    getSchedule();
                    showModal();
                }
            });

            return r;
        });

        INSTANCE = this;
    }

    public void changeSchoolClass(ActionEvent actionEvent) {
        currentClass = classList.get(classListView.getSelectionModel().getSelectedIndex());
        initTable();
    }

    void initTable() {
        scheduleTable.getItems().clear();
        LessonScheduleDao lessonScheduleDao = new LessonScheduleDao();
        for(int j = 1; j < 9; j++) {
            Schedule schedule = new Schedule();
            for(int i = 1; i < 7; i++) {
                List list = lessonScheduleDao.getLesson(currentClass, 1, j, i);
                schedule.getLessons().add(list.isEmpty() ? new LessonSchedule() : (LessonSchedule) list.get(0));
            }
            schedule.setLessonNumber(j);
            values.add(schedule);
        }
        scheduleTable.setItems(values);
    }

    private void settableData() {
        columnNumber.setCellValueFactory(cellData -> cellData.getValue().getLessonNumber());
        monday.setCellValueFactory(cellData -> cellData.getValue().getLessons().get(0).getName());
        tuesday.setCellValueFactory(cellData -> cellData.getValue().getLessons().get(1).getName());
        wednesday.setCellValueFactory(cellData -> cellData.getValue().getLessons().get(2).getName());
        thursday.setCellValueFactory(cellData -> cellData.getValue().getLessons().get(3).getName());
        friday.setCellValueFactory(cellData -> cellData.getValue().getLessons().get(4).getName());
        saturday.setCellValueFactory(cellData -> cellData.getValue().getLessons().get(5).getName());
        //sunday.setCellValueFactory(cellData -> cellData.getValue().getLessons().get(6).getName());
    }

    private LessonSchedule getSchedule() {
        Schedule schedule = scheduleTable.getSelectionModel().getSelectedItem();
        int index = scheduleTable.getSelectionModel().getSelectedCells().get(0).getColumn() - 1;
        LessonSchedule lessonSchedule = null;

        if(index >= 0) {
            lessonSchedule = schedule.getLessons().get(index);

            if(lessonSchedule.getSchoolClass() == null) {
                lessonSchedule = new LessonSchedule();
                lessonSchedule.setDay(index + 1);
                lessonSchedule.setSchoolClass(currentClass);
                lessonSchedule.setLessonNumber(scheduleTable.getSelectionModel().getFocusedIndex() + 1);
                lessonSchedule.setSemestr(1);
            }

        }
        return lessonSchedule;
    }

    private Stage showModal() {
        return new ScheduleRedactorModal(getSchedule()).showModal();
    }

    public static ScheduleController getInstance() {
        return INSTANCE;
    }

}
