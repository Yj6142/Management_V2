package js.management.repository;

import js.management.domian.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotationRepository extends JpaRepository<Quotation,Long> {
}
