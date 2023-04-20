package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class EditProfileFormController {

    public AnchorPane contectEditProfile;

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) contectEditProfile.getScene().getWindow();
        stage.close();

    }
}
