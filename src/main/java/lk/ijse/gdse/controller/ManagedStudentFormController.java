package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.gdse.dto.StudentDTO;
import lk.ijse.gdse.service.custom.impl.StudentServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class ManagedStudentFormController {

    public TextField txtStudentId;
    public TextField txtStudentName;
    public TextField txtAddress;
    public TextField txtContactNo;
    public RadioButton radioBtnMale;
    public ToggleGroup radioBtnGender;
    public RadioButton radioBtnFemale;
    public DatePicker dateDOB;
    public TextField txtSearch;

    StudentServiceImpl studentService = new StudentServiceImpl();

    public void saveBtnOnAction(ActionEvent actionEvent) {

        String gender;
        if (radioBtnFemale.isSelected()){
            gender = "FeMale";
        }else{
            gender = "Male";
        }

        String studentId = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtAddress.getText();
        String contactNo = txtContactNo.getText();
        Date date = Date.valueOf(dateDOB.getValue());

        try {
            boolean save = studentService.save(new StudentDTO(studentId, name, address, contactNo, date, gender));
            if (save){
                new Alert(Alert.AlertType.CONFIRMATION, "User saved successful!").show();

            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "User can not saved").show();

            }

        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something happen please try again or contact developer").show();
        }

    }

    public void updateStudentOnAction(ActionEvent actionEvent) {
        String gender;
        if (radioBtnFemale.isSelected()){
            gender = "FeMale";
        }else{
            gender = "Male";
        }

        String studentId = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtAddress.getText();
        String contactNo = txtContactNo.getText();
        Date date = Date.valueOf(dateDOB.getValue());

        try {
            boolean student = studentService.update(new StudentDTO(studentId, name, address, contactNo, date, gender));
            if (student){
                new Alert(Alert.AlertType.CONFIRMATION, "User Update successful!").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "User can not update").show();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something happen please try again or contact developer").show();
        }
    }

    public void deleteStudentOnAction(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.WARNING,"do you want delete Student");
        alert.show();

        try {
            boolean delete = studentService.delete(txtStudentId.getText());
            if (delete){
                new Alert(Alert.AlertType.CONFIRMATION, "Student delete successful!").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "User can not delete").show();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something happen please try again or contact developer").show();
        }
    }

    public void searchBtnOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = studentService.search(txtSearch.getText());
        String studentId = studentDTO.getStudent_id();
        String name = studentDTO.getName();
        String address = studentDTO.getAddress();
        String contactNo = studentDTO.getContact_no();
        String gender = studentDTO.getGender();



        txtStudentId.setText(studentId);
        txtStudentName.setText(name);
        txtAddress.setText(address);
        txtContactNo.setText(contactNo);



        if (Objects.equals(gender, "Male")){
            radioBtnMale.setSelected(true);
        }else if (Objects.equals(gender, "FeMale")){
            radioBtnFemale.setSelected(true);
        }
    }
}
