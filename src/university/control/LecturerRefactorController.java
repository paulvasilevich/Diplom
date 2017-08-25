package university.control;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class LecturerRefactorController {

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
    private CheckBox mathematicsCheckBox;
    @FXML
    private CheckBox philosophyCheckBox;
    @FXML
    private CheckBox socialEngineeringCheckBox;
    @FXML
    private CheckBox logicCheckBox;
    @FXML
    private CheckBox physicsCheckBox;
    @FXML
    private CheckBox chemistryCheckBox;
    @FXML
    private CheckBox cyberneticsCheckBox;
    @FXML
    private CheckBox dnaEngineeringCheckBox;
    @FXML
    private CheckBox biologyCheckBox;

    private Stage dialogStage;

    @FXML
    public void initialize() {

    }
    String url = "jdbc:mysql://localhost/University";
    String user = "root";
    String pass = "root";

    @FXML
    private void saveBtnClck() {
        if (nameField.getText().isEmpty() || passportField.getText().isEmpty() ||
                dateField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert window!");
            alert.setHeaderText("The form don't complete.");
            alert.setContentText("Please make form completely.");
        } else {

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT  INTO lecturers (full_name, date_of_birth, passport) " +
                        "VALUES (?,?,?)");

                preparedStatement.setString(1, nameField.getText());
                preparedStatement.setString(2, dateField.getText());
                preparedStatement.setString(3, passportField.getText());

                preparedStatement.executeUpdate();

                mathematicsCheckBox.selectedProperty().addListener(event->{
                    if (mathematicsCheckBox.isSelected()) {
                        setLecturerDisciplineRelationships(nameField.getText(), "Mathematics");
                    }
                });

                philosophyCheckBox.selectedProperty().addListener(event->{
                    if (philosophyCheckBox.isSelected()) {
                        setLecturerDisciplineRelationships(nameField.getText(), "Philosophy");
                    }
                });

                socialEngineeringCheckBox.selectedProperty().addListener(event->{
                    if (socialEngineeringCheckBox.isSelected()) {
                        setLecturerDisciplineRelationships(nameField.getText(), "Social Engineering");
                    }
                });

                logicCheckBox.selectedProperty().addListener(event->{
                    if (logicCheckBox.isSelected()) {
                        setLecturerDisciplineRelationships(nameField.getText(), "Logic");
                    }
                });

                physicsCheckBox.selectedProperty().addListener(event->{
                    if (physicsCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "Physics");
                }
                });

                chemistryCheckBox.selectedProperty().addListener(event->{
                    if (chemistryCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "Chemistry");
                }});

                cyberneticsCheckBox.selectedProperty().addListener(event->{
                    if (cyberneticsCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "Cybernetics");
                }
                });

                dnaEngineeringCheckBox.selectedProperty().addListener(event->{
                    if (dnaEngineeringCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "DNA Engineering");
                }});

                biologyCheckBox.selectedProperty().addListener(event->{
                    if (biologyCheckBox.isSelected()) {
                        setLecturerDisciplineRelationships(nameField.getText(), "Biology");
                    }
                });

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

    private int getId(String name) {
        try {
            String table = "disciplines";
            String column = "discipline";

            if (name.equals(nameField.getText())) {
                table = "lecturers";
                column = "full_name";
            }


            Connection connection2 = DriverManager.getConnection(url, user, pass);
            PreparedStatement preparedStatement2 = connection2.prepareStatement("SELECT id FROM " + table + " WHERE " + column + " = ?");

            preparedStatement2.setString(1, name);
            ResultSet resultSet = preparedStatement2.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void setLecturerDisciplineRelationships(String lecturerName, String disciplineName) {
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lectDiscpRelationship (id_lecturer, id_discipline) VALUES (?, ?)");

            int idLecturer = getId(lecturerName);
            int idDiscipline = getId(disciplineName);

            preparedStatement.setInt(1, idLecturer);
            preparedStatement.setInt(2, idDiscipline);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
