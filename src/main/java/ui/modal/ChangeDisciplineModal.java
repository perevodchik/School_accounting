package ui.modal;

import data.entity.Discipline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.controllers.ChangeDisciplineModalController;

import java.io.IOException;

public class ChangeDisciplineModal implements IModal {
    private Discipline discipline;

    public ChangeDisciplineModal(Discipline discipline) {
        this.discipline = discipline;
        }

    public Stage showModal() {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/changeDisciplineModal.fxml")
        );

        Stage stage = new Stage(StageStyle.UNDECORATED);
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ChangeDisciplineModalController controller =
        loader.getController();
        controller.initData(discipline);

        stage.show();

        return stage;
    }

}
