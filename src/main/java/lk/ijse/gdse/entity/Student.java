package lk.ijse.gdse.entity;

import lombok.*;
import org.hibernate.annotations.AttributeAccessor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@Entity
public class Student {
    @Id
    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private Date dob;
    private String gender;


}
