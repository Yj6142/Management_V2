package js.managementV2.service;

import js.managementV2.domain.Company;
import js.managementV2.domain.Item;
import js.managementV2.domain.Quotation;
import js.managementV2.dto.QuotationListDto;
import js.managementV2.repository.CompanyRepository;
import js.managementV2.repository.ItemRepository;
import js.managementV2.repository.QuotationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuotationService {

    private final ItemRepository itemRepository;
    private final CompanyRepository companyRepository;
    private final QuotationRepository quotationRepository;

    public void addQuotation(Long itemId, float exPrice, String companyName) {
        Item item = itemRepository.findById(itemId).orElseThrow();
        Company company = getCompanyByName(companyName);

        log.info("item={}, company={}, exPrice={}", item, company, exPrice);
        quotationRepository.save(new Quotation(item, company, LocalDate.now(), exPrice));
    }

    public Page<QuotationListDto> getQuotationList(String companyName, Pageable pageable) {

        Company company = getCompanyByName(companyName);

        Page<Quotation> quotationByCompany = quotationRepository.findByCompany(company, pageable);
        return getQuotationListDto(quotationByCompany);
    }

    public List<QuotationListDto> searchQuotationByNum(String companyName, String searchData) {

        Company company = getCompanyByName(companyName);

        List<Quotation> quotationsByNum = quotationRepository.findByCompanyAndItem_ArticleNumContaining(company, searchData);
        return quotationsByNum.stream().map(quotation -> new QuotationListDto(
                quotation.getId(), quotation.getRequestDate(), quotation.getItem().getArticleNum(),
                quotation.getItem().getName(), quotation.getExPrice(), quotation.getCompany().getCurrency().getCurrencyCode(),
                quotation.getItem().getPrice(), quotation.getOrderTime()
        )).toList();
    }

    private static Page<QuotationListDto> getQuotationListDto(Page<Quotation> quotations) {
        return quotations
                .map(quotation -> new QuotationListDto(
                        quotation.getId(), quotation.getRequestDate(), quotation.getItem().getArticleNum(),
                        quotation.getItem().getName(), quotation.getExPrice(), quotation.getCompany().getCurrency().getCurrencyCode(),
                        quotation.getItem().getPrice(), quotation.getOrderTime()
                ));
    }

    private Company getCompanyByName(String companyName) {
        return companyRepository.findByName(companyName).orElseThrow();
    }
}
