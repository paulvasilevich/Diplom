package university.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class InnerDataInObsrvblLists {



    private List<Group> groupList;
    private List<Student> studentList;
    private List<Lecturer> lecturerList;
    private List<LectureHall> lectureHallList;
    private List<Discipline> disciplineList;
    private List<LecturerDisciplineRelationship> lecturerDisciplineRelationshipList;

    private ObservableList<Group> groupObservableList;
    private ObservableList<Student> studentObservableList;
    private ObservableList<Lecturer> lecturerObservableList;
    private ObservableList<LectureHall> lectureHallObservableList;
    private ObservableList<Discipline> disciplineObservableList;
    private ObservableList<LecturerDisciplineRelationship> lecturerDisciplineRelationshipObservableList;

    /**@getter's
     * and
     * @setter's
     * */
    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Lecturer> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerList(List<Lecturer> lecturerList) {
        this.lecturerList = lecturerList;
    }

    public List<LectureHall> getLectureHallList() {
        return lectureHallList;
    }

    public void setLectureHallList(List<LectureHall> lectureHallList) {
        this.lectureHallList = lectureHallList;
    }

    public List<Discipline> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(List<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }

    public List<LecturerDisciplineRelationship> getLecturerDisciplineRelationshipList() {
        return lecturerDisciplineRelationshipList;
    }

    public void setLecturerDisciplineRelationshipList(List<LecturerDisciplineRelationship> lecturerDisciplineRelationshipList) {
        this.lecturerDisciplineRelationshipList = lecturerDisciplineRelationshipList;
    }

    public ObservableList<Group> getGroupObservableList() {
        return groupObservableList;
    }

    public void setGroupObservableList(ObservableList<Group> groupObservableList) {
        this.groupObservableList = groupObservableList;
    }

    public ObservableList<Student> getStudentObservableList() {
        return studentObservableList;
    }

    public void setStudentObservableList(ObservableList<Student> studentObservableList) {
        this.studentObservableList = studentObservableList;
    }

    public ObservableList<Lecturer> getLecturerObservableList() {
        return lecturerObservableList;
    }

    public void setLecturerObservableList(ObservableList<Lecturer> lecturerObservableList) {
        this.lecturerObservableList = lecturerObservableList;
    }

    public ObservableList<LectureHall> getLectureHallObservableList() {
        return lectureHallObservableList;
    }

    public void setLectureHallObservableList(ObservableList<LectureHall> lectureHallObservableList) {
        this.lectureHallObservableList = lectureHallObservableList;
    }

    public ObservableList<Discipline> getDisciplineObservableList() {
        return disciplineObservableList;
    }

    public void setDisciplineObservableList(ObservableList<Discipline> disciplineObservableList) {
        this.disciplineObservableList = disciplineObservableList;
    }

    public ObservableList<LecturerDisciplineRelationship> getLecturerDisciplineRelationshipObservableList() {
        return lecturerDisciplineRelationshipObservableList;
    }

    public void setLecturerDisciplineRelationshipObservableList(ObservableList<LecturerDisciplineRelationship> lecturerDisciplineRelationshipObservableList) {
        this.lecturerDisciplineRelationshipObservableList = lecturerDisciplineRelationshipObservableList;
    }

    public InnerDataInObsrvblLists() {

        groupList = new LinkedList<>();
        studentList = new LinkedList<>();
        disciplineList = new LinkedList<>();
        lecturerList = new LinkedList<>();
        lectureHallList = new LinkedList<>();

        String url = "jdbc:mysql://localhost/University";
        String user = "root";
        String pass = "root";

        try {
    //        Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            readGroups(statement);
            readStudents(statement);
            readDiscipline(statement);
            readLecturer(statement);
            readLecturerHall(statement);
           // readLectDiscplReltnshps(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        groupObservableList = FXCollections.observableList(getGroupList());
        studentObservableList = FXCollections.observableList(getStudentList());
        lecturerObservableList = FXCollections.observableList(getLecturerList());
        lectureHallObservableList = FXCollections.observableList(getLectureHallList());
        disciplineObservableList = FXCollections.observableList(getDisciplineList());

    }

//    private void readGroups(Statement statement) {
//        ResultSet resultSet = statement.executeQuery("SELECT id, groups FROM group JOIN ");
//        try {
//            while (resultSet.next()) {
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }

    private void readStudents(Statement statement) {
        try {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT \n" +
                            "sts.id, sts.full_name, sts.date_of_birth, sts.passport,\n" +
                            "gs.group_num\n" +
                            "FROM students sts\n" +
                            "LEFT JOIN groups gs ON sts.id_group = gs.id\n" +
                            "ORDER BY sts.id;\n");
            while (resultSet.next()) {
                Student newStudent = new Student();
                newStudent.setId(resultSet.getInt("sts.id"));
                newStudent.setFullName(resultSet.getString("sts.full_name"));
                newStudent.setDateOfBirth(resultSet.getString("sts.date_of_birth"));
                newStudent.setPassport(resultSet.getString("sts.passport"));
                newStudent.setIdGroup(resultSet.getString("gs.group_num"));
                studentList.add(newStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void readLecturer(Statement statement) {
        try {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT lrs.id, lrs.full_name, lrs.date_of_birth, lrs.passport FROM lecturers lrs ORDER BY lrs.id;");
            while (resultSet.next()) {
                Lecturer lecturer = new Lecturer();
                lecturer.setId(resultSet.getInt("lrs.id"));
                lecturer.setFullName(resultSet.getString("lrs.full_name"));
                lecturer.setDateOfBirth(resultSet.getString("lrs.date_of_birth"));
                lecturer.setPassport(resultSet.getString("lrs.passport"));
                lecturerList.add(lecturer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void readLecturerHall(Statement statement) {
        try {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT \n" +
                            "lhs.id, lhs.lecturehall,\n" +
                            "gs.group_num,\n" +
                            "lrs.full_name,\n" +
                            "ds.discipline\n" +
                            "FROM lecturehalls lhs\n" +
                            "LEFT JOIN groups gs ON lhs.id = gs.id_lecturehall\n" +
                            "left join lecturers lrs ON  gs.id_lecturer = lrs.id\n" +
                            "left join disciplines ds ON gs.id_discipline = ds.id\n" +
                            "order by lhs.id;");
            while (resultSet.next()) {
                LectureHall newLectureHall = new LectureHall();
                newLectureHall.setId(resultSet.getInt("lhs.id"));
                newLectureHall.setValue(resultSet.getString("lhs.lecturehall"));
                newLectureHall.setGroups(resultSet.getString("gs.group_num"));
                newLectureHall.setLecturer(resultSet.getString("lrs.full_name"));
                newLectureHall.setDiscipline(resultSet.getString("ds.discipline"));
                lectureHallList.add(newLectureHall);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void readDiscipline(Statement statement) {

        try {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT dscpl.id, dscpl.discipline FROM disciplines dscpl ORDER BY dscpl.id;");
            while (resultSet.next()) {
                Discipline newDiscipline = new Discipline();
                newDiscipline.setId(resultSet.getInt("dscpl.id"));
                newDiscipline.setValue(resultSet.getString("dscpl.discipline"));
                disciplineList.add(newDiscipline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void readGroups(Statement statement) {
        try {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT \n" +
                            "gs.id, gs.group_num,\n" +
                            "lrs.full_name,\n" +
                            "ds.discipline,\n" +
                            "lhs.lecturehall\n" +
                            "FROM groups gs\n" +
                            "LEFT JOIN lecturers lrs ON gs.id_lecturer = lrs.id\n" +
                            "LEFT JOIN disciplines ds ON gs.id_discipline = ds.id\n" +
                            "LEFT JOIN lecturehalls lhs ON gs.id_lecturehall = lhs.id ORDER BY gs.id;");
            while (resultSet.next()) {
                Group newGroup = new Group();
                newGroup.setId(resultSet.getInt("gs.id"));
                newGroup.setGroupNum(resultSet.getString("gs.group_num"));
                newGroup.setLecturer(resultSet.getString("lrs.full_name"));
                newGroup.setDiscipline(resultSet.getString("ds.discipline"));
                newGroup.setLectureHall(resultSet.getString("lhs.lecturehall"));
                groupList.add(newGroup);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
