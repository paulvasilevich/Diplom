package university.model;

public class Lecturer {

    private int id;
    private String fullName;
    private String dateOfBirth;
    private String passport;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Lecturer() {
    }

    public Lecturer(int id, String fullName, String dateOfBirth, String passport) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lecturer lecturer = (Lecturer) o;

        if (id != lecturer.id) return false;
        if (fullName != null ? !fullName.equals(lecturer.fullName) : lecturer.fullName != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(lecturer.dateOfBirth) : lecturer.dateOfBirth != null)
            return false;
        return passport != null ? passport.equals(lecturer.passport) : lecturer.passport == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
}
