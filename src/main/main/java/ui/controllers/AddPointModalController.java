package ui.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import data.dao.PointDao;
import data.dao.ReasonDao;
import data.entity.Point;
import data.entity.Reason;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class AddPointModalController {
    private int month;
    @FXML
    public JFXComboBox<Reason> pointReasonComboBox;
    @FXML
    public JFXTextField pointValueField;
    @FXML
    public AnchorPane addPointModalPane;

    private Point point;

    @FXML
    public void initialize() {
        ReasonDao reasonDao = new ReasonDao();
        ObservableList<Reason> values = FXCollections.observableList(reasonDao.getAll());
        pointReasonComboBox.setItems(values);
        pointReasonComboBox.getSelectionModel().select(1);

        pointReasonComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if(newValue.getName().equals("Хворів")) {
                        pointValueField.setText("n");
                        pointValueField.setDisable(true);
                    } else {
                        if(pointValueField.getText().equals("n")) pointValueField.setText("");
                        pointValueField.setDisable(false);
                    }
                }
        );
    }

    @FXML
    public void cancelAdding(ActionEvent actionEvent) {
        ((Stage) addPointModalPane.getScene().getWindow()).close();
    }

    @FXML
    public void addPoint(ActionEvent actionEvent) {
        if(validatePointValue()) {
            PointDao pointDao = new PointDao();
            Integer val = null;

            if(pointValueField.getText().isEmpty())
            {
                
            } else if (Integer.valueOf(pointValueField.getText()) < 13 && Integer.valueOf(pointValueField.getText()) > -1)
            {
                point.setValue(pointValueField.getText());
            }

            if(month < 7)
            {
                point.setSemestr(2);
            }
            else {
                point.setSemestr(1);
            }

            point.setReason(pointReasonComboBox.getSelectionModel().getSelectedItem());
            pointDao.save(point);
            ((Stage) addPointModalPane.getScene().getWindow()).close();
            ZhurnalController.getInstance().setTable();
        }
    }

    private boolean validatePointValue() {
        return Pattern.compile("^[\\d]{0,2}$").matcher(pointValueField.getText()).find();
    }

    public void initData(Point point, int currentMonth) {
        this.point = point;
        this.month = currentMonth;
    }
}
