package ui.modal;

import data.entity.Point;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.controllers.AddPointModalController;

import java.io.IOException;

public class AddPointModal implements IModal {
    private Point point;

    public AddPointModal(Point point) {
        this.point = point;
    }

    public Stage showModal() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/addPointModal.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.UNDECORATED);
        try {
            stage.setScene(new Scene(loader.load())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        AddPointModalController controller =
                loader.getController();
        controller.initData(point);

        stage.show();

        return stage;
    }
}
