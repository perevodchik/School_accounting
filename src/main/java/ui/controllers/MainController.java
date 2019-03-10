package ui.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainController {
    @FXML
    public ImageView closebBtn;
    @FXML
    private JFXButton fbtn1;
    @FXML
    private JFXButton fbtn2;
    @FXML
    private BorderPane mainPane;

    private JFXButton activeBtn;

    @FXML
    protected void initialize() { }

    @FXML
    public void setZhurnal(ActionEvent actionEvent) {
        loadUI("zhurnal");
        setActibeBtn(activeBtn, fbtn1);
    }

    @FXML
    public void setTimesheet(ActionEvent actionEvent) {
        loadUI("timesheet");
        setActibeBtn(activeBtn, fbtn2);
    }

    @FXML
    public void close(MouseEvent actionEvent) {
        ((Stage) mainPane.getScene().getWindow()).close();
    }

    private void setActibeBtn(JFXButton btn1, JFXButton btn2) {
        if(activeBtn != null) btn1.getStyleClass().remove("btn-active");
        btn2.getStyleClass().add("btn-active");
        activeBtn = btn2;
        //#0A7460
        //#0F6C5A little more dark
        //#1C574C little^2 more dark
    }

    private void loadUI(String ui) {
        Parent root = null;
        try {
            String path = "/UI_fxml/" + ui + ".fxml";
            root = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        mainPane.setCenter(root);
    }
}
