package data.entity;

import javax.persistence.*;

@Entity(name="lesson_number")
@Table(name="lesson_number")
public class LessonNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lessonNumber;

    public LessonNumber() {
    }

    public LessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    @Override
    public String toString() {
        return "LessonNumber{" +
                "lessonNumber=" + lessonNumber +
                '}';
    }
}
