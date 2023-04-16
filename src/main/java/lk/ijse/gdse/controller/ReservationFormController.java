package lk.ijse.gdse.controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.entity.Rooms;
import lk.ijse.gdse.entity.Student;
import lk.ijse.gdse.service.custom.impl.ReservationServiceImpl;
import lk.ijse.gdse.view.CartTm;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationFormController implements Initializable {

    public ChoiceBox<String> choiceBox;
    public JFXTextField txtStudentPhoneNumber;
    public JFXTextField txtStudentName;
    public JFXTextField roomType;
    public Label lblAvailableRooms;
    public Label lblKeyMoney;
    public JFXTextField txtReservationId;
    public JFXRadioButton paidRadioButton;
    public JFXRadioButton notPaidRadioButton;
    public TableView<CartTm> tblCart;
    public TableColumn colReservation;
    public TableColumn<Object, Object> colStudentId;
    public TableColumn<Object, Object> colStudentName;
    public TableColumn colRoomId;
    public TableColumn colStatus;
    public TableColumn colQty;
    public TableColumn colButton;
    public JFXTextField txtQuantity;

    ReservationServiceImpl reservationService = new ReservationServiceImpl();
    public AnchorPane dashBoardContext;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getRoomsId();
        colReservation.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colButton.setCellValueFactory(new PropertyValueFactory<>("button"));

    }


    public void getRoomsId(){


        try {
            List<Rooms> roomsList = reservationService.getRoomId();

            for (Rooms rooms:roomsList) {

//                ArrayList<Rooms>arrayList = new ArrayList<>();
//                arrayList.add(rooms);

                String roomTypeId = rooms.getRoom_type_id();

                choiceBox.getItems().addAll(roomTypeId);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String txtStudentPhoneNumberOnAction(ActionEvent actionEvent) {
        Student student = reservationService.search(txtStudentPhoneNumber.getText());
        String name = student.getName();
        String studentId = student.getStudent_id();

        txtStudentName.setText(name);

        return studentId;
    }

    ObservableList<CartTm>cartTm = FXCollections.observableArrayList();
    public void addToCartButtonOnAction(ActionEvent actionEvent) {

        String status = null;
        ArrayList<String> strings = searchBtnOnAction();
        Button button = new Button("Delete");

        if (paidRadioButton.isSelected()){
            status = "paid";

        } else if (notPaidRadioButton.isSelected()) {
            status = "not paid";

        }


        String studentId = txtStudentPhoneNumberOnAction(actionEvent);
        String reservationId = txtReservationId.getText();
        String studentName = txtStudentName.getText();
        String qty = txtQuantity.getText();
        System.out.println(strings.get(0));
        System.out.println(strings.get(1));

        CartTm tm = new CartTm(
                reservationId,
                studentId,
                studentName,
                choiceBox.getValue(),
                status,
                qty,
                button
                );

        button.setOnAction(e->{

            for (CartTm tempTm:cartTm
            ) {
                if(tempTm.getStudentId().equals(tm.getStudentId())){
                    cartTm.remove(tempTm);
                }
            }
        });

        cartTm.add(tm);
        tblCart.setItems(cartTm);


    }

    public void searchButtonOnAction(ActionEvent actionEvent) {
        txtStudentPhoneNumberOnAction(actionEvent);
        searchBtnOnAction();



    }

    public ArrayList<String> searchBtnOnAction() {
        String value = choiceBox.getValue();
        Rooms rooms = reservationService.searchByRoomId(value);

        String qty = String.valueOf(rooms.getQty());
        String type = rooms.getType();
        String keyMoney = rooms.getKey_money();

        ArrayList<String>arrayList = new ArrayList<>();
        arrayList.add(qty);
        arrayList.add(value);
        roomType.setText(type);
        lblAvailableRooms.setText(qty);
        lblKeyMoney.setText(keyMoney);

        return arrayList;

    }



}
