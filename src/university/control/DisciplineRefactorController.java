package university.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DisciplineRefactorController {


    @FXML
    private Button addBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField disciplineField;

    private Stage dialogStage;

    private int id;

    private String url = "jdbc:mysql://localhost/University";
    private String user = "root";
    private String pass = "root";

    /**
     * Getters and Setters
     *
     */
    public TextField getDisciplineField() {
        return disciplineField;
    }

    public void setDisciplineField(TextField disciplineField) {
        this.disciplineField = disciplineField;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @FXML
    public void initialize() {

    }

    @FXML
    private void addBtnClck() {
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

            dialogStage = (Stage) addBtn.getScene().getWindow();
            dialogStage.close();
        }
    }

    @FXML
    private void editBtnClck() {
        if (disciplineField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert window!");
            alert.setHeaderText("The form don't complete.");
            alert.setContentText("Please make form completely.");
        } else {

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE disciplines SET discipline = ? WHERE id = ?");

                preparedStatement.setString(1, disciplineField.getText());
                preparedStatement.setInt(2, id);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            dialogStage = (Stage) editBtn.getScene().getWindow();
            dialogStage.close();
        }
    }

    @FXML
    private void cancelBtnClck() {
        dialogStage = (Stage) cancelBtn.getScene().getWindow();
        dialogStage.close();
    }
}
