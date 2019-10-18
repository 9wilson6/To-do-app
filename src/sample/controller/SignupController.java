package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.modal.User;

public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton signUpLoginButton;

    @FXML
    private JFXTextField signUpFname;

    @FXML
    private JFXTextField signUpLname;

    @FXML
    private JFXTextField signUpUname;

    @FXML
    private JFXTextField signUpLocation;

    @FXML
    private JFXCheckBox signUpMcheckbx;

    @FXML
    private JFXCheckBox signUpFcheckbx;

    @FXML
    private JFXPasswordField signUpPassword;

    @FXML
    private JFXButton signUpButton;

    @FXML
    void initialize() {

        signUpLoginButton.setOnAction(event -> {

            signUpLoginButton.getScene().getWindow().hide();
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/login.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage= new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        signUpButton.setOnAction(event -> {
            createUser();

        });
    }

    private void createUser() {
        DatabaseHandler dbh= new DatabaseHandler();
        String firstname=signUpFname.getText().trim();
        String lastname= signUpLname.getText().trim();
        String username=signUpUname.getText().trim();
        String password=signUpPassword.getText().trim();
        String location= signUpLocation.getText().trim();
        String gender="";
        if (signUpFcheckbx.isSelected()){gender="Female";}else gender="Male";

        User user= new User(firstname,lastname,username,password,gender,location);
        dbh.signUpUser(user);

    }
}
