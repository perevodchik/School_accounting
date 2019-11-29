package data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name="points")
@Table(name="points")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="val")
    private String value;

    @Column(name="semestr")
    private int semestr;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "reason_id")
    private Reason reason;

    @Column(name="date")
    private Date date;

    public Point() {}

    public Point(int semestr, Discipline discipline, Student student) {
        this.value = "";
        this.semestr = semestr;
        this.discipline = discipline;
        this.student = student;
    }

    public Point(int id, String value, int semestr, Discipline discipline, Student student, Reason reason, Date date) {
        this.id = id;
        this.value = value;
        this.semestr = semestr;
        this.discipline = discipline;
        this.student = student;
        this.reason = reason;
        this.date = date;
    }

    public int getSemestr() { return semestr; }
    public String getValue() { return value; }
    public Discipline getDiscipline() { return discipline; }
    public Student getStudent() { return student; }
    public Reason getReason() { return reason; }
    public Date getDate() { return date; }

    public void setSemestr(int semestr) { this.semestr = semestr; }
    public void setValue(String value) { this.value = value; }
    public void setDiscipline(Discipline discipline) { this.discipline = discipline; }
    public void setStudent(Student student) { this.student = student; }
    public void setReason(Reason reason) { this.reason = reason; }
    public void setDate(Date date) { this.date = date; }

    @Override
    public String toString() {
        return "id = [ " + id + " ], value = [ " + value + " ], " + "semesty = [ " + semestr + " ], " +
                "discipline = [ " + discipline.getName() + " ], " +
                "student = " + student + " ], reason = [ " + reason.getName() + " ], date = [ " + date.toString() + " ]";
    }

}
