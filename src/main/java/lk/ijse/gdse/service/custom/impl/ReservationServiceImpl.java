package lk.ijse.gdse.service.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.gdse.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.gdse.dao.custom.impl.RoomsDAOImpl;
import lk.ijse.gdse.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse.dto.RoomsDTO;
import lk.ijse.gdse.dto.StudentDTO;
import lk.ijse.gdse.entity.Reservation;
import lk.ijse.gdse.entity.Rooms;
import lk.ijse.gdse.entity.Student;
import lk.ijse.gdse.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl {

    RoomsDAOImpl roomsDAO = new RoomsDAOImpl();

    StudentDAOImpl studentDAO = new StudentDAOImpl();

    ReservationDAOImpl reservationDAO = new ReservationDAOImpl();

    public List<Rooms> getRoomId() {
        return roomsDAO.getAll();
    }

    public Student search(String phoneNumber) {

        List<Student> studentList = studentDAO.search(phoneNumber);

        for (Student student:studentList) {
            return student;
        }

        return null;

    }

    public Rooms searchByRoomId(String roomId) {

        Rooms rooms = roomsDAO.searchByRoomId(roomId);

        return rooms;
    }




    public void save(String reservationId,String status,String phoneNumber,String roomId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student getstudent = search(phoneNumber);
        Student student = new Student(getstudent.getStudent_id(),getstudent.getName(),getstudent.getAddress(),getstudent.getContact_no(),getstudent.getDob(),getstudent.getGender());

        Rooms getRooms = searchByRoomId(roomId);
        Rooms rooms = new Rooms(getRooms.getRoom_type_id(),getRooms.getType(),getRooms.getKey_money(),getRooms.getQty());

//        ArrayList<Student> students = new ArrayList<>();


        try {

            boolean save = reservationDAO.save(new Reservation(reservationId, student, rooms, status));
            if(save){
                transaction.commit();
                new Alert(Alert.AlertType.CONFIRMATION,"Reservation Saved").show();
            }else {
                transaction.rollback();
                new Alert(Alert.AlertType.CONFIRMATION,"Not saved").show();
            }
        } catch (Exception e) {
            System.out.println(e);

        }


    }
}
