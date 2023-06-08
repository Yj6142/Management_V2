package js.management.domian;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Item {

    @Id
    private Long articleNum;
    private String name;
    private int price;

}
