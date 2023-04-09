package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.entity.User;
import lk.ijse.gdse.service.custom.impl.UserServiceImpl;
import lk.ijse.gdse.util.Navigation;
import lk.ijse.gdse.util.Routes;


import java.net.URL;


public class LoginFormController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public AnchorPane signUpContext;
    public TextField signupFullName;
    public TextField signUpUserName;
    public TextField singUpPassword;
    public TextField signUpEmail;
    public AnchorPane mainContext;
    public CheckBox checkBoxShowPassword;
    public TextField showPasswordField;


    UserServiceImpl userService = new UserServiceImpl();


    public void clearField(){
        signupFullName.clear();
        signUpUserName.clear();
        signUpEmail.clear();
        singUpPassword.clear();
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String userName = txtUserName.getText();

        try {
         UserDTO  userDTO = userService.search(userName);
            String userName1 = userDTO.getUserName();
            String password = userDTO.getPassword();

            if((txtUserName.getText().equals(userName1)&&txtPassword.getText().equals(password))){
                Stage stage = Navigation.init(mainContext);
                stage.close();
                Navigation.navigate(Routes.DashBoard);
            }

        } catch (NullPointerException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR,"User cannot found").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something happen please try again or contact developer").show();
            throw new RuntimeException(e);
        }

    }

    public void btnSignUpOnAction(ActionEvent actionEvent) {
        signUpContext.setVisible(true);
    }

    public void btnLoginSignupFormOnAction(ActionEvent actionEvent) {
        signUpContext.setVisible(false);
    }

    public void btnSignupInSignFormOnAction(ActionEvent actionEvent) {
        String name = signupFullName.getText();
        String userName = signUpUserName.getText();
        String email = signUpEmail.getText();
        String password = singUpPassword.getText();

        try {
            if (userService.save(new UserDTO(name,userName,email,password))) {
                new Alert(Alert.AlertType.CONFIRMATION, "User saved successful!").show();
                clearField();
            }
        }catch (Exception e){
            System.out.println(e);;
            new Alert(Alert.AlertType.WARNING,"Can not save user please try again..").show();
        }

    }

    public void checkBoxShowPassword(ActionEvent actionEvent) {
        boolean selected = checkBoxShowPassword.isSelected();
        if(selected){
            txtPassword.setVisible(false);
            showPasswordField.setVisible(true);
            showPasswordField.setText(txtPassword.getText());
        }else {
            txtPassword.setVisible(true);
            showPasswordField.setVisible(false);
        }

    }

}

