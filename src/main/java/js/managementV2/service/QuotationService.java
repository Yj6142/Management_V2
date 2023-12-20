package js.managementV2.service;

import js.managementV2.domain.Company;
import js.managementV2.domain.Item;
import js.managementV2.dto.SearchItemDto;
import js.managementV2.repository.CompanyRepository;
import js.managementV2.repository.ItemRepository;
import js.managementV2.repository.QuotationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuotationService {

    private final ItemRepository itemRepository;
    private final QuotationRepository quotationRepository;
    private final CompanyRepository companyRepository;


    //제품 찾고 회사 찾아서 exPrice 계산해서 return
    public SearchItemDto findItemByArticleNum(String articleNum, String company) {
        Item findItem = itemRepository.findByArticleNum(articleNum).orElseThrow();
        Company findCom = companyRepository.findByName(company).orElseThrow();

        float defaultDiscount = findCom.getDefaultDiscount();
        float exPrice = findItem.getPrice() / defaultDiscount;

        return new SearchItemDto(findItem.getArticleNum(), findItem.getName(), findItem.getPrice(), exPrice, findCom.getCurrency().getSymbol());

    }

    public List<Company> companyList(){
        return companyRepository.findAll();
    }

}
