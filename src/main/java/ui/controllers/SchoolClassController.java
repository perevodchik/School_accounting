package ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import data.HibernateSessionFactoryUtil;
import data.dao.ClassDisciplineDao;
import data.dao.DisciplineDao;
import data.dao.SchoolClassDao;
import data.dao.StudentDao;
import data.entity.ClassDiscipline;
import data.entity.Discipline;
import data.entity.SchoolClass;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ui.modal.AddSchoolClassModal;

import javax.persistence.Query;

public class SchoolClassController {
    @FXML
    public JFXButton changeBtn;
    @FXML
    public JFXButton removeBtn;
    @FXML
    public JFXButton addBtn;
    @FXML
    public TableView<SchoolClass> schoolClassTable;
    @FXML
    public TableColumn<SchoolClass, String> countCol;
    @FXML
    public TableColumn<SchoolClass, String> nameCol;
    @FXML
    public TableColumn<SchoolClass, String> idCol;
    @FXML
    public JFXListView<Discipline> allDisciplineList;
    @FXML
    public JFXListView<Discipline> schoolClassDisciplineList;

    public static SchoolClassController INSTANCE = null;

    private SchoolClass currentClass;
    private ObservableList<Discipline> disciplineList;
    private ObservableList<SchoolClass> classList;

    @FXML
    protected void initialize() {
        INSTANCE = this;
        disciplineList = FXCollections.observableArrayList(new DisciplineDao().getAll());
        schoolClassTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        setTable();
        setCellValueFactory();
        allDisciplineList.setItems(disciplineList);

        schoolClassTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            currentClass = schoolClassTable.getSelectionModel().getSelectedItem();
            setDiscipline();
        }
        );

        allDisciplineList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ClassDiscipline classDiscipline = new ClassDiscipline(newValue, currentClass);
            new ClassDisciplineDao().save(classDiscipline);
            setDiscipline();
        }
        );

        schoolClassDisciplineList.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue != null) {
                new ClassDisciplineDao().delete(currentClass, newValue);
                setDiscipline();
            }
        }));
    }

    private void setDiscipline() {
        if(currentClass != null) schoolClassDisciplineList.setItems(FXCollections.observableArrayList(new SchoolClassDao().findById(currentClass.getId()).getClassDiscipline()));
    }

    public void setTable() {
        SchoolClassDao schoolClassDao = new SchoolClassDao();
        classList = FXCollections.observableArrayList();

        classList.addAll(schoolClassDao.getAll());

        schoolClassTable.setItems(classList);
    }

    private void setCellValueFactory() {
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        idCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        countCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(
                new StudentDao().getFromSchoolClass(cellData.getValue()).size()
        )));
    }

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if(currentClass != null)
        {
            if (mouseEvent.getSource() == changeBtn)
            {
                new ui.modal.ChangeSchoolClassModal(schoolClassTable.getSelectionModel().getSelectedItem()).showModal();
            } else if (mouseEvent.getSource() == removeBtn)
            {
                ObservableList<SchoolClass> schoolClasses = schoolClassTable.getSelectionModel().getSelectedItems();
                Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                for (SchoolClass schoolClass : schoolClasses)
                {
                    try
                    {
                        session.beginTransaction();
                        Query q = session.createQuery("delete classes where id = " + schoolClass.getId());
                        q.executeUpdate();
                        session.getTransaction().commit();
                    } catch (HibernateException he) {
                        he.printStackTrace();
                    }
                }
            }
        }
        if (mouseEvent.getSource() == addBtn)
        {
            new AddSchoolClassModal().showModal();
        }
        setTable();
    }

}
