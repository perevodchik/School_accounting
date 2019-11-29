package data.entity;

import javax.persistence.*;

@Entity(name="position")
@Table(name="position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    public Position() { }

    public Position(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {return "id = [ "+id+" ], name = [ "+name+" ]";}
}
