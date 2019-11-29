package data.entity;

import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;

@Entity(name="lesson_schedule")
@Table(name="lesson_schedule")
public class LessonSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @Column(name = "day")
    private int day;

    @Column(name = "lesson_number")
    private int lessonNumber;

    @Column(name = "semestr")
    private int semestr;

    public LessonSchedule() { }

    public LessonSchedule(Discipline discipline, SchoolClass schoolClass, int lessonNumber, int semestr, int day) {
        this.discipline = discipline;
        this.schoolClass = schoolClass;
        this.lessonNumber = lessonNumber;
        this.semestr = semestr;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public SimpleStringProperty getName() {
        return new SimpleStringProperty(discipline != null ? discipline.getName() : "");
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public int getSemestr() {
        return semestr;
    }

    public void setSemestr(int semestr) {
        this.semestr = semestr;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "LessonSchedule{" +
                "id=" + id +
                ", discipline=" + discipline +
                ", schoolClass=" + schoolClass +
                ", day=" + day +
                ", lessonNumber=" + lessonNumber +
                ", semestr=" + semestr +
                '}';
    }
}
