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
    private Button addBtn;
    @FXML
    private Button editBtn;
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

    private int id;

    private String url = "jdbc:mysql://localhost/University";
    private String user = "root";
    private String pass = "root";

    /**
     * Getters and Setters
     *
     */

    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public TextField getDateField() {
        return dateField;
    }

    public void setDateField(TextField dateField) {
        this.dateField = dateField;
    }

    public TextField getPassportField() {
        return passportField;
    }

    public void setPassportField(TextField passportField) {
        this.passportField = passportField;
    }

    public CheckBox getMathematicsCheckBox() {
        return mathematicsCheckBox;
    }

    public void setMathematicsCheckBox(CheckBox mathematicsCheckBox) {
        this.mathematicsCheckBox = mathematicsCheckBox;
    }

    public CheckBox getPhilosophyCheckBox() {
        return philosophyCheckBox;
    }

    public void setPhilosophyCheckBox(CheckBox philosophyCheckBox) {
        this.philosophyCheckBox = philosophyCheckBox;
    }

    public CheckBox getSocialEngineeringCheckBox() {
        return socialEngineeringCheckBox;
    }

    public void setSocialEngineeringCheckBox(CheckBox socialEngineeringCheckBox) {
        this.socialEngineeringCheckBox = socialEngineeringCheckBox;
    }

    public CheckBox getLogicCheckBox() {
        return logicCheckBox;
    }

    public void setLogicCheckBox(CheckBox logicCheckBox) {
        this.logicCheckBox = logicCheckBox;
    }

    public CheckBox getPhysicsCheckBox() {
        return physicsCheckBox;
    }

    public void setPhysicsCheckBox(CheckBox physicsCheckBox) {
        this.physicsCheckBox = physicsCheckBox;
    }

    public CheckBox getChemistryCheckBox() {
        return chemistryCheckBox;
    }

    public void setChemistryCheckBox(CheckBox chemistryCheckBox) {
        this.chemistryCheckBox = chemistryCheckBox;
    }

    public CheckBox getCyberneticsCheckBox() {
        return cyberneticsCheckBox;
    }

    public void setCyberneticsCheckBox(CheckBox cyberneticsCheckBox) {
        this.cyberneticsCheckBox = cyberneticsCheckBox;
    }

    public CheckBox getDnaEngineeringCheckBox() {
        return dnaEngineeringCheckBox;
    }

    public void setDnaEngineeringCheckBox(CheckBox dnaEngineeringCheckBox) {
        this.dnaEngineeringCheckBox = dnaEngineeringCheckBox;
    }

    public CheckBox getBiologyCheckBox() {
        return biologyCheckBox;
    }

    public void setBiologyCheckBox(CheckBox biologyCheckBox) {
        this.biologyCheckBox = biologyCheckBox;
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

                    if (mathematicsCheckBox.isSelected()) {
                        setLecturerDisciplineRelationships(nameField.getText(), "Mathematics");
                    }

                    if (philosophyCheckBox.isSelected()) {
                        setLecturerDisciplineRelationships(nameField.getText(), "Philosophy");
                    }

                    if (socialEngineeringCheckBox.isSelected()) {
                        setLecturerDisciplineRelationships(nameField.getText(), "Social Engineering");
                    }

                    if (logicCheckBox.isSelected()) {
                        setLecturerDisciplineRelationships(nameField.getText(), "Logic");
                    }

                    if (physicsCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "Physics");
                    }

                    if (chemistryCheckBox.isSelected()) {
                        setLecturerDisciplineRelationships(nameField.getText(), "Chemistry");
                    }

                    if (cyberneticsCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "Cybernetics");
                    }

                    if (dnaEngineeringCheckBox.isSelected()) {
                        setLecturerDisciplineRelationships(nameField.getText(), "DNA Engineering");
                    }

                    if (biologyCheckBox.isSelected()) {
                        setLecturerDisciplineRelationships(nameField.getText(), "Biology");
                    }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            dialogStage = (Stage) addBtn.getScene().getWindow();
            dialogStage.close();
        }
    }

    @FXML
    private void editBtnClck() {
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
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE lecturers SET " +
                        "full_name = ?, date_of_birth = ?, passport = ? WHERE id = ?");

                preparedStatement.setString(1, nameField.getText());
                preparedStatement.setString(2, dateField.getText());
                preparedStatement.setString(3, passportField.getText());
                preparedStatement.setInt(4, id);

                preparedStatement.executeUpdate();

                if (mathematicsCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "Mathematics");
                }

                if (philosophyCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "Philosophy");
                }

                if (socialEngineeringCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "Social Engineering");
                }

                if (logicCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "Logic");
                }

                if (physicsCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "Physics");
                }

                if (chemistryCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "Chemistry");
                }

                if (cyberneticsCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "Cybernetics");
                }

                if (dnaEngineeringCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "DNA Engineering");
                }

                if (biologyCheckBox.isSelected()) {
                    setLecturerDisciplineRelationships(nameField.getText(), "Biology");
                }

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

    private int getIdParameter(String name) {
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

            int idLecturer = getIdParameter(lecturerName);
            int idDiscipline = getIdParameter(disciplineName);

            preparedStatement.setInt(1, idLecturer);
            preparedStatement.setInt(2, idDiscipline);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void getLecturerDisciplineRelationships(String lecturerName) {
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ds.discipline FROM " +
                    "lectDiscpRelationship ldr LEFT JOIN disciplines ds ON ldr.id_discipline = ds.id " +
                    "WHERE  ldr.id_lecturer = ?" );

            preparedStatement.setInt(1, getIdParameter(lecturerName));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString(1).equals("Cybernetics")) {
                    cyberneticsCheckBox.setSelected(true);
                }
                if (resultSet.getString(1).equals("DNA Engineering")) {
                    dnaEngineeringCheckBox.setSelected(true);
                }
                if (resultSet.getString(1).equals("Social Engineering")) {
                    socialEngineeringCheckBox.setSelected(true);
                }
                if (resultSet.getString(1).equals("Physics")) {
                    physicsCheckBox.setSelected(true);
                }
                if (resultSet.getString(1).equals("Logic")) {
                    logicCheckBox.setSelected(true);
                }
                if (resultSet.getString(1).equals("Philosophy")) {
                    philosophyCheckBox.setSelected(true);
                }
                if (resultSet.getString(1).equals("Chemistry")) {
                    chemistryCheckBox.setSelected(true);
                }
                if (resultSet.getString(1).equals("Mathematics")) {
                    mathematicsCheckBox.setSelected(true);
                }
                if (resultSet.getString(1).equals("Biology")) {
                    biologyCheckBox.setSelected(true);
                }
            }







        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
