package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.service.custom.impl.StudentServiceImpl;

public class ManagedStudentFormController {

    public TextField txtStudentId;
    public TextField txtStudentName;
    public TextField txtAddress;
    public TextField txtContactNo;
    public DatePicker txtGender;
    public RadioButton radioBtnMale;
    public ToggleGroup radioBtnGender;
    public RadioButton radioBtnFemale;

    StudentServiceImpl studentService = new StudentServiceImpl();

    public void saveBtnOnAction(ActionEvent actionEvent) {

        studentService.save();

    }
}
