package lk.ijse.gdse.service.custom.impl;

import lk.ijse.gdse.dao.custom.impl.RoomsDAOImpl;
import lk.ijse.gdse.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse.dto.RoomsDTO;
import lk.ijse.gdse.dto.StudentDTO;
import lk.ijse.gdse.entity.Rooms;
import lk.ijse.gdse.entity.Student;

import java.util.List;

public class ReservationServiceImpl {

    RoomsDAOImpl roomsDAO = new RoomsDAOImpl();

    StudentDAOImpl studentDAO = new StudentDAOImpl();

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


}
