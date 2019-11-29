package data.entity;

import javax.persistence.*;

@Entity(name="discipline_class")
@Table(name="discipline_class")
public class ClassDiscipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    public ClassDiscipline() {
    }

    public ClassDiscipline(Discipline discipline, SchoolClass schoolClass) {
        this.discipline = discipline;
        this.schoolClass = schoolClass;
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

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }
}
