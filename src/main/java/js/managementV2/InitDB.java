package js.managementV2;

import jakarta.annotation.PostConstruct;
import js.managementV2.domain.Company;
import js.managementV2.domain.Item;
import js.managementV2.repository.CompanyRepository;
import js.managementV2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final CompanyRepository companyRepository;
    private final ItemRepository itemRepository;

    @PostConstruct
    public void init(){

        companyRepository.save(new Company("cnc", Currency.getInstance(Locale.CHINA), 160));
        companyRepository.save(new Company("ks", Currency.getInstance(Locale.CHINA), 160));
        companyRepository.save(new Company("hme", Currency.getInstance(Locale.US), 1300));

        itemRepository.save(new Item("10293812", "dkfjqeri84712942", 12000, "Ceratize"));

    }
}
