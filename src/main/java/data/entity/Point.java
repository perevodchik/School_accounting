package data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="points")
@Table(name="points")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="val")
    private int value;

    @Column(name="semestr")
    private int semestr;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "reason_id")
    private Reason reason;

    @Column(name="lesson_number")
    private int lessonNumber;

    @Column(name="date")
    private Timestamp date;

    public Point() {}

    public Point(int id, int value, int semestr, Discipline discipline, Student student, Teacher teacher, Reason reason, int lessonNumber, Timestamp date) {
        this. id = id;
        this.value = value;
        this.semestr = semestr;
        this.discipline = discipline;
        this.student = student;
        this.teacher = teacher;
        this.reason = reason;
        this.lessonNumber = lessonNumber;
        this.date = date;
    }

    public int getSemestr() { return semestr; }
    public int getValue() { return value; }
    public Discipline getDiscipline() { return discipline; }
    public Student getStudent() { return student; }
    public Teacher getTeacher() { return teacher; }
    public Reason getReason() { return reason; }
    public int getLessonNumber() { return lessonNumber; }
    public Timestamp getDate() { return date; }

    public void setSemestr(int semestr) { this.semestr = semestr; }
    public void setValue(int value) { this.value = value; }
    public void setDiscipline(Discipline discipline) { this.discipline = discipline; }
    public void setStudent(Student student) { this.student = student; }
    public void setReason(Reason reason) { this.reason = reason; }
    public void setLessonNumber(int lessonNumber) { this.lessonNumber = lessonNumber; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    @Override
    public String toString() {
        return "id = [ " + id + " ], value = [ " + value + " ], " + "semesty = [ " + semestr + " ], " +
                "discipline = [ " + discipline.getName() + " ], " +
                "student = " + student.getFam() + " " + student.getName() + " " + student.getOtch() + ", class = " + student.getClass().getName() + " ], " +
                "teacher = [ " + teacher.getFam() + " " + teacher.getName() + " " + teacher.getOtch() + " ], " +
                "reason = [ " + reason.getName() + " ], lesson = [ " + lessonNumber + " ], " +
                "date = [ " + date.toString() + " ]";
    }

}
