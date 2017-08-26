package university.control;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import university.MainApp;
import university.model.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class StartPageController {

    /**@groupTable*/
    @FXML
    private TableView<Group> tableViewGroup;
    @FXML
    private TableColumn<Group, Integer> tableColumnIdGroup;
    @FXML
    private TableColumn<Group, String> tableColumnNumGroup;
    @FXML
    private TableColumn<Group, String> tableColumnLecturerGroup;
    @FXML
    private TableColumn<Group, String> tableColumnDisciplineGroup;
    @FXML
    private TableColumn<Group, String> tableColumnHallGroup;

    /**@studentTable*/
    @FXML
    private TableView<Student> tableViewStudent;
    @FXML
    private TableColumn<Student, Integer> tableColumnIdStudent;
    @FXML
    private TableColumn<Student, String> tableColumnFullNameStudent;
    @FXML
    private TableColumn<Student, String> tableColumnDateOfBirthStudent;
    @FXML
    private TableColumn<Student, String> tableColumnPassportStudent;
    @FXML
    private TableColumn<Student, String> tableColumnGroupStudent;

    /**@lecturerTable*/
    @FXML
    private TableView<Lecturer> tableViewLecturer;
    @FXML
    private TableColumn<Lecturer, Integer> tableColumnIdLecturer;
    @FXML
    private TableColumn<Lecturer, String> tableColumnFullNameLecturer;
    @FXML
    private TableColumn<Lecturer, String> tableColumnDateOfBirthLecturer;
    @FXML
    private TableColumn<Lecturer, String> tableColumnPassportLecturer;

    /**@lectureHallTable*/
    @FXML
    private TableView<LectureHall> tableViewLectureHall;
    @FXML
    private TableColumn<LectureHall, Integer> tableColumnIdLectureHall;
    @FXML
    private TableColumn<LectureHall, String> tableColumnLectureHall;
    @FXML
    private TableColumn<LectureHall, String> tableColumnGroupsLectureHall;
    @FXML
    private TableColumn<LectureHall, String> tableColumnLectureLectureHall;
    @FXML
    private TableColumn<LectureHall, String> tableColumnDisciplineLectureHall;

    /**@disciplineTable*/
     @FXML
    private TableView<Discipline> tableViewDiscipline;
    @FXML
    private TableColumn<Discipline, Integer> tableColumnIdDiscipline;
    @FXML
    private TableColumn<Discipline, String> tableColumnDiscipline;

    /**@otherField*/
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private ChoiceBox<String> choiceTable;
    @FXML
    private ChoiceBox<String> choiceSearchType;
    private String url = "jdbc:mysql://localhost/University";
    private String user = "root";
    private String pass = "root";


    public TextField getSearchTextField() {
        return searchTextField;
    }

    public void setSearchTextField(TextField searchTextField) {
        this.searchTextField = searchTextField;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(Button searchButton) {
        this.searchButton = searchButton;
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton(Button editButton) {
        this.editButton = editButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public ChoiceBox<String> getChoiceTable() {
        return choiceTable;
    }

    public void setChoiceTable(ChoiceBox<String> choiceTable) {
        this.choiceTable = choiceTable;
    }

    public ChoiceBox<String> getChoiceSearchType() {
        return choiceSearchType;
    }

    public void setChoiceSearchType(ChoiceBox<String> choiceSearchType) {
        this.choiceSearchType = choiceSearchType;
    }

    public InnerDataInObsrvblLists list;

    @FXML
    private void initialize() {
        update();


        choiceTable.setItems(FXCollections.observableArrayList(
                "Groups", "Students", "Lecturers", "Lecture Halls", "Disciplines"));
        choiceTable.getSelectionModel().selectFirst();

        choiceTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch (newValue) {
                    case "Groups": {
                        tableViewGroup.toFront();
                        choiceSearchType.setItems(FXCollections.observableArrayList(
                                "Groups", "Lecturers", "Lecture Halls", "Disciplines"));
                    }
                    break;
                    case "Students": {
                        tableViewStudent.toFront();
                        choiceSearchType.setItems(FXCollections.observableArrayList(
                                "Groups", "Name", "Birthdate", "Passport"));
                    }
                        break;
                    case "Lecturers": {
                        tableViewLecturer.toFront();
                        choiceSearchType.setItems(FXCollections.observableArrayList(
                                 "Name", "Birthdate", "Passport"));
                    }
                        break;
                    case "Lecture Halls": {
                        tableViewLectureHall.toFront();
                        choiceSearchType.setItems(FXCollections.observableArrayList(
                                "Groups", "Lecturers", "Lecture Halls", "Disciplines"));
                    }
                        break;
                    case "Disciplines": {
                        tableViewDiscipline.toFront();
                        choiceSearchType.setItems(FXCollections.observableArrayList(
                                "Disciplines"));
                    }
                        break;
                }
            }
        });


    }

    @FXML
    public void searchBtnClck() {
        if (searchTextField.getText().isEmpty()) {
            update();
        }
        else {
            String tableName = tableName();
            String columName = columnName();
            String searchParameter = searchTextField.getText() + "%";
            ArrayList<String> listProperties = new ArrayList<>();

            try {
                Connection connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM " + tableName + " WHERE " + columName + " LIKE ?");
                preparedStatement.setString(1, searchParameter);
                ResultSet resultSet = preparedStatement.executeQuery();
                switch (tableName) {
                    case "groups": {
                        ArrayList<Group> groupSearchArrayList = new ArrayList<>();
                        while (resultSet.next()) {
                            Group searchGroup = new Group();
                            searchGroup.setId(resultSet.getInt(1));
                            searchGroup.setGroupNum(resultSet.getString(2));
                            searchGroup.setLecturer(resultSet.getString(3));
                            searchGroup.setDiscipline(resultSet.getString(4));
                            searchGroup.setLectureHall(resultSet.getString(5));
                            groupSearchArrayList.add(searchGroup);
                        }
                        ObservableList<Group> groupSearchObservableList =
                                FXCollections.observableList(groupSearchArrayList);
                        updateForSearch(tableName, groupSearchObservableList);
                        break;
                    }
                    case "students": {
                        ArrayList<Student> studentsSearchArrayList = new ArrayList<>();
                        while (resultSet.next()) {
                            Student searchStudent = new Student();
                            searchStudent.setId(resultSet.getInt(1));
                            searchStudent.setFullName(resultSet.getString(2));
                            searchStudent.setDateOfBirth(resultSet.getString(3));
                            searchStudent.setPassport(resultSet.getString(4));
                            searchStudent.setIdGroup(resultSet.getString(5));
                            studentsSearchArrayList.add(searchStudent);
                        }
                        ObservableList<Student> studentsSearchObservableList =
                                FXCollections.observableList(studentsSearchArrayList);
                        updateForSearch(tableName, studentsSearchObservableList);
                        break;
                    }
                    case "lecturers": {
                        ArrayList<Lecturer> lecturersSearchArrayList = new ArrayList<>();
                        while (resultSet.next()) {
                            Lecturer searchLecturer = new Lecturer();
                            searchLecturer.setId(resultSet.getInt(1));
                            searchLecturer.setFullName(resultSet.getString(2));
                            searchLecturer.setDateOfBirth(resultSet.getString(3));
                            searchLecturer.setPassport(resultSet.getString(4));
                            lecturersSearchArrayList.add(searchLecturer);
                        }
                        ObservableList<Lecturer> lecturersSearchObservableList =
                                FXCollections.observableList(lecturersSearchArrayList);
                        updateForSearch(tableName, lecturersSearchObservableList);
                        break;
                    }
                    case "lecturehalls": {
                        ArrayList<LectureHall> lectureHallsSearchArrayList = new ArrayList<>();
                        while (resultSet.next()) {
                            LectureHall searchLectureHalls = new LectureHall();
                            searchLectureHalls.setId(resultSet.getInt(1));
                            searchLectureHalls.setValue(resultSet.getString(2));
                            searchLectureHalls.setLecturer(resultSet.getString(3));
                            searchLectureHalls.setGroups(resultSet.getString(4));
                            searchLectureHalls.setDiscipline(resultSet.getString(5));
                        }
                        ObservableList<LectureHall> lectureHallsSearchObservableList =
                                FXCollections.observableList(lectureHallsSearchArrayList);
                        updateForSearch(tableName, lectureHallsSearchObservableList);
                        break;
                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void addBtnClck(ActionEvent actionEvent) {
       showDialogWindow(actionEvent);

    }

    @FXML
    private void editBtnClck(ActionEvent actionEvent) {
        String tablename = tableName();




        Object item = choiceTable().getSelectionModel().getSelectedItem();

        showDialogWindow(actionEvent, item);


    }

    @FXML
    private void deleteBtnClck() {
        delete(choiceTable().getSelectionModel().getSelectedIndex() + 1);
        update();
    }

    public void update() {
        list = new InnerDataInObsrvblLists();

        tableColumnIdGroup.setCellValueFactory(new PropertyValueFactory<Group, Integer>("id"));
        tableColumnNumGroup.setCellValueFactory(new PropertyValueFactory<Group, String>("groupNum"));
        tableColumnLecturerGroup.setCellValueFactory(new PropertyValueFactory<Group, String>("Lecturer"));
        tableColumnHallGroup.setCellValueFactory(new PropertyValueFactory<Group, String>("LectureHall"));
        tableColumnDisciplineGroup.setCellValueFactory(new PropertyValueFactory<Group, String>("Discipline"));

        tableViewGroup.setItems(list.getGroupObservableList());

        tableColumnIdStudent.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        tableColumnFullNameStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("fullName"));
        tableColumnDateOfBirthStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("dateOfBirth"));
        tableColumnPassportStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("passport"));
        tableColumnGroupStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("idGroup"));

        tableViewStudent.setItems(list.getStudentObservableList());

        tableColumnIdLecturer.setCellValueFactory(new PropertyValueFactory<Lecturer, Integer>("id"));
        tableColumnFullNameLecturer.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("fullName"));
        tableColumnDateOfBirthLecturer.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("dateOfBirth"));
        tableColumnPassportLecturer.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("passport"));

        tableViewLecturer.setItems(list.getLecturerObservableList());

        tableColumnIdLectureHall.setCellValueFactory(new PropertyValueFactory<LectureHall, Integer>("id"));
        tableColumnLectureHall.setCellValueFactory(new PropertyValueFactory<LectureHall, String>("value"));
        tableColumnGroupsLectureHall.setCellValueFactory(new PropertyValueFactory<LectureHall, String>("groups"));
        tableColumnLectureLectureHall.setCellValueFactory(new PropertyValueFactory<LectureHall, String>("lecturer"));
        tableColumnDisciplineLectureHall.setCellValueFactory(new PropertyValueFactory<LectureHall, String>("discipline"));

        tableViewLectureHall.setItems(list.getLectureHallObservableList());

        tableColumnIdDiscipline.setCellValueFactory(new PropertyValueFactory<Discipline, Integer>("id"));
        tableColumnDiscipline.setCellValueFactory(new PropertyValueFactory<Discipline, String>("value"));

        tableViewDiscipline.setItems(list.getDisciplineObservableList());
    }

    public void updateForSearch(String tableName, ObservableList list) {

        switch (tableName) {
            case "groups": {
                tableColumnIdGroup.setCellValueFactory(new PropertyValueFactory<Group, Integer>("id"));
                tableColumnNumGroup.setCellValueFactory(new PropertyValueFactory<Group, String>("groupNum"));
                tableColumnLecturerGroup.setCellValueFactory(new PropertyValueFactory<Group, String>("Lecturer"));
                tableColumnHallGroup.setCellValueFactory(new PropertyValueFactory<Group, String>("LectureHall"));
                tableColumnDisciplineGroup.setCellValueFactory(new PropertyValueFactory<Group, String>("Discipline"));

                tableViewGroup.setItems(list);
                tableViewGroup.toFront();
                break;
            }
            case "students": {
                tableColumnIdStudent.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
                tableColumnFullNameStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("fullName"));
                tableColumnDateOfBirthStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("dateOfBirth"));
                tableColumnPassportStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("passport"));
                tableColumnGroupStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("idGroup"));

                tableViewStudent.setItems(list);
                tableViewStudent.toFront();
                break;
            }

            case "lecturers": {
                tableColumnIdLecturer.setCellValueFactory(new PropertyValueFactory<Lecturer, Integer>("id"));
                tableColumnFullNameLecturer.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("fullName"));
                tableColumnDateOfBirthLecturer.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("dateOfBirth"));
                tableColumnPassportLecturer.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("passport"));

                tableViewLecturer.setItems(list);
                tableViewLecturer.toFront();
                break;
            }

            case "lecturehalls": {
                tableColumnIdLectureHall.setCellValueFactory(new PropertyValueFactory<LectureHall, Integer>("id"));
                tableColumnLectureHall.setCellValueFactory(new PropertyValueFactory<LectureHall, String>("value"));
                tableColumnGroupsLectureHall.setCellValueFactory(new PropertyValueFactory<LectureHall, String>("groups"));
                tableColumnLectureLectureHall.setCellValueFactory(new PropertyValueFactory<LectureHall, String>("lecturer"));
                tableColumnDisciplineLectureHall.setCellValueFactory(new PropertyValueFactory<LectureHall, String>("discipline"));

                tableViewLectureHall.setItems(list);
                tableViewLectureHall.toFront();
                break;
            }
        }
    }

    public void delete(int id) {
        String table = tableName();
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + table + "  WHERE id = ?");

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private TableView choiceTable() {
        switch (choiceTable.getSelectionModel().getSelectedItem()) {
            case "Groups":
                return tableViewGroup;
            case "Students":
                return tableViewStudent;
            case "Lecturers":
                return tableViewLecturer;
            case "Lecture Halls":
                return tableViewLectureHall;
            case "Disciplines":
                return tableViewDiscipline;
        }
        return null;
    }

    private void showDialogWindow(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            switch (choiceTable.getSelectionModel().getSelectedItem()) {
                case "Groups": {
                    stage.setTitle("Group add/edit window");
                    Pane pane = (Pane) FXMLLoader.load(MainApp.class.getResource("view/AddEditGroupPage.fxml"));
                    stage.setScene(new Scene(pane));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
                    stage.showAndWait();
                    stage.close();
                    break;
                }
                case "Students":{
                    stage.setTitle("Student add/edit window");
                    Pane pane = FXMLLoader.load(MainApp.class.getResource("view/AddEditStudentPage.fxml"));
                    stage.setScene(new Scene(pane));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
                    stage.showAndWait();
                    stage.close();
                    break;
                }
                case "Lecturers":{
                    stage.setTitle("Lecturer add/edit window");
                    Pane pane = FXMLLoader.load(MainApp.class.getResource("view/AddEditLecturerPage.fxml"));
                    stage.setScene(new Scene(pane));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
                    stage.showAndWait();
                    stage.close();
                    break;
                }
                case "Disciplines":{
                    stage.setTitle("Discipline add/edit window");
                    Pane pane = FXMLLoader.load(MainApp.class.getResource("view/AddEditDisciplinePage.fxml"));
                    stage.setScene(new Scene(pane));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
                    stage.showAndWait();
                    stage.close();
                    break;
                }
                case "Lecture Halls":{
                    stage.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        update();
    }

    private void showDialogWindow(ActionEvent actionEvent, Object item) {
        Stage stage = new Stage();
        try {
            switch (choiceTable.getSelectionModel().getSelectedItem()) {
                case "Groups": {
                    FXMLLoader loader = new FXMLLoader();
                    stage.setTitle("Group add/edit window");
                    Pane pane = (Pane) loader.load(MainApp.class.getResource("view/AddEditGroupPage.fxml"));
                    Scene scene = new Scene(pane);
                    stage.setScene(scene);


//                    FXMLLoader loader1 = new FXMLLoader();
//                    loader1.setLocation(MainApp.class.getResource("control/GroupRefactorController.java"));
//                    GroupRefactorController groupRefactorController = loader1.getController();
//                    groupRefactorController.getGroupNumField().setText(((Group) item).getGroupNum());
//                    groupRefactorController.getHallField().setText(((Group) item).getLectureHall());
//                    groupRefactorController.getLecturerCBox().setValue(((Group) item).getLecturer());
//                    groupRefactorController.getDisciplineCBox().setValue(((Group) item).getDiscipline());

                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
                    stage.showAndWait();
                    stage.close();
                    break;
                }
                case "Students":{
                    stage.setTitle("Student add/edit window");
                    Pane pane = FXMLLoader.load(MainApp.class.getResource("view/AddEditStudentPage.fxml"));
                    stage.setScene(new Scene(pane));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
                    stage.showAndWait();
                    stage.close();
                    break;
                }
                case "Lecturers":{
                    stage.setTitle("Lecturer add/edit window");
                    Pane pane = FXMLLoader.load(MainApp.class.getResource("view/AddEditLecturerPage.fxml"));
                    stage.setScene(new Scene(pane));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
                    stage.showAndWait();
                    stage.close();
                    break;
                }
                case "Disciplines":{
                    stage.setTitle("Discipline add/edit window");
                    Pane pane = FXMLLoader.load(MainApp.class.getResource("view/AddEditDisciplinePage.fxml"));
                    stage.setScene(new Scene(pane));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
                    stage.showAndWait();
                    stage.close();
                    break;
                }
                case "Lecture Halls":{
                    stage.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        update();
    }

    private String tableName() {
        String tableName = new String();
        switch (choiceTable.getSelectionModel().getSelectedItem()) {
            case "Groups":  tableName = "groups";;
                break;
            case "Students":  tableName = "students";;
                break;
            case "Lecturers":  tableName = "lecturers";;
                break;
            case "Lecture Halls":  tableName = "lecturehalls";;
                break;
            case "Disciplines":  tableName = "disciplines";;
                break;
        }
        return  tableName;
    }

    private String columnName() {
        String searchName = "";
        switch (choiceTable.getSelectionModel().getSelectedItem()) {
            case "Groups":  {

                switch (choiceSearchType.getSelectionModel().getSelectedItem()) {
                    case "Groups" : {
                        searchName = "group_num";
                        break;
                    }
                    case "Lecturers" : {
                        searchName = "id_lecturer";
                        break;
                    }
                    case "Lecture Halls" : {
                        searchName = "id_lecturehall";
                        break;
                    }
                    case "Discipline" : {
                        searchName = "id_discipline";
                        break;
                    }
                }
                break;
            }
            case "Students": {
                switch (choiceSearchType.getSelectionModel().getSelectedItem()) {
                    case "Groups": {
                        searchName = "id_group";
                        break;
                    }
                    case "Name": {
                        searchName = "full_name";
                        break;
                    }
                    case "Birthdate": {
                        searchName = "date_of_birth";
                        break;
                    }
                    case "Passport": {
                        searchName = "passport";
                        break;
                    }
                }
                break;
            }

            case "Lecturers" : {
                switch (choiceSearchType.getSelectionModel().getSelectedItem()) {
                    case "Name": {
                        searchName = "full_name";
                        break;
                    }
                    case "Birthdate": {
                        searchName = "date_of_birth";
                        break;
                    }
                    case "Passport": {
                        searchName = "passport";
                        break;
                    }
                }
                break;
            }
            case "Lecture Halls":  {
                switch (choiceSearchType.getSelectionModel().getSelectedItem()) {
                    case "Groups" : {
                        searchName = "id_group";
                        break;
                    }
                    case "Lecturers" : {
                        searchName = "id_lecturer";
                        break;
                    }
                    case "Lecture Halls" : {
                        searchName = "lecturehall";
                        break;
                    }
                    case "Discipline" : {
                        searchName = "id_discipline";
                        break;
                    }
                }
                break;
            }
            case "Disciplines": {
                searchName = "discipline";
                break;
            }
        }
        return searchName;
    }






}
