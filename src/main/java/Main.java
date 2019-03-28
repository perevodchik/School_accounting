import data.HibernateSessionFactoryUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/home.fxml"));
        Scene scene = new Scene(root);


        root.setOnMousePressed(event -> {
            xOffset = primaryStage.getX() - event.getScreenX();
            yOffset = primaryStage.getY() - event.getScreenY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() + xOffset);
            primaryStage.setY(event.getScreenY() + yOffset);
        });

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMaximized(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Облік успішності");

    }

    private class initThread implements Runnable {
        private Logger log = Logger.getLogger(initThread.class.getName());

        @Override
        public void run() {
            log.log(Level.INFO, Thread.currentThread().getName() + " started...");
            HibernateSessionFactoryUtil.getSessionFactory();
            log.log(Level.INFO, Thread.currentThread().getName() + " stopped...");
        }

    }

}
