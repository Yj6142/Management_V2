package js.managementV2;

import jakarta.annotation.PostConstruct;
import js.managementV2.domain.Company;
import js.managementV2.domain.Item;
import js.managementV2.domain.Orders;
import js.managementV2.domain.Quotation;
import js.managementV2.repository.CompanyRepository;
import js.managementV2.repository.ItemRepository;
import js.managementV2.repository.OrdersRepository;
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
    private final OrdersRepository ordersRepository;

    @PostConstruct
    public void init(){

        Company exCompany = new Company("CNC", Currency.getInstance(Locale.CHINA), 160);
        companyRepository.save(exCompany);
        companyRepository.save(new Company("KS", Currency.getInstance(Locale.CHINA), 160));
        companyRepository.save(new Company("HME", Currency.getInstance(Locale.US), 1300));


        for (int i = 0; i < 10; i++) {
            Item item = new Item(Integer.toString(i), "skdjflksdf", 23842, "ceratize");
            itemRepository.save(item);
            quotationRepository.save(new Quotation(item, exCompany, LocalDate.now(), item.getPrice() / exCompany.getDefaultDiscount()));
        }

        ordersRepository.save(new Orders(quotationRepository.findById(1L).orElseThrow(), LocalDate.of(2024, 3, 3), 30, 344257));
        ordersRepository.save(new Orders(quotationRepository.findById(1L).orElseThrow(), LocalDate.of(2024, 3, 4), 30, 344257));
        ordersRepository.save(new Orders(quotationRepository.findById(2L).orElseThrow(), LocalDate.of(2024, 3, 4), 50, 230341));
        ordersRepository.save(new Orders(quotationRepository.findById(2L).orElseThrow(), LocalDate.of(2024, 3, 3), 50, 424234));
        ordersRepository.save(new Orders(quotationRepository.findById(2L).orElseThrow(), LocalDate.of(2024, 3, 3), 50, 503482));
    }
}
