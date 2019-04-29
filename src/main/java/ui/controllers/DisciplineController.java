package ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import data.dao.DisciplineDao;
import data.entity.Discipline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import ui.modal.AddDisciplineModal;
import ui.modal.ChangeDisciplineModal;

public class DisciplineController {
    @FXML
    public JFXButton addBtn;
    @FXML
    public JFXButton removeBtn;
    @FXML
    public JFXButton changeBtn;
    @FXML
    public VBox disciplinePane;
    @FXML
    private JFXListView<Discipline> allDisciplineList;

    private Discipline selectedDiscipline;
    private ObservableList<Discipline> disciplineList;
    private static DisciplineController INSTANCE = null;

    @FXML
    protected void initialize() {
        allDisciplineList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                selectedDiscipline = allDisciplineList.getSelectionModel().getSelectedItem();
            }
        );
        setList();
        INSTANCE = this;
    }

    public static DisciplineController getInstance() {
        return INSTANCE;
    }

    public void setList() {
        DisciplineDao disciplineDao = new DisciplineDao();
        disciplineList = FXCollections.observableArrayList();
        disciplineList.addAll(disciplineDao.getAll());
        allDisciplineList.setItems(disciplineList);
    }

    @FXML
    public void handleButtonClicks(ActionEvent actionEvent) {
        DisciplineDao disciplineDao = new DisciplineDao();
        if(actionEvent.getSource() == addBtn) {
            new AddDisciplineModal().showModal(disciplinePane);
        } else if(actionEvent.getSource() == removeBtn) {
            disciplineDao.delete(selectedDiscipline);
            setList();
        } else if(actionEvent.getSource() == changeBtn) {
            new ChangeDisciplineModal(selectedDiscipline).showModal(disciplinePane);
        }
    }
}
