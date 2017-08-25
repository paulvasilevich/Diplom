package university.control;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    String url = "jdbc:mysql://localhost/University";
    String user = "root";
    String pass = "root";

    @FXML
    private void saveBtnClck() {
        if (nameField.getText().isEmpty() || passportField.getText().isEmpty() ||
                dateField.getText().isEmpty() || groupChoiceBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert window!");
            alert.setHeaderText("The form don't complete.");
            alert.setContentText("Please make form completely.");
        } else {

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT  INTO students (full_name, date_of_birth, passport, id_group) " +
                        "VALUES (?,?,?,?)");

                int idGroup = getId();


                preparedStatement.setString(1, nameField.getText());
                preparedStatement.setString(2, dateField.getText());
                preparedStatement.setString(3, passportField.getText());
                preparedStatement.setInt(4, idGroup);


                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            saveBtnStatus = true;
            dialogStage = (Stage) saveBtn.getScene().getWindow();
            dialogStage.close();
        }
    }

    @FXML
    private void cancelBtnClck() {
        dialogStage = (Stage) cancelBtn.getScene().getWindow();
        dialogStage.close();
    }

    private int getId() {
        try {
            Connection connection2 = DriverManager.getConnection(url, user, pass);
            PreparedStatement preparedStatement2 = connection2.prepareStatement("SELECT id FROM groups WHERE group_num = ?");

            preparedStatement2.setString(1, groupChoiceBox.getSelectionModel().getSelectedItem().toString());
            ResultSet resultSet = preparedStatement2.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
