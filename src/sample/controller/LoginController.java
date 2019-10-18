package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.animation.Shaker;
import sample.database.DatabaseHandler;
import sample.modal.User;

public class LoginController {
    DatabaseHandler dbh;
    private int userId;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField loginUsernameTf;

    @FXML
    private JFXPasswordField loginPasswordTf;

    @FXML
    private JFXButton loginLoginButton;

    @FXML
    private JFXButton loginSignUpButton;

    @FXML
    void initialize() {
        loginSignUpButton.setOnAction(event -> {

            //take user to signUp screen
            loginSignUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root= loader.getRoot();
            Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        loginLoginButton.setOnAction(event -> {
            dbh= new DatabaseHandler();
            User user= new User();
            user.setUsername(loginUsernameTf.getText().trim());
            user.setPassword(loginPasswordTf.getText().trim());
          ResultSet userRow= dbh.isUserLoggedIn(user);
          int counter=0;
try {
    while (userRow.next()){
        counter ++;
        userId=userRow.getInt("userid");
    }
} catch (SQLException e) {
    e.printStackTrace();
}
if (counter>0){
    showAddItemScreen();
}else {
    Shaker userNameShaker= new Shaker(loginUsernameTf);
    Shaker passwordShaker= new Shaker(loginPasswordTf);
    userNameShaker.shake();
    passwordShaker.shake();
}
        });
    }

    private void showAddItemScreen() {
        loginPasswordTf.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/addItem.fxml"));
        try {
            loader.load();
            Parent root= loader.getRoot();
            Stage stage= new Stage();
            stage.setScene(new Scene(root));
            AddItemController addItemController= new AddItemController();
            addItemController.setUserId(userId);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
