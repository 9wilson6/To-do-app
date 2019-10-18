package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import sample.database.DatabaseHandler;
import sample.modal.Task;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label taskAddedLabel;
    @FXML
    private ImageView listRefresh;
    @FXML
    private JFXListView<Task> itemListView;

    @FXML
    private JFXTextField listTastTaskTf;

    @FXML
    private JFXTextField listTaskDescriptionTf;

    @FXML
    private JFXButton listSaveTaskButton;
    private ObservableList<Task> tasks;
    private ObservableList<Task> refreshedTask;
    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        tasks = FXCollections.observableArrayList();
        databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getResultByUserId(AddItemController.userId);
        while (resultSet.next()){
            Task task = new Task();
            task.setTask(resultSet.getString("task"));
            task.setDatecreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));
            task.setTaskId(resultSet.getInt("taskId"));
            tasks.add(task);
        }

        itemListView.setItems(tasks);
        itemListView.setCellFactory(param -> new CellController());

        listSaveTaskButton.setOnAction(event -> {
            try {
                saveTask();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        listRefresh.setOnMouseClicked(event -> {
            try {
                refreshList();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
public void refreshList() throws SQLException, ClassNotFoundException {
    System.out.println("Things have been refreshed");
        refreshedTask= FXCollections.observableArrayList();
        DatabaseHandler databaseHandler= new DatabaseHandler();
        ResultSet resultSet=databaseHandler.getResultByUserId(AddItemController.userId);
        while (resultSet.next()){
            Task task= new Task();
            task.setTaskId(resultSet.getInt("taskId"));
            task.setTask(resultSet.getString("task"));
            task.setDescription(resultSet.getString("description"));
            task.setDatecreated(resultSet.getTimestamp("datecreated"));
            refreshedTask.addAll(task);
        }
}
public void saveTask() throws SQLException, ClassNotFoundException {

         String task=   listTastTaskTf.getText().trim();
         String description=listTaskDescriptionTf.getText().trim();
         if (!task.equals("") || !description.equals("")){
             Task myNewTask= new Task();
             myNewTask.setTask(task);
             myNewTask.setDescription(description);
             Calendar calendar=Calendar.getInstance();
             Timestamp timestamp= new Timestamp(calendar.getTimeInMillis());
             myNewTask.setDatecreated(timestamp);
            myNewTask.setUserId(AddItemController.userId);
             databaseHandler.insertTask(myNewTask);
             listTastTaskTf.setText("");
             listTaskDescriptionTf.setText("");
             initialize();
         }

}

public void updateTask(){


}
}
