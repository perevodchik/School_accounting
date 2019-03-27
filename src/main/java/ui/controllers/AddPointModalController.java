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
    }

    @FXML
    public void cancelAdding(ActionEvent actionEvent) {
        ((Stage) addPointModalPane.getScene().getWindow()).close();
    }

    @FXML
    public void addPoint(ActionEvent actionEvent) {
        if(validatePointValue())
        {
            PointDao pointDao = new PointDao();
            point.setValue(Integer.valueOf(pointValueField.getText()));
            point.setReason(pointReasonComboBox.getSelectionModel().getSelectedItem());
            pointDao.save(point);
            ((Stage) addPointModalPane.getScene().getWindow()).close();
            ZhurnalController.getInstance().setTable();
        }
    }

    private boolean validatePointValue() {
        return Pattern.compile("[\\w]+").matcher(pointValueField.getText()).find() && (Integer.valueOf(pointValueField.getText()) < 13 && Integer.valueOf(pointValueField.getText()) > -1);
    }

    public void initData(Point point) {
        this.point = point;
    }
}
