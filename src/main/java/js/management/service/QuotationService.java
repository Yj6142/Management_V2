package js.management.service;

import js.management.domian.Item;
import js.management.domian.Quotation;
import js.management.repository.ItemRepository;
import js.management.repository.QuotationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class QuotationService {

    private final QuotationRepository quotationRepository;

    //오늘 quotation 조회
    public List<Quotation> todayQuotation() {
        return quotationRepository.findByRequestDate(LocalDate.now());
    }

    //회사 별 quotation 조회
    public List<Quotation> findByCompanyQuotation(String company) {
        return quotationRepository.findByCompany(company);
    }

    //article 번호 이용 Quotation 만들어서 저장하기
    public Quotation addQuotation(String company,Item item, int discountRate) {
        Quotation quotation = new Quotation(LocalDate.now(), company, item, discountRate);
        return quotationRepository.save(quotation);
    }

    //Quotation 전체 조회
    public List<Quotation> quotationList() {
        return quotationRepository.findAll();
    }

}
