package data.entity;

import javax.persistence.*;

@Entity(name="day")
@Table(name="day")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String mame;

    public Day() {
    }

    public Day(int id, String mame) {
        this.id = id;
        this.mame = mame;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMame() {
        return mame;
    }

    public void setMame(String mame) {
        this.mame = mame;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", mame='" + mame + '\'' +
                '}';
    }
}
