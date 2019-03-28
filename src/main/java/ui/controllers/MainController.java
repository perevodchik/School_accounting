package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    public AnchorPane parentPane;
    @FXML
    public ImageView closeBtn;
    @FXML
    public ImageView backBtnBtn;


    @FXML
    protected void initialize() {
        loadUI();
        //HibernateSessionFactoryUtil.getSessionFactory();
        MenuController.setParentPane(parentPane);
    }

    @FXML
    public void close(MouseEvent actionEvent) {
        ((Stage) parentPane.getScene().getWindow()).close();
    }


    public void goToMenu(MouseEvent mouseEvent) {
        loadUI();
    }

    private void loadUI() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(root != null) {
            if (parentPane.getChildren().size() < 2) {
                parentPane.getChildren().add(root);
            } else parentPane.getChildren().set(1, root);
            AnchorPane.setTopAnchor(root, 170.0);
            AnchorPane.setLeftAnchor(root, 150.0);
            AnchorPane.setRightAnchor(root, 150.0);
            AnchorPane.setBottomAnchor(root, 50.0);
        } else System.out.println("root == null");
    }
}
