package lk.ijse.gdse.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@Entity
public class Rooms {
    @Id
    private String room_type_id;
    private String type;
    private String key_money;
    private int qty;
    @OneToMany(mappedBy = "room_type_id")
    List<Reservation> studentList = new ArrayList<>();

    public Rooms(String room_type_id, String type, String key_money,int qty){
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
    }

}
