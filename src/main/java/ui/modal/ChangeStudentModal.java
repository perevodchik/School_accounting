package ui.modal;

import data.entity.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.controllers.ChangeStudentModalController;
import ui.controllers.ChangeStudentModalController;

import java.io.IOException;

public class ChangeStudentModal implements IModal {
    private Student student;

    public ChangeStudentModal(Student student) {
        this.student = student;
    }

    @Override
    public Stage showModal(Node node) {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/changeStudentModal.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.UNDECORATED);
        try {
            stage.setScene(new Scene(loader.load())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        ChangeStudentModalController controller =
                loader.getController();
        controller.initData(student);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(node.getScene().getWindow());
        stage.show();

        return stage;
    }
}
