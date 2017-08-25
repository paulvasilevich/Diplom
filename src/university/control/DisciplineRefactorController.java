package university.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class DisciplineRefactorController {


    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField disciplineField;

    private Stage dialogStage;
    @FXML
    public void initialize() {

    }
    String url = "jdbc:mysql://localhost/University";
    String user = "root";
    String pass = "root";

    @FXML
    private void saveBtnClck() {
        if (disciplineField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert window!");
            alert.setHeaderText("The form don't complete.");
            alert.setContentText("Please make form completely.");
        } else {

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT  INTO disciplines (discipline) " +
                        "VALUES (?)");

                preparedStatement.setString(1, disciplineField.getText());

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            dialogStage = (Stage) saveBtn.getScene().getWindow();
            dialogStage.close();
        }
    }

    @FXML
    private void cancelBtnClck() {
        dialogStage = (Stage) cancelBtn.getScene().getWindow();
        dialogStage.close();
    }
}
