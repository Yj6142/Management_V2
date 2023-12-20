package js.managementV2.service;

import jakarta.transaction.Transactional;
import js.managementV2.domain.Company;
import js.managementV2.dto.CompanyFormDto;
import js.managementV2.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> companyList() {
        return companyRepository.findAll();
    }

    public Company addCompany(CompanyFormDto company) {
        Company newCompany = new Company(company.getName(), company.getCurrency(), company.getDefaultDiscount());
        return companyRepository.save(newCompany);
    }

    public Long deleteCompany(Long companyId){
        try {
            companyRepository.deleteById(companyId);
            return companyId;
        } catch(EmptyResultDataAccessException e) {
            log.info("해당 id가 존재하지 않습니다.");
            return null;
        }
    }

    public Company updateCompany(Long id, CompanyFormDto company) {

        Company existingCompany = companyRepository.findById(id).orElse(null);

        //존재하는 id 이면 update 하고, companyId 를 return
        if(existingCompany != null) {
            existingCompany.update(company.getName(), company.getCurrency(), company.getDefaultDiscount());
            return existingCompany;
        } else {
            //존재하지 않는다면
            log.info("해당 id가 존재하지 않습니다.");
            throw new EmptyResultDataAccessException(1);
        }
    }
}
