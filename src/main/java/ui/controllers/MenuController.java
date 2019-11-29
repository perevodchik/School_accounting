package ui.controllers;

import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;


import java.io.IOException;

public class MenuController {
    private static AnchorPane parentPane = null;

    @FXML
    public Button btnClasses;
    @FXML
    public Button btnSchedule;
    @FXML
    public Button btnDiscipline;
    @FXML
    public Button btnTimetable;
    @FXML
    public Button btnStudents;
    @FXML
    public Button btnZhurnal;

    @FXML
    protected void initialize() {
        btnClasses.setGraphic(fontAwesomeIconGlyphIconFactory(FontAwesomeIcon.GRADUATION_CAP));
        btnSchedule.setGraphic(fontAwesomeIconGlyphIconFactory(FontAwesomeIcon.CLIPBOARD));
        btnDiscipline.setGraphic(fontAwesomeIconGlyphIconFactory(FontAwesomeIcon.BELL));
        btnTimetable.setGraphic(fontAwesomeIconGlyphIconFactory(FontAwesomeIcon.FILE_ALT));
        btnStudents.setGraphic(fontAwesomeIconGlyphIconFactory(FontAwesomeIcon.GROUP));
        btnZhurnal.setGraphic(fontAwesomeIconGlyphIconFactory(FontAwesomeIcon.BOOK));
    }

    private GlyphIcon<FontAwesomeIcon> fontAwesomeIconGlyphIconFactory(FontAwesomeIcon _icon) {
        GlyphIcon<FontAwesomeIcon> icon = new FontAwesomeIconView(_icon);
        icon.setSize("50");
        icon.setFill(Paint.valueOf("#2d75e8"));
        return icon;
    }

    static void setParentPane(Pane _parentPane) {
        parentPane = (AnchorPane) _parentPane;
    }

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnClasses) {
            loadUI("schoolClasses");
        } else if (mouseEvent.getSource() == btnSchedule) {
            loadUI("schedule");
        } else if (mouseEvent.getSource() == btnDiscipline) {
            loadUI("discipline");
        } else if (mouseEvent.getSource() == btnTimetable) {
            loadUI("timetable");
        } else if (mouseEvent.getSource() == btnStudents) {
            loadUI("students");
        } else if (mouseEvent.getSource() == btnZhurnal) {
            loadUI("zhurnal");
        }
    }

    private void loadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/" + ui + ".fxml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        parentPane.getChildren().set(1, root);
        AnchorPane.setTopAnchor(root, 170.0);
        AnchorPane.setLeftAnchor(root, 150.0);
        AnchorPane.setRightAnchor(root, 150.0);
        AnchorPane.setBottomAnchor(root, 5.0);
    }
}
