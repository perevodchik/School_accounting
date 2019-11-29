package data.entity;

import javax.persistence.*;

@Entity(name="discipline")
@Table(name="discipline")
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    public Discipline() { }

    public Discipline(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {return name;}
}
