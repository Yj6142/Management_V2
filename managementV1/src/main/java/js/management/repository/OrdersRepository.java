package js.management.repository;

import js.management.domian.Item;
import js.management.domian.OrderStatus;
import js.management.domian.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByRequestDateAndCompany(LocalDate requestDate, String company);

    //회사 & 주문/견적 & 내림차순 정렬
    List<Orders> findByCompanyAndOrderStatusOrderByIdDesc(String company, OrderStatus status);
    //article 번호 & 주문/견적 & 회사
    List<Orders> findByItemAndOrderStatusAndCompany(Item item, OrderStatus status, String company);

    List<Orders> findByOrderStatusOrderByIdDesc(OrderStatus status);

}
