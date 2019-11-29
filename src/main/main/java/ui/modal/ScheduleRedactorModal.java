package ui.modal;

import data.entity.LessonSchedule;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.controllers.ScheduleRedactorModalController;

import java.io.IOException;

public class ScheduleRedactorModal implements IModal {
    private LessonSchedule schedule;

    public ScheduleRedactorModal(LessonSchedule schedule) {
        this.schedule = schedule;
    }

    public Stage showModal() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/scheduleRedactorModal.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.UNDECORATED);
        try {
            stage.setScene(new Scene(loader.load())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        ScheduleRedactorModalController controller =
                loader.getController();
        controller.initData(schedule);

        stage.show();

        return stage;
    }

}
