package js.managementV2.controller;

import js.managementV2.dto.OrderListDto;
import js.managementV2.dto.TodayProfitDto;
import js.managementV2.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<String> orderItem(@RequestBody OrderItemDto data) {
        orderService.saveOrder(data.quotationId, data.quantity);
        return ResponseEntity.ok("저장이 완료되었습니다.");
    }

    @GetMapping("/orderList")
    public Page<OrderListDto> getOrderList(@RequestParam(required = false, defaultValue = "1") int page) {
        PageRequest pageable = PageRequest.of(page - 1, 15, Sort.by("orderDate").descending());
        return orderService.getOrderList(pageable);
    }

    @GetMapping("/orders/{companyName}")
    public Page<OrderListDto> getOrderListByCompany(@PathVariable String companyName, @RequestParam(required = false, defaultValue = "1") int page) {
        log.info("companyName={}", companyName);
        PageRequest pageable = PageRequest.of(page - 1, 15, Sort.by("orderDate").descending());
        return orderService.getOrdersByCompany(companyName, pageable);
    }

    @PostMapping("/orders/{companyName}")
    public List<OrderListDto> getOrdersFilteredByDate(@PathVariable String companyName, @RequestBody OrderDateDto orderDateDto) {
        //String 으로 받은 DateTime -> Date 로 나눠서 LocalDate 로 형변환
        LocalDate orderDate = stringToLocalDate(orderDateDto);
        return orderService.getOrdersByOrderDate(orderDate, companyName);
    }

    @GetMapping("/orders/todayProfit")
    public List<TodayProfitDto> getTodayProfit() {
        return orderService.calcProfit();
    }

    @PostMapping("/orders/todayProfit")
    public Map<String, Object> getTodayProfitByOrderDate(@RequestBody OrderDateDto orderDateDto){

        log.info("orderDateDto = {}", orderDateDto);
        LocalDate orderDate = stringToLocalDate(orderDateDto);

        //해당 orderDate 의 회사별 이윤 및 해당 날짜 이윤 가져오기
        List<TodayProfitDto> profitList = orderService.calcTodayProfit(orderDate);
        TodayProfitDto totalProfit = orderService.calcDailyTotalProfit(orderDate);

        Map<String, Object> todayProfitResult = new HashMap<>();
        todayProfitResult.put("totalProfit", totalProfit.getTotalProfit());
        todayProfitResult.put("profitList", profitList);

        log.info("todayProfitResult = {}", todayProfitResult);

        return todayProfitResult;
    }

    private static LocalDate stringToLocalDate(OrderDateDto orderDateDto) {
        String dateTimeToDate = orderDateDto.orderDate.split("T")[0];
        return LocalDate.parse(dateTimeToDate, DateTimeFormatter.ISO_DATE);
    }

    @Data
    static class OrderItemDto {
        private Long quotationId;
        private int quantity;
    }

    @Data
    static class OrderDateDto {
        private String orderDate;
    }
}
