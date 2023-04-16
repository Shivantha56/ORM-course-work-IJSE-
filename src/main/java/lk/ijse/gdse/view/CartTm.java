package lk.ijse.gdse.view;

import javafx.scene.control.Button;

public class CartTm {

    private String reservationId;
    private String studentId;
    private String studentName;
    private String roomId;
    private String status;
    private String qty;
    private Button button;

    public CartTm(String reservationId, String studentId, String studentName, String roomId, String string, String qty,
                  Button button) {
        this.reservationId = reservationId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.roomId = roomId;
        this.status = string;
        this.qty = qty;
        this.button = button;
    }

    public CartTm() {
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "CartTm{" +
                "reservationId='" + reservationId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", roomId='" + roomId + '\'' +
                ", string='" + status + '\'' +
                ", qty='" + qty + '\'' +
                ", button=" + button +
                '}';
    }

}
