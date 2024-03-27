package js.managementV2.repository;

import js.managementV2.domain.Company;
import js.managementV2.domain.Quotation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuotationRepository extends JpaRepository<Quotation, Long> {

    Page<Quotation> findByCompany(Company company, Pageable pageable);

    List<Quotation> findByCompanyAndItem_ArticleNumContaining(Company company, String articleNum);

}
