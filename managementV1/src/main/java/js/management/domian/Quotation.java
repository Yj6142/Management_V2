package js.management.domian;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDate;

@Entity
@Getter
public class Quotation {

    @Id @GeneratedValue
    @Column(name = "quotation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Item item;

    private String company;
    private LocalDate requestDate;
    private float exPrice;
    private int orderTime;

    public Quotation() {
    }

    public Quotation(Item item, String company, LocalDate requestDate, float exPrice) {
        this.item = item;
        this.company = company;
        this.requestDate = requestDate;
        this.exPrice = exPrice;
    }
}
