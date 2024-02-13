package js.managementV2.repository;

import js.managementV2.domain.Company;
import js.managementV2.domain.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {

    List<Quotation> findByCompany(Company company);
}
