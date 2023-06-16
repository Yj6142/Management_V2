package js.management.service;

import js.management.domian.Item;
import js.management.domian.OrderStatus;
import js.management.domian.Orders;
import js.management.repository.OrdersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class QuotationService {

    private final OrdersRepository ordersRepository;

    //회사 별 quotation 조회
    public List<Orders> findByCompanyQuotation(String company) {
        return ordersRepository.findByCompanyAndOrderStatusOrderByIdDesc(company,OrderStatus.QUOTATION);
    }

    //ArticleNum -> quotation 조회
    public List<Orders> findByArticleNumQuotation(Item item, String company) {
        return ordersRepository.findByItemAndOrderStatusAndCompany(item, OrderStatus.QUOTATION, company);
    }

    //article 번호 이용 Quotation 만들어서 저장하기
    public Orders addQuotation(Orders orders) {
        return ordersRepository.save(orders);
    }

    //company별 오늘 quotation만 나오게
    public List<Orders> findByRequestDateQuotation(LocalDate requestDate, String company) {
        return ordersRepository.findByRequestDateAndCompany(requestDate, company);
    }


}
