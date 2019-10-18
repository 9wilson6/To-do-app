package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.animation.Shaker;

public class AddItemController {
    public static int userId;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addButton;
    @FXML
    private Label noTaskLabel;


    @FXML
    void initialize() {
addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
    Shaker buttonShaker = new Shaker(addButton);
    buttonShaker.shake();
    FadeTransition bFadeTransition= new FadeTransition(Duration.millis(2000), addButton);
    FadeTransition LFadeTransition= new FadeTransition(Duration.millis(2000), noTaskLabel);
    addButton.relocate(0,20);
    noTaskLabel.relocate(0,85);
    addButton.setOpacity(0);
    noTaskLabel.setOpacity(0);


    LFadeTransition.setFromValue(1f);
    LFadeTransition.setToValue(0f);
    LFadeTransition.setCycleCount(1);
    LFadeTransition.setAutoReverse(false);
    LFadeTransition.play();

    bFadeTransition.setFromValue(1f);
    bFadeTransition.setToValue(0f);
    bFadeTransition.setCycleCount(1);
    bFadeTransition.setAutoReverse(false);
    bFadeTransition.play();

    try {
        AnchorPane formAnchor=
                FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));

      AddItemController.userId=getUserId();
        FadeTransition rootTransition= new FadeTransition(Duration.millis(2000), formAnchor);
        rootTransition.setFromValue(0f);
        rootTransition.setToValue(1f);
        rootTransition.setCycleCount(1);
        rootTransition.setAutoReverse(false);
        rootTransition.play();
        rootAnchorPane.getChildren().setAll(formAnchor);
    } catch (IOException e) {
        e.printStackTrace();
    }
} );
    }

    public void setUserId(int userId) {
        this.userId=userId;
    }
    public int getUserId(){

       return this.userId;
    }
}
