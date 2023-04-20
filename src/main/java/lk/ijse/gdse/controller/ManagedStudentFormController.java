package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.gdse.dto.StudentDTO;
import lk.ijse.gdse.entity.Student;
import lk.ijse.gdse.service.custom.impl.StudentServiceImpl;
import lk.ijse.gdse.util.Validate;

import java.net.URL;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManagedStudentFormController implements Initializable {

    public TextField txtStudentId;
    public TextField txtStudentName;
    public TextField txtAddress;
    public TextField txtContactNo;
    public RadioButton radioBtnMale;
    public ToggleGroup radioBtnGender;
    public RadioButton radioBtnFemale;
    public DatePicker dateDOB;
    public TextField txtSearch;
    public TableView <Student> tblStudent;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colAddress;
    public TableColumn colContactNo;
    public TableColumn colGender;
    public TableColumn colDob;
    public Button btnSave;
    public Button btnUpdateStudent;

    StudentServiceImpl studentService = new StudentServiceImpl();
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Pattern namePattern = Pattern.compile("(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})");
        Pattern address = Pattern.compile("^[A-z0-9 ,/]{4,20}$\"");
        Pattern StudentId = Pattern.compile("^(S00-)[0-9]{3,10}$");
        Pattern telNumberPattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");

//        (RM-)[0-9]{1,10}$

        map.put(txtStudentId,StudentId);
        map.put(txtContactNo,telNumberPattern);



        btnSave.setOnMouseClicked(event -> {

            saveCustomer();
        });

        btnUpdateStudent.setOnMouseClicked(event -> {
            //updateCustomer();
            updateStudentOnAction(new ActionEvent());
        });

        btnSave.setDisable(true);
        btnUpdateStudent.setDisable(true);

        setTableValue();

    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Validate.validate(map,btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = Validate.validate(map, btnSave);

            ;
            //if the response is a text field
            //that means there is a error
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();// if there is a error just focus it
            } else if (response instanceof Boolean) {
                System.out.println("Work");
                saveCustomer();
//                updateCustomer();
            }
        }
    }

    private void saveCustomer() {

        String gender;
        if (radioBtnFemale.isSelected()) {
            gender = "FeMale";
        } else {
            gender = "Male";
        }

        String studentId = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtAddress.getText();
        String contactNo = txtContactNo.getText();
        Date date = Date.valueOf(dateDOB.getValue());

        try {
            boolean save = studentService.save(new StudentDTO(studentId, name, address, contactNo, date, gender));
            if (save) {
                new Alert(Alert.AlertType.CONFIRMATION, "User saved successful!").show();

            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "User can not saved").show();

            }

            tblStudent.refresh();

        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something happen please try again or contact developer").show();
        }

        setTableValue();


    }

    public void setBorders(TextField... textFields){
        for (TextField textField : textFields) {
            textField.getParent().setStyle("-fx-border-color: rgba(76, 73, 73, 0.83)");
        }
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {

        String gender;
        if (radioBtnFemale.isSelected()) {
            gender = "FeMale";
        } else {
            gender = "Male";
        }

        String studentId = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtAddress.getText();
        String contactNo = txtContactNo.getText();
        Date date = Date.valueOf(dateDOB.getValue());

        try {
            boolean save = studentService.save(new StudentDTO(studentId, name, address, contactNo, date, gender));
            if (save) {
                new Alert(Alert.AlertType.CONFIRMATION, "User saved successful!").show();

            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "User can not saved").show();

            }

            tblStudent.refresh();

        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something happen please try again or contact developer").show();
        }

        setTableValue();

    }

    public void updateStudentOnAction(ActionEvent actionEvent) {
        String gender;
        if (radioBtnFemale.isSelected()) {
            gender = "FeMale";
        } else {
            gender = "Male";
        }

        String studentId = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtAddress.getText();
        String contactNo = txtContactNo.getText();
        Date date = Date.valueOf(dateDOB.getValue());

        try {
            boolean student = studentService.update(new StudentDTO(studentId, name, address, contactNo, date, gender));
            if (student) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Update successful!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "User can not update").show();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something happen please try again or contact developer").show();
        }
    }

    public void deleteStudentOnAction(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.WARNING, "do you want delete Student");
        alert.show();

        try {
            boolean delete = studentService.delete(txtStudentId.getText());
            if (delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student delete successful!").show();
            } else {

                new Alert(Alert.AlertType.CONFIRMATION, "User can not delete").show();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something happen please try again or contact developer").show();
        }

        setTableValue();

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


        if (Objects.equals(gender, "Male")) {
            radioBtnMale.setSelected(true);
        } else if (Objects.equals(gender, "FeMale")) {
            radioBtnFemale.setSelected(true);
        }
    }


    public void getAll(){

        ObservableList<Student> obList = FXCollections.observableArrayList();
        try {
            List<Student> studentList = studentService.getAll();

            for (Student student:studentList) {

                obList.add(new Student(
                        student.getStudent_id(),
                        student.getName(),
                        student.getAddress(),
                        student.getContact_no(),
                        student.getDob(),
                        student.getGender()
                        ));

            }
            tblStudent.setItems(obList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void setTableValue(){
        getAll();
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

    }

}
