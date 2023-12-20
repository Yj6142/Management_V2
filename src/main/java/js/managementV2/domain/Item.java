package js.managementV2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String articleNum;
    private String name;
    private float price;
    private String manufacturer;

    public Item(String articleNum, String name, float price, String manufacturer) {
        this.articleNum = articleNum;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public Item() {

    }
}
