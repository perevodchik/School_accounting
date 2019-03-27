package data.entity.other;

import data.entity.Point;
import data.entity.Student;

import java.util.HashMap;
import java.util.Map;

public class PointList{
    private Student student;
    private Map<Integer, Point> pointMap = new HashMap<>();

    public int getSum() {
        return pointMap.entrySet().stream().mapToInt(e -> e.getValue().getValue()).sum();
    }

    private int getCount() {return pointMap.size(); }

    public Student getStudent() {
        return  student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getValue(int key) {
        return String.valueOf(pointMap.get(key).getValue());
    }

    public Map<Integer, Point> getPointMap() {
        return pointMap;
    }

    public void setPointMap(Map<Integer, Point> pointMap) {
        this.pointMap = pointMap;
    }

    @Override
    public String toString() {
        return "PointList{" +
                "student=" + student +
                ", pointMap=" + pointMap +
                '}';
    }
}
