package lk.ijse.gdse.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import java.sql.Date;
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    private String res_id;
    @CreationTimestamp
    private Date date;
    private String student_id;
    private String room_type_id;
    private String status;
}
