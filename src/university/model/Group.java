package university.model;

public class Group {

    private int id;
    private String groupNum;
    private String Lecturer;
    private String Discipline;
    private String LectureHall;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String getLecturer() {
        return Lecturer;
    }

    public void setLecturer(String lecturer) {
        Lecturer = lecturer;
    }

    public String getDiscipline() {
        return Discipline;
    }

    public void setDiscipline(String discipline) {
        Discipline = discipline;
    }

    public String getLectureHall() {
        return LectureHall;
    }

    public void setLectureHall(String lectureHall) {
        LectureHall = lectureHall;
    }

    public Group() {
    }

    public Group(int id, String groupNum, String lecturer, String discipline, String lectureHall) {
        this.id = id;
        this.groupNum = groupNum;
        Lecturer = lecturer;
        Discipline = discipline;
        LectureHall = lectureHall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != group.id) return false;
        if (groupNum != null ? !groupNum.equals(group.groupNum) : group.groupNum != null) return false;
        if (Lecturer != null ? !Lecturer.equals(group.Lecturer) : group.Lecturer != null) return false;
        if (Discipline != null ? !Discipline.equals(group.Discipline) : group.Discipline != null) return false;
        return LectureHall != null ? LectureHall.equals(group.LectureHall) : group.LectureHall == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (groupNum != null ? groupNum.hashCode() : 0);
        result = 31 * result + (Lecturer != null ? Lecturer.hashCode() : 0);
        result = 31 * result + (Discipline != null ? Discipline.hashCode() : 0);
        result = 31 * result + (LectureHall != null ? LectureHall.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupNum='" + groupNum + '\'' +
                ", Lecturer='" + Lecturer + '\'' +
                ", Discipline='" + Discipline + '\'' +
                ", LectureHall='" + LectureHall + '\'' +
                '}';
    }
}
