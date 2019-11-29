package ui.modal;

import data.entity.SchoolClass;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.controllers.SchoolClassRedactorModalController;

import java.io.IOException;

public class ChangeSchoolClassModal implements IModal {
    private SchoolClass schoolClass;

    //public ChangeSchoolClassModal(SchoolClass schoolClass) { this.schoolClass = schoolClass; }

    public ChangeSchoolClassModal(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Stage showModal() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/changeSchoolClassModal.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.UNDECORATED);
        try {
            stage.setScene(new Scene(loader.load())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        SchoolClassRedactorModalController controller =
                loader.getController();
        controller.initData(schoolClass);

        stage.show();

        return stage;
    }

}
