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

       // choiceTable.setValue("Groups");
        choiceTable.setItems(FXCollections.observableArrayList(
                "Groups", "Students", "Lecturers", "Lecture Halls", "Disciplines"));
        choiceSearchType.setItems(FXCollections.observableArrayList(
                "Groups", "Students", "Lecturers", "Lecture Halls", "Disciplines"));
        choiceTable.getSelectionModel().selectFirst();

        choiceTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch (newValue) {
                    case "Groups": tableViewGroup.toFront();
                    break;
                    case "Students": tableViewStudent.toFront();
                        break;
                    case "Lecturers": tableViewLecturer.toFront();
                        break;
                    case "Lecture Halls": tableViewLectureHall.toFront();
                        break;
                    case "Disciplines": tableViewDiscipline.toFront();
                        break;
                }
            }
        });


    }

    @FXML
    private void searchBtnClck(ActionEvent actionEvent) {

    }
    @FXML
    public void addBtnClck(ActionEvent actionEvent) {
       showDialogWindow(actionEvent);

    }
    @FXML
    private void editBtnClck(ActionEvent actionEvent) {
        showDialogWindow(actionEvent);
        Object editObject = choiceTable.getSelectionModel().getSelectedIndex();
    }
    @FXML
    private void deleteBtnClck(ActionEvent actionEvent) {
        delete(choiceTable().getSelectionModel().getSelectedIndex());
        update();
    }

    public void update() {
        list = new InnerDataInObsrvblLists();

        tableColumnIdGroup.setCellValueFactory(new PropertyValueFactory<Group, Integer>("id"));
        tableColumnNumGroup.setCellValueFactory(new PropertyValueFactory<Group, String>("groupNum"));
        tableColumnLecturerGroup.setCellValueFactory(new PropertyValueFactory<Group, String>("Lecturer"));
        tableColumnHallGroup.setCellValueFactory(new PropertyValueFactory<Group, String>("Discipline"));
        tableColumnDisciplineGroup.setCellValueFactory(new PropertyValueFactory<Group, String>("LectureHall"));

        tableViewGroup.setItems(list.getGroupObservableList());

        tableColumnIdStudent.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        tableColumnFullNameStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("fullName"));
        tableColumnDateOfBirthStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("dateOfBirth"));
        tableColumnPassportStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("idDiscipline"));
        tableColumnGroupStudent.setCellValueFactory(new PropertyValueFactory<Student, String>("passport"));

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

    public void add(Object object) {

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ? VALUES ()");
            preparedStatement.setString(1,tableName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ? WHERE id = ?");
            preparedStatement.setString(1,  tableName());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void edit() {

    }

    private TableView choiceTable() {
        switch (choiceTable.getSelectionModel().getSelectedItem()) {
            case "Groups":
                return tableViewGroup;
            case "Students":
                return tableViewStudent;
            case "Lecturers":
                return tableViewLecturer;
            case "Lecturers Hall":
                return tableViewLectureHall;
            case "Discipline":
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






}
