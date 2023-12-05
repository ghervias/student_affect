package gui.controllers;

import gui.CodeEditor;
import gui.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tasks.PythonRunner;
import tasks.Session;
import tasks.Task;

public class CodeTaskController implements Controller{
    public Task currentTask;
    public Session currentSession;
    @FXML
    public Text taskTitle;
    public Text taskDesc;
    public Text timerText;
    public CodeEditor editor;

    public void updateDynamicText() {
        taskTitle.setText("Dynamic Text Updated!");
    }

    public void test(ActionEvent e) {
        System.out.println("test!");
    }

    public void checkAnswer(ActionEvent e){
        String answer = editor.getCodeAndSnapshot();
        System.out.println("USER ANSWER: " + answer);
        if (currentSession.checkCompletion(answer)){
            System.out.println("correct answer.");
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            if(currentSession.isOver()){
                SceneSwitcher.endScreen(stage);
            }
            else {
                SceneSwitcher.taskSwitch(stage, currentSession);
//            try {
//                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/fxml/PythonTask.fxml"));
//                Parent root = loader.load();
//
//                //get the controller for the view and set its task to be the next task.
//                CodeTaskController controller = loader.getController();
//                controller.setMyService(currentSession);
//
//
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//            }
//            catch (Exception excep){
//                excep.printStackTrace();
//            }
            }
        }
        else{
            System.out.println("incorrect answer.");
        }
    }

    public void setMyService(Session currentSession) {
        this.currentSession = currentSession;
        this.currentTask = currentSession.nextTask();
        taskDesc.setText(currentTask.getDescription());
        taskTitle.setText("Task number will go here");
        taskTitle.setText("Problem " + currentSession.getTaskNumber());
        currentSession.linkTimerText(timerText);
    }


    public void startSession(){
        currentSession.startSession();
    }
    //attempt to display task information here
}

