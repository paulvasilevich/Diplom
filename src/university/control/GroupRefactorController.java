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

    String url = "jdbc:mysql://localhost/University";
    String user = "root";
    String pass = "root";


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

    @FXML
    private void saveBtnClck() {
        if (groupNumField.getText().isEmpty() || hallField.getText().isEmpty() ||
                lecturerCBox.getSelectionModel().isEmpty() || disciplineCBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert window!");
            alert.setHeaderText("The form don't complete.");
            alert.setContentText("Please make form completely.");
        } else {

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT  INTO groups (group_num, id_lecturer, id_discipline, id_lecturehall) " +
                        "VALUES (?,?,?,?)");

                int idDiscipline = getId("disciplines");
                int idLecturer = getId("lecturers");
                int idHall = getId("lecturehalls");

                preparedStatement.setString(1, groupNumField.getText());
                preparedStatement.setInt(2, idLecturer);
                preparedStatement.setInt(3, idDiscipline);
                preparedStatement.setInt(4, idHall);


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

    private int getId(String table) {
        try {
        String column = "";
        String choiceName = "";
        switch (table) {
            case "lecturers" :
                column = "full_name";
                choiceName = lecturerCBox.getSelectionModel().getSelectedItem().toString();
                break;
            case "disciplines":
                column = "discipline";
                choiceName = disciplineCBox.getSelectionModel().getSelectedItem().toString();
                break;
            case "lecturehalls":
                column = "lecturehall";
                choiceName = hallField.getText();
                break;
        }


            Connection connection2 = DriverManager.getConnection(url, user, pass);
            PreparedStatement preparedStatement2 = connection2.prepareStatement("SELECT id FROM " + table +" WHERE " + column +" = ?");

            preparedStatement2.setString(1, choiceName);
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
