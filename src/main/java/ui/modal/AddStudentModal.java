package ui.modal;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AddStudentModal implements IModal{

    @Override
    public Stage showModal(Node node) {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/addStudentModal.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.UNDECORATED);
        try {
            stage.setScene(new Scene(loader.load())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(node.getScene().getWindow());
        stage.show();

        return stage;
    }
}
