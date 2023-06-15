package js.management.service;

import js.management.domian.OrderStatus;
import js.management.domian.Orders;
import js.management.repository.OrdersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final OrdersRepository ordersRepository;

    //전체 orderList 조회
    public List<Orders> findOrderList() {
        return ordersRepository.findByOrderStatusOrderByIdDesc(OrderStatus.ORDER);
    }

    //id로 조회해오기
    public Orders findQuotation(Long id) {
        return ordersRepository.findById(id).get();
    }

    //업데이트 기능 만들기
    public void updateOrder(Orders order, int quantity) {
        order.updateOrder(OrderStatus.ORDER, LocalDate.now(),quantity);
        log.info("order = [{}][{}][{}]", order.getOrderDate(), order.getOrderStatus(), order.getQuantity());
        ordersRepository.save(order);
    }

    //company 이용해서 orderlist 가져오기
    public List<Orders> findByCompanyOrder(String company) {
        return ordersRepository.findByCompanyAndOrderStatus(company,OrderStatus.ORDER);
    }
}
