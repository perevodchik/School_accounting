package data.entity;

import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="students")
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="fam")
    private String fam;

    @Column(name="otch")
    private String otch;

    //@Column(name="class")
    //@OneToMany(fetch = FetchType.LAZY)

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @Column(name="nar_Date")
    private Timestamp narDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFam(String fam) {
        this.fam = fam;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }

    public void setClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public void setNarDate(Timestamp narDate) {
        this.narDate = narDate;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getFam() { return fam; }

    public String getOtch() { return otch; }

    public SchoolClass getSchoolClass() { return schoolClass; }

    public Timestamp getNarDate() { return narDate; }

    public SimpleStringProperty getStringProperty() {
        return new SimpleStringProperty(fam + " " + name + " " + otch);
    }

    @Override
    public String toString() {
        return "id = [ " + id + " ], name = {" + name +
                " ], fam = [ " + fam + " ], otch = [ " + otch +
                " ], class = [ " + schoolClass.getName() + " ], born date = [ " + narDate.toString() + " ]";
    }

}
