package js.management.domian;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private Long articleNum;
    private String name;
    private int price;

}
