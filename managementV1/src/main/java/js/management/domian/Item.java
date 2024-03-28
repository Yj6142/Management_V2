package js.management.domian;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Item {

    @Id
    private String articleNum;
    private String name;
    private float price;

}
