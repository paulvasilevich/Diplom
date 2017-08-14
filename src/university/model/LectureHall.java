package university.model;

public class LectureHall {

    private int id;
    private String value;
    private String lecturer;
    private String groups;
    private String discipline;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public LectureHall() {
    }

    public LectureHall(int id, String value, String lecturer, String groups, String discipline) {
        this.id = id;
        this.value = value;
        this.lecturer = lecturer;
        this.groups = groups;
        this.discipline = discipline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LectureHall that = (LectureHall) o;

        if (id != that.id) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (lecturer != null ? !lecturer.equals(that.lecturer) : that.lecturer != null) return false;
        if (groups != null ? !groups.equals(that.groups) : that.groups != null) return false;
        return discipline != null ? discipline.equals(that.discipline) : that.discipline == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (lecturer != null ? lecturer.hashCode() : 0);
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        result = 31 * result + (discipline != null ? discipline.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LectureHall{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", lecturer='" + lecturer + '\'' +
                ", groups='" + groups + '\'' +
                ", discipline='" + discipline + '\'' +
                '}';
    }
}

