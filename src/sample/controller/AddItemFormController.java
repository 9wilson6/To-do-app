package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.modal.Task;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddItemFormController {
    DatabaseHandler dbh;
    private int userId;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField tastTaskTf;

    @FXML
    private JFXTextField taskDescriptionTf;

    @FXML
    private JFXButton saveTaskButton;
    @FXML
    private Label taskAddedLabel;
    @FXML
    private JFXButton myTodosShowButton;

    @FXML
    void initialize() {
        saveTaskButton.setOnAction(event -> {
            dbh = new DatabaseHandler();
            String task = tastTaskTf.getText().trim();
            String taskDesc = taskDescriptionTf.getText();
            Calendar calendar = Calendar.getInstance();
            Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
            Task taskobj = new Task(AddItemController.userId, timestamp, task, taskDesc);
            dbh.insertTask(taskobj);
            tastTaskTf.setText("");

            taskDescriptionTf.setText("");
            taskAddedLabel.setVisible(true);

            try {
                myTodosShowButton.setVisible(true);
                myTodosShowButton.setText("My todos: " + "("+dbh.countUserTasks(AddItemController.userId)+")");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            myTodosShowButton.setOnAction(event1 -> {
                FXMLLoader loader = new FXMLLoader();
                myTodosShowButton.getScene().getWindow().hide();
                loader.setLocation(getClass().getResource("/sample/view/list.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage= new Stage();
                Scene scene= new Scene(root);
                stage.setScene(scene);
                stage.show();

            });
        });
    }

}
