package lk.ijse.gdse.entity;

import lombok.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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
    @ManyToOne
    private Student student_id;
    @ManyToOne
    private Rooms room_type_id;
    private String status;


    public Reservation(String reservationId, Student student, Rooms rooms, String status) {
        this.res_id = reservationId;
        this.student_id = student;
        this.room_type_id = rooms;
        this.status = status;
    }
}

