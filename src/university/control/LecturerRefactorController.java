package university.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import university.model.Lecturer;

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
    private CheckBox MathematicsCheckBox;
    @FXML
    private CheckBox PhiloshophyCheckBox;
    @FXML
    private CheckBox SocialEngineringCheckBox;
    @FXML
    private CheckBox LogicCheckBox;
    @FXML
    private CheckBox PhysicsCheckBox;
    @FXML
    private CheckBox ChemistryCheckBox;
    @FXML
    private CheckBox CyberneticsCheckBox;
    @FXML
    private CheckBox DNAEngineringCheckBox;
    @FXML
    private CheckBox BiologyCheckBox;
    private boolean saveBtnStatus;
    private Stage dialogStage;
    private Lecturer newLecturer = new Lecturer();

    @FXML
    public void initialize() {

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

        newLecturer.setFullName(nameField.getText());
        newLecturer.setDateOfBirth(dateField.getText());
        newLecturer.setPassport(passportField.getText());


        saveBtnStatus=true;
        dialogStage.close();
    }

    @FXML
    private void cancelBtnClck() {
        dialogStage.close();
    }

}
