package lk.ijse.gdse.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Navigation {

    public static Stage init(AnchorPane anchorPane){
        Stage stage =(Stage)anchorPane.getScene().getWindow();
        return stage;
    }

    public static void navigate(Routes routes)throws IOException {

        Stage stage = new Stage();
        URL resource = null;
        switch (routes){
            case DashBoard:
                 resource = Navigation.class.getResource("/view/DashBoardForm.fxml");
                 break;
            case Home:
                break;
        }



        Parent load = FXMLLoader.load(resource);
        stage.setScene(new Scene(load));
        stage.show();



    }

}
