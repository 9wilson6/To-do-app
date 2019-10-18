package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.database.DatabaseHandler;
import sample.modal.Task;

public class CellController extends JFXListCell<Task> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ImageView iconImageView;

    @FXML
    private Label taskLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label dateViewLabel;

    @FXML
    private ImageView deleteButton;
    FXMLLoader fxmlLoader;
    private DatabaseHandler databaseHandler;
    @FXML
    void initialize() {

    }

    @Override
    protected void updateItem(Task myTask, boolean empty) {
        super.updateItem(myTask, empty);
        if (empty || myTask==null){
            setText(null);
            setGraphic(null);
        }else{
            if (fxmlLoader==null){
                fxmlLoader= new FXMLLoader(getClass()
                        .getResource("/sample/view/cell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            taskLabel.setText(myTask.getTask());
            dateViewLabel.setText(myTask.getDatecreated().toString());
            descriptionLabel.setText(myTask.getDescription());


            deleteButton.setOnMouseClicked(event ->{
                getListView().getItems().remove(getItem());
                databaseHandler = new DatabaseHandler();
                try {
                    databaseHandler.deleteTask(AddItemController.userId,myTask.getTaskId());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
            setText(null);
            setGraphic(rootAnchorPane);
        }
    }
}
