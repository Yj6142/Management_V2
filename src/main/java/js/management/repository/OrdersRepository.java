package js.management.repository;

import js.management.domian.Item;
import js.management.domian.OrderStatus;
import js.management.domian.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByRequestDate(LocalDate requestDate);

    List<Orders> findByCompany(String company);

    List<Orders> findByItem(Item item);

    List<Orders> findByOrderStatus(OrderStatus status);
}
