package university.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import university.model.Discipline;

public class DisciplineRefactorController {


    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField disciplineField;
    private boolean saveBtnStatus;
    private Stage dialogStage;
    private Discipline newDiscipline = new Discipline();
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

        newDiscipline.setValue(disciplineField.getText());

        saveBtnStatus=true;
        dialogStage.close();
    }

    @FXML
    private void cancelBtnClck() {
        dialogStage.close();
    }
}
