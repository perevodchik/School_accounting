package ui.controllers;

import data.HibernateSessionFactoryUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    public AnchorPane parentPane;
    @FXML
    public ImageView closeBtn;
    @FXML
    public ImageView backBtnBtn;


    @FXML
    protected void initialize() {
        loadUI("menu");
        //HibernateSessionFactoryUtil.getSessionFactory();
        MenuController.setParentPane(parentPane);
    }

    @FXML
    public void close(MouseEvent actionEvent) {
        ((Stage) parentPane.getScene().getWindow()).close();
    }


    public void goToMenu(MouseEvent mouseEvent) {
        loadUI("menu");
    }

    private void loadUI(String ui) {
        Parent root = null;
        try
        {
            String path = "/fxml/" + ui + ".fxml";
            root = FXMLLoader.load(getClass().getResource(path));
        } catch(
        IOException e)

        {
            System.out.println(e.getMessage());
        }
        if(parentPane.getChildren().size() < 2) {
            parentPane.getChildren().add(root);
        } else parentPane.getChildren().set(1, root);
        AnchorPane.setTopAnchor(root,170.0);
        AnchorPane.setLeftAnchor(root,150.0);
        AnchorPane.setRightAnchor(root,150.0);
        AnchorPane.setBottomAnchor(root,50.0);
    }

    public class initThread implements Runnable {
        private Logger log = Logger.getLogger(MainController.initThread.class.getName());

        @Override
        public void run() {
            log.log(Level.INFO, Thread.currentThread().getName() + " started...");
            HibernateSessionFactoryUtil.getSessionFactory();
            log.log(Level.INFO, Thread.currentThread().getName() + " stopped...");
        }

    }
}
