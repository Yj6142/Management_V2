package js.management.controller;

import js.management.domian.Item;
import js.management.domian.Orders;
import js.management.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //전체 orderList 조회
    @GetMapping("/orders")
    public String orderList(Model model) {
        List<Orders> orderList = orderService.findOrderList();
        orderToDto(model, orderList);

        return "order/orderList";
    }

    @PostMapping("/orders")
    public String orderList(@RequestParam("company") String company, Model model) {

        List<Orders> orderByCompany = orderService.findByCompanyOrder(company);
        orderToDto(model, orderByCompany);

        return "order/orderList";
    }

    //addOrderForm
    @GetMapping("/orders/add/{param}")
    public String addOrderForm(@PathVariable("param") Long id, Model model) {

        Orders quotation = orderService.findQuotation(id);

        OrderDto quotationDto = new OrderDto(quotation.getId(), quotation.getCompany(), quotation.getItem(), quotation.getExPrice());

        model.addAttribute("quotation", quotationDto);
        model.addAttribute("orders", new OrderDto());
        return "order/addOrderForm";
    }

    @PostMapping("/orders/add/{param}")
    public String addOrder(@PathVariable("param") Long id,
                           @ModelAttribute OrderDto orderDto, Model model) {

        Orders quotation = orderService.findQuotation(id);
        orderService.updateOrder(quotation, orderDto.quantity);

        return "redirect:/orders";
    }

    private void orderToDto(Model model, List<Orders> orderList) {
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (Orders order : orderList) {
            OrderDto orderDto = new OrderDto(order.getCompany(), order.getOrderDate(), order.getItem(), order.getExPrice(), order.getQuantity());
            orderDtoList.add(orderDto);
        }

        model.addAttribute("orders", orderDtoList);
    }

    @Data
    static class OrderDto {
        private Long id;
        private String company;
        private LocalDate orderDate;
        private Item item;
        private float exPrice;
        private int quantity;
        private float totalPrice;


        public OrderDto(Long id, String company, Item item, float exPrice) {
            this.id = id;
            this.company = company;
            this.item = item;
            this.exPrice = exPrice;
        }

        public OrderDto(String company, LocalDate orderDate, Item item, float exPrice, int quantity) {
            this.company = company;
            this.orderDate = orderDate;
            this.item = item;
            this.exPrice = exPrice;
            this.quantity = quantity;
            this.totalPrice = this.exPrice * this.quantity;
        }

        public OrderDto() {
        }
    }
}
