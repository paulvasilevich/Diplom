package university.control;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import university.model.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentRefactorController {
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField nameField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField passportField;
    @FXML
    private ChoiceBox groupChoiceBox;

    private boolean saveBtnStatus;
    private Stage dialogStage;
    private Student newStudent = new Student();

    @FXML
    public void initialize() {

        String url = "jdbc:mysql://localhost/University";
        String user = "root";
        String pass = "root";
        ArrayList<String> groupList = new ArrayList();
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT group_num FROM University.groups ORDER BY groups.id;");
            while (resultSet.next()) {
                groupList.add(resultSet.getString("group_num"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        groupChoiceBox.setItems(FXCollections.observableArrayList(groupList));
    }
    public void setDialogStage(Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }

    public boolean isSaveBtnClck()
    {
        return saveBtnStatus;
    }

    @FXML
    private void saveBtnClck()
    {

        newStudent.setFullName(nameField.getText());
        newStudent.setDateOfBirth(dateField.getText());
        newStudent.setPassport(passportField.getText());
        newStudent.setIdGroup(groupChoiceBox.getSelectionModel().getSelectedItem().toString());

        saveBtnStatus=true;
        dialogStage.close();
    }

    @FXML
    private void cancelBtnClck() {
        dialogStage.close();
    }
}
