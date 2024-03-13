package js.managementV2.service;

import js.managementV2.domain.*;
import js.managementV2.dto.OrderListDto;
import js.managementV2.dto.TodayProfitDto;
import js.managementV2.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final QuotationService quotationService;
    private final ExchangeRateService exchangeRateService;
    private final CompanyService companyService;

    public void saveOrder(Long quotationId, int quantity) {

        //quotationId 이용해서 quotation 찾아오기
        Quotation quotation = quotationService.getQuotationById(quotationId);
        quotation.addOrderTime();

        //quotation 에서 회사정보 찾아서 currencyCode 가져와서 exchangeRate 구하기
        ExchangeRateInfo exchangeRateInfo = exchangeRateService.getExchangeRate(quotation.getCompany().getCurrency());
        float exchangeRate = Float.parseFloat(exchangeRateInfo.getExchangeRate().replaceAll(",", ""));

        //이윤 계산하기 (견적 나간 가격 * 현재 환율 - 원래 물건 가격)
        float profit = (quotation.getExPrice() * exchangeRate) - quotation.getItem().getPrice();
        ordersRepository.save(new Orders(quotation, LocalDate.now(), quantity, profit * quantity));
    }

    public Page<OrderListDto> getOrdersByCompany(String companyName, Pageable pageable) {

        Company company = companyService.findCompanyByName(companyName);
        return ordersRepository.findByQuotation_Company(company, pageable)
                .map(orders -> {
                    Item findItem = orders.getQuotation().getItem();
                    return getOrderListDto(orders, findItem, company);
                });
    }

    public List<OrderListDto> getOrdersByOrderDate(LocalDate orderDate, String companyName) {
        Company company = companyService.findCompanyByName(companyName);
        return ordersRepository.findByOrderDateAndQuotation_Company(orderDate, company).stream()
                .map(orders -> {
                    Item findItem = orders.getQuotation().getItem();
                    return getOrderListDto(orders, findItem, company);
                }).toList();
    }

    public Page<OrderListDto> getOrderList(Pageable pageable) {
        return ordersRepository.findAll(pageable)
                .map(orders -> {
                    Item findItem = orders.getQuotation().getItem();
                    Company company = orders.getQuotation().getCompany();
                    return getOrderListDto(orders, findItem, company);
                });
    }

    public List<TodayProfitDto> calcProfit() {
        return ordersRepository.calcProfitByCompanyAndDate();
    }

    public List<TodayProfitDto> calcTodayProfit(LocalDate orderDate) {
        return ordersRepository.calcTodayProfitByOrderDate(orderDate);
    }

    private static OrderListDto getOrderListDto(Orders orders, Item findItem, Company company) {
        return new OrderListDto(
                orders.getId(),
                orders.getOrderDate(),
                findItem.getArticleNum(),
                findItem.getName(),
                orders.getQuantity(),
                findItem.getPrice(),
                orders.getQuotation().getExPrice(),
                orders.getProfit(),
                company.getName(),
                company.getCurrency().getCurrencyCode()
        );
    }

}
