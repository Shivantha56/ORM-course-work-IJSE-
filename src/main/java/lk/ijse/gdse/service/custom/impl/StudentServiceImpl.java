package lk.ijse.gdse.service.custom.impl;

import lk.ijse.gdse.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse.dto.StudentDTO;
import lk.ijse.gdse.entity.Student;

import java.util.List;

public class StudentServiceImpl {
    StudentDAOImpl studentDAO = new StudentDAOImpl();

    public boolean save(StudentDTO studentDTO) throws Exception {
        studentDAO.save(new Student(studentDTO.getStudent_id(),studentDTO.getName(),studentDTO.getAddress(),
                studentDTO.getContact_no(),studentDTO.getDob(),studentDTO.getGender()
        ));

        return true;
    }

    public StudentDTO search(String phoneNumber) {

        List<Student> studentList = studentDAO.search(phoneNumber);



        return  null;
    }

    public boolean update(StudentDTO studentDTO) throws Exception {
        studentDAO.update(new Student(studentDTO.getStudent_id(),studentDTO.getName(),studentDTO.getAddress(),
                studentDTO.getContact_no(),studentDTO.getDob(),studentDTO.getGender()
        ));
        return true;
    }

    public boolean delete(String id) throws Exception {
        studentDAO.delete(id);
        return true;
    }

    public List<Student> getAll() throws Exception {
        return studentDAO.getAll();
    }
}
