package js.managementV2.repository;

import js.managementV2.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(String company);       //company 명 이용해서 company 찾기

}
