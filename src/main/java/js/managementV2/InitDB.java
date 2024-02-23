package js.managementV2;

import jakarta.annotation.PostConstruct;
import js.managementV2.domain.Company;
import js.managementV2.domain.Item;
import js.managementV2.domain.Quotation;
import js.managementV2.repository.CompanyRepository;
import js.managementV2.repository.ItemRepository;
import js.managementV2.repository.QuotationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final CompanyRepository companyRepository;
    private final ItemRepository itemRepository;
    private final QuotationRepository quotationRepository;

    @PostConstruct
    public void init(){

        Company exCompany = new Company("CNC", Currency.getInstance(Locale.CHINA), 160);
        companyRepository.save(exCompany);
        companyRepository.save(new Company("KS", Currency.getInstance(Locale.CHINA), 160));
        companyRepository.save(new Company("HME", Currency.getInstance(Locale.US), 1300));


        for (int i = 0; i < 100; i++) {
            Item item = new Item(Integer.toString(i), "skdjflksdf", 23842, "ceratize");
            itemRepository.save(item);
            quotationRepository.save(new Quotation(item, exCompany, LocalDate.now(), item.getPrice() / exCompany.getDefaultDiscount()));
        }
    }
}
