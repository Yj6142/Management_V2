package js.management.domian;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Orders {

    @Id @GeneratedValue
    @Column(name = "Quotation_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; //견적의 경우에는 QUOTATION , 주문의 경우에는 ORDER로 바뀐다.
    private LocalDate requestDate;
    private LocalDate orderDate;
    private String company;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleNum")
    private Item item;
    private float exPrice;
    private int quantity;

    public Orders(OrderStatus orderStatus, LocalDate requestDate, String company, Item item, float exPrice) {
        this.orderStatus = orderStatus;
        this.requestDate = requestDate;
        this.company = company;
        this.item = item;
        this.exPrice = exPrice;
    }

    public Orders() {

    }
}
