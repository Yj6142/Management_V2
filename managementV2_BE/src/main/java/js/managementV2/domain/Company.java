package js.managementV2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Currency;

@Entity
@Getter
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private Long id;
    private String name;
    private Currency currency;
    private float defaultDiscount;

    public Company(String name, Currency currency, float defaultDiscount) {
        this.name = name;
        this.currency = currency;
        this.defaultDiscount = defaultDiscount;
    }

    public Company() {
    }

    public void update(String name, Currency currency, float defaultDiscount) {
        this.name = name;
        this.currency = currency;
        this.defaultDiscount = defaultDiscount;
    }
}
