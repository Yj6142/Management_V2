package js.management.repository;

import js.management.domian.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {

    List<Quotation> findByRequestDate(LocalDate requestDate);

    List<Quotation> findByCompany(String company);
}
