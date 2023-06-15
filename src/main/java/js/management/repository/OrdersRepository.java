package js.management.repository;

import js.management.domian.Item;
import js.management.domian.OrderStatus;
import js.management.domian.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByRequestDateOrderByIdDesc(LocalDate requestDate);

    List<Orders> findByCompanyAndOrderStatus(String company, OrderStatus status);

    List<Orders> findByItemAndOrderStatus(Item item, OrderStatus status);

    List<Orders> findByOrderStatusOrderByIdDesc(OrderStatus status);

}
