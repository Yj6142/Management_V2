package js.managementV2.controller;

import js.managementV2.domain.Company;
import js.managementV2.dto.CompanyFormDto;
import js.managementV2.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/company")
    public List<Company> companyList() {
        return companyService.companyList();
    }

    @PostMapping("/company/add")
    public Company addCompany(@RequestBody CompanyFormDto company) {
        return companyService.addCompany(company);
    }

    @GetMapping("/company/delete")
    public Long deleteCompany(@RequestParam Long id) {
        log.info("id = {}", id);
        return companyService.deleteCompany(id);
    }

    @PostMapping("/company/update")
    public Company updateCompany(@RequestParam Long id, @RequestBody CompanyFormDto company){
        //id에 해당하는 company 를 찾아서 그 company 정보를 뒤집어 씌우기
        return companyService.updateCompany(id, company);
    }

}
