package ui.modal;

import data.entity.Point;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.controllers.AddPointModalController;

import java.io.IOException;

public class AddPointModal implements IModal {
    private Point point;
    private int currentMonth;

    public AddPointModal(Point point, int currentMonth) {
        this.currentMonth = currentMonth;
        this.point = point;
    }


    @Override
    public Stage showModal(Node node) {
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
        controller.initData(point, currentMonth);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(node.getScene().getWindow());
        stage.show();

        return stage;
    }
}
