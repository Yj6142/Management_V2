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

import java.util.List;

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

    @GetMapping("/orders/todayProfit")
    public List<TodayProfitDto> getTodayProfit() {
        return orderService.calcTodayProfit();
    }

    @Data
    static class OrderItemDto {
        private Long quotationId;
        private int quantity;
    }
}
