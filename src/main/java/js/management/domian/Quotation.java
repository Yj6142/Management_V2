package js.management.domian;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Quotation {

    @Id @GeneratedValue
    @Column(name = "Quotation_id")
    private Long id;
    private LocalDate requestDate;
    private String company;
    @ManyToOne
    @JoinColumn(name = "articleNum")
    private Item item;
    private int discountRate;

    public Quotation(LocalDate requestDate, String company, Item item, int discountRate) {
        this.requestDate = requestDate;
        this.company = company;
        this.item = item;
        this.discountRate = discountRate;
    }

    public Quotation() {

    }
}
