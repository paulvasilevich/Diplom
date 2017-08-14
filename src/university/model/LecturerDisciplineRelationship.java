package university.model;

public class LecturerDisciplineRelationship {
    private String idLecturerValue;
    private String idDisciplineValue;

    public String getIdLecturerValue() {
        return idLecturerValue;
    }

    public void setIdLecturerValue(String idLecturerValue) {
        this.idLecturerValue = idLecturerValue;
    }

    public String getIdDisciplineValue() {
        return idDisciplineValue;
    }

    public void setIdDisciplineValue(String idDisciplineValue) {
        this.idDisciplineValue = idDisciplineValue;
    }

    public LecturerDisciplineRelationship() {
    }

    public LecturerDisciplineRelationship(String idLecturerValue, String idDisciplineValue) {
        this.idLecturerValue = idLecturerValue;
        this.idDisciplineValue = idDisciplineValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LecturerDisciplineRelationship that = (LecturerDisciplineRelationship) o;

        if (idLecturerValue != null ? !idLecturerValue.equals(that.idLecturerValue) : that.idLecturerValue != null)
            return false;
        return idDisciplineValue != null ? idDisciplineValue.equals(that.idDisciplineValue) : that.idDisciplineValue == null;
    }

    @Override
    public int hashCode() {
        int result = idLecturerValue != null ? idLecturerValue.hashCode() : 0;
        result = 31 * result + (idDisciplineValue != null ? idDisciplineValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LecturerDisciplineRelationship{" +
                "idLecturerValue='" + idLecturerValue + '\'' +
                ", idDisciplineValue='" + idDisciplineValue + '\'' +
                '}';
    }
}
