package js.managementV2.controller;

import js.managementV2.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<String> orderItem(@RequestBody OrderItemDto data) {

        orderService.saveOrder(data.quotationId, data.quantity);
        return ResponseEntity.ok("저장이 완료되었습니다.");
    }

    @Data
    static class OrderItemDto {
        private Long quotationId;
        private int quantity;
    }
}
