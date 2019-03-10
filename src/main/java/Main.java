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
    private double x, y;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/UI_fxml/main_screen.fxml"));
        Scene scene = new Scene(root);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getSceneX() - x);
            primaryStage.setY(event.getSceneY() - y);
        });

        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setMaximized(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Облік успішності");

        Thread t1 = new Thread(new Main.initThread(), "Init thread");
        t1.start();
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
