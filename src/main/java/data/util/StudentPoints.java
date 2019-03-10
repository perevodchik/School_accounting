package data.util;

import javafx.beans.property.SimpleStringProperty;

public class StudentPoints {
    private SimpleStringProperty discipline;
    private SimpleStringProperty firstSemestr;
    private SimpleStringProperty secondSemestr;
    private SimpleStringProperty yearsAwrg;

    public StudentPoints() {}

    public StudentPoints(String discipline, Float firstSemestr, Float secondSemestr) {
        this.discipline = new SimpleStringProperty(discipline);
        this.firstSemestr = new SimpleStringProperty(firstSemestr.toString());
        this.secondSemestr = new SimpleStringProperty(secondSemestr.toString());
        yearsAwrg = new SimpleStringProperty(String.valueOf((firstSemestr+secondSemestr)/2));
    }

    public SimpleStringProperty getDiscipline() { return discipline; }
    public SimpleStringProperty getFirstSemestr() { return firstSemestr; }
    public SimpleStringProperty getSecondSemestr() { return secondSemestr; }
    public SimpleStringProperty getYearsAwrg() { return yearsAwrg; }

    @Override
    public String toString() {
        return "discipline = [ " + discipline + " ], first semestr = [ " + firstSemestr + " ], second semestr = [ " + secondSemestr + " ], year avrg = [ " + yearsAwrg + " ]";
    }
}
