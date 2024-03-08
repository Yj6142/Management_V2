package js.managementV2.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quotation_id")
    private Quotation quotation;

    private LocalDate orderDate;
    private int quantity;
    private float profit;

    public Orders(Quotation quotation, LocalDate orderDate, int quantity) {
        this.quotation = quotation;
        this.orderDate = orderDate;
        this.quantity = quantity;
    }

    public Orders() {

    }
}
