package data.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="teachers")
@Table(name="teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="fam")
    private String fam;

    @Column(name="otch")
    private String otch;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "discipline_teachers",
            joinColumns = { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "discipline_id") })
    private Set<Discipline> teacherDiscipline = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

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

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setTeacherDiscipline(Set<Discipline> teacherDiscipline) { this.teacherDiscipline = teacherDiscipline; }

    public int getId() { return id; }

    String getName() { return name; }

    String getFam() { return fam; }

    String getOtch() { return otch; }

    public Position getPosition() { return position; }

    public Set<Discipline> getTeacherDiscipline() { return teacherDiscipline; }

    @Override
    public String toString() {
        return "id = [ " + id + " ], name = {" + name +
                " ], fam = [ " + fam + " ], otch = [ " + otch +
                " ], position = [ " + position.getName() + " ], discipline = { " + teacherDiscipline.toString() + " }";
    }
}
