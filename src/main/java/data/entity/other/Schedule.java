package data.entity.other;

import data.entity.LessonSchedule;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Schedule {
    private ObservableList<LessonSchedule> lessons = FXCollections.observableArrayList();
    private int lessonNumber;

    public Schedule() {
        lessonNumber = 0;
    }

    public Schedule(ObservableList<LessonSchedule> lessons, int lessonNumber) {
        this.lessons = lessons;
        this.lessonNumber = lessonNumber;
    }

    public ObservableList<LessonSchedule> getLessons() {
        return lessons;
    }

    public void setLessonNames(ObservableList<LessonSchedule> lessonNames) {
        this.lessons = lessonNames;
    }

    public SimpleStringProperty getLessonNumber() {
        return new SimpleStringProperty(String.valueOf(lessonNumber));
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "lessonNames=" + lessons +
                ", lessonNumber=" + lessonNumber +
                '}';
    }
}
