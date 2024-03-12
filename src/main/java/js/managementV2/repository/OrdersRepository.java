package js.managementV2.repository;

import js.managementV2.domain.Company;
import js.managementV2.domain.Orders;
import js.managementV2.dto.TodayProfitDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Page<Orders> findByQuotation_Company(Company company, Pageable pageable);

    @Query("SELECT " +
            "new js.managementV2.dto.TodayProfitDto(o.orderDate, q.company.name, sum(o.profit)) " +
            "FROM Orders o " +
            "INNER JOIN Quotation q on q.id=o.quotation.id " +
            "GROUP BY q.company.id, o.orderDate")
    List<TodayProfitDto> calcProfitByCompanyAndDate();



}
