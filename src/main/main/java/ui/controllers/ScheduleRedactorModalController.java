package ui.controllers;

import com.jfoenix.controls.JFXComboBox;
import data.dao.LessonScheduleDao;
import data.entity.Discipline;
import data.entity.LessonSchedule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ScheduleRedactorModalController {
    @FXML
    private AnchorPane modalPane;
    @FXML
    private JFXComboBox<Discipline> disciplineListView;

    private LessonSchedule schedule;
    private ObservableList<Discipline> observableListDiscipline;

    @FXML
    public void apply(ActionEvent actionEvent) {
        changeSchedule();
        ScheduleController.getInstance().initTable();
        ((Stage) modalPane.getScene().getWindow()).close();
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        ((Stage) modalPane.getScene().getWindow()).close();
    }

    @FXML
    public void selectDiscipline(ActionEvent actionEvent) {
    }

    @FXML
    public void delete(ActionEvent actionEvent) {
        LessonScheduleDao lessonScheduleDao = new LessonScheduleDao();
        lessonScheduleDao.delete(schedule);
        ScheduleController.getInstance().initTable();
        ((Stage) modalPane.getScene().getWindow()).close();
    }

    private void changeSchedule() {
        LessonScheduleDao lessonScheduleDao = new LessonScheduleDao();

        if (disciplineListView.getSelectionModel().getSelectedIndex() == -1)
        {
            ((Stage) modalPane.getScene().getWindow()).close();
        }
        else
            {
            if (schedule.getDiscipline() != null)
            {
                schedule.setDiscipline(disciplineListView.getSelectionModel().getSelectedItem());
                lessonScheduleDao.update(schedule);
            }
            else
                {
                schedule.setDiscipline(disciplineListView.getSelectionModel().getSelectedItem());
                lessonScheduleDao.save(schedule);
            }
        }
    }

    public void initData(LessonSchedule schedule) {
        this.schedule = schedule;
        observableListDiscipline = FXCollections.observableArrayList(schedule.getSchoolClass().getClassDiscipline());
        disciplineListView.setItems(observableListDiscipline);
    }
}
