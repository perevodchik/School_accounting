package ui.modal;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AddSchoolClassModal implements IModal{
    public Stage showModal() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/addSchoolClassModal.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.UNDECORATED);
        try {
            stage.setScene(new Scene(loader.load())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.show();

        return stage;
    }
}
