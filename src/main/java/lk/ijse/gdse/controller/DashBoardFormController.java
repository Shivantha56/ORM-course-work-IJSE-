package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.util.Navigation;
import lk.ijse.gdse.util.Routes;

import java.io.IOException;

public class DashBoardFormController {

    public AnchorPane dashBoardContext;
    public Button btnManageStudent;


    public void btnManageStudentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ManageStudent,dashBoardContext);

    }
}
