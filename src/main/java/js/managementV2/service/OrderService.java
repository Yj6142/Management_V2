package js.managementV2.service;

import js.managementV2.domain.Orders;
import js.managementV2.domain.Quotation;
import js.managementV2.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final QuotationService quotationService;

    public void saveOrder(Long quotationId, int quantity) {
        
        //quotationId 이용해서 quotation 찾아오기
        Quotation quotation = quotationService.getQuotationById(quotationId);
        quotation.addOrderTime();
        ordersRepository.save(new Orders(quotation, LocalDate.now(), quantity));

    }
}
