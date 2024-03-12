package js.managementV2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.query.Order;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
public class Quotation {

    @Id
    @GeneratedValue
    @Column(name = "quotation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    private LocalDate requestDate;
    private float exPrice;
    private int orderTime;

    public Quotation() {
    }

    public Quotation(Item item, Company company, LocalDate requestDate, float exPrice) {
        this.item = item;
        this.company = company;
        this.requestDate = requestDate;
        this.exPrice = exPrice;
    }

    public void addOrderTime() {
        this.orderTime++;
    }


}
