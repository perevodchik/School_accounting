package data.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="classes")
@Table(name="classes")
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "discipline_class",
            joinColumns = { @JoinColumn(name = "class_id") },
            inverseJoinColumns = { @JoinColumn(name = "discipline_id") })
    private Set<Discipline> classDiscipline = new HashSet<>();

    public SchoolClass() {}

    public SchoolClass(int id, String name, Set<Discipline> classDiscipline) {
        this.id = id;
        this.name = name;
        this.classDiscipline = classDiscipline;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Set<Discipline> getClassDiscipline() {return classDiscipline;}
    public void setClassDiscipline(Set<Discipline> classDiscipline) {this.classDiscipline = classDiscipline;}

    @Override
    public String toString() {return "id = [ "+id+" ], name = [ "+name+" ], class discipline = { " + classDiscipline.toString() + " }";}
}
