package ui.modal;

import data.entity.LessonSchedule;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.controllers.ScheduleRedactorModalController;

import java.io.IOException;

public class ScheduleRedactorModal implements IModal {
    private LessonSchedule schedule;

    public ScheduleRedactorModal(LessonSchedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public Stage showModal(Node node) {
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

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(node.getScene().getWindow());
        stage.show();

        return stage;
    }

}
