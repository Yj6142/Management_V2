package js.managementV2.controller;

import js.managementV2.dto.QuotationListDto;
import js.managementV2.service.QuotationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class QuotationController {

    private final QuotationService quotationService;

    @PostMapping("/quotation/{companyName}")
    public ResponseEntity<String> addQuotation(@RequestBody List<QuotationDto> items, @PathVariable String companyName) {
        try {
            for (QuotationDto quotationDto : items) {
                quotationService.addQuotation(quotationDto.getItemId(), quotationDto.getExPrice(), companyName);
            }
            return ResponseEntity.ok("저장 완료");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("저장에 실패했습니다.");
        }
    }

    @GetMapping("/quotations/{companyName}")
    public Page<QuotationListDto> quotationList(@PathVariable String companyName, @RequestParam(required = false, defaultValue = "1") int page) {
        PageRequest pageable = PageRequest.of(page - 1, 15, Sort.by("id").descending());
        log.info("quotationList controller 실행, companyName = {}", companyName);
        return quotationService.getQuotationList(companyName, pageable);
    }

    @PostMapping("/quotations/search")
    public List<QuotationListDto> searchQuotationList(@RequestBody SearchFormDto searchFormDto) {

        if (searchFormDto.searchOption == 1) {
            return quotationService.searchQuotationByNum(searchFormDto.companyName, searchFormDto.searchData);
        } else {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        }
    }

    @Data
    static class SearchFormDto {
        private int searchOption;
        private String searchData;
        private String companyName;
    }

    @Data
    static class QuotationDto {
        private Long itemId;
        private float exPrice;
    }
}
