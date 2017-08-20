package university.control;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import university.model.Discipline;
import university.model.Group;
import university.model.InnerDataInObsrvblLists;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public class GroupRefactorController {


    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField groupNumField;
    @FXML
    private TextField hallField;
    @FXML
    private ChoiceBox disciplineCBox;
    @FXML
    private ChoiceBox lecturerCBox;
    private boolean saveBtnStatus;
    private Stage dialogStage;
    private Group newGroup = new Group();



    @FXML
    public void initialize() {
        try {

            String url = "jdbc:mysql://localhost/University";
            String user = "root";
            String pass = "root";
            ArrayList<String> lecturersList = new ArrayList<>();

            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT  lrs.full_name FROM lecturers lrs ORDER BY lrs.id;");
            while (resultSet.next()) {
                lecturersList.add(resultSet.getString("lrs.full_name"));
            }
            lecturerCBox.setItems(FXCollections.observableList(lecturersList));
            lecturerCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    ArrayList<String> disciplineList = new ArrayList<>();
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement("SELECT ds.discipline\n" +
                                " FROM University.lectDiscpRelationship ldr\n" +
                                " LEFT JOIN lecturers  ls ON ldr.id_lecturer = ls.id\n" +
                                " LEFT JOIN disciplines ds ON ldr.id_discipline=ds.id\n" +
                                " WHERE ls.full_name LIKE ?;");
                        preparedStatement.setString(1, newValue.toString());
                        ResultSet resultSet1 = preparedStatement.executeQuery();

                        while (resultSet1.next()) {
                            disciplineList.add(resultSet1.getString("ds.discipline"));
                        }
                        disciplineCBox.setItems(FXCollections.observableList(disciplineList));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    private void saveBtnClck() {
        if (groupNumField.getText().isEmpty() || hallField.getText().isEmpty() ||
                lecturerCBox.getSelectionModel().isEmpty() || disciplineCBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert window!");
            alert.setHeaderText("The form don't complete.");
            alert.setContentText("Please make form completely.");
        } else {

            newGroup.setGroupNum(groupNumField.getText());
            newGroup.setLectureHall(hallField.getText());
            newGroup.setLecturer(lecturerCBox.getSelectionModel().getSelectedItem().toString());
            newGroup.setDiscipline(disciplineCBox.getSelectionModel().getSelectedItem().toString());

            String url = "jdbc:mysql://localhost/University";
            String user = "root";
            String pass = "root";

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement preparedStatement = connection.prepareStatement("");

                preparedStatement.setString(1, newGroup.getGroupNum());
                preparedStatement.setString(2, newGroup.getLectureHall());
                preparedStatement.setString(3, newGroup.getLecturer());
                preparedStatement.setString(4, newGroup.getDiscipline());


                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            saveBtnStatus = true;
            dialogStage = (Stage) cancelBtn.getScene().getWindow();
            dialogStage.close();
        }
    }

    @FXML
    private void cancelBtnClck() {
        dialogStage = (Stage) cancelBtn.getScene().getWindow();
        dialogStage.close();
    }
}
