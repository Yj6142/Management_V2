package js.managementV2.controller;

import js.managementV2.dto.SearchItemDto;
import js.managementV2.service.QuotationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QuotationController {

    private final QuotationService quotationService;

    @PostMapping("/search")
    public SearchItemDto searchItem(@RequestBody searchDto data) {
        //articleNum 이랑 회사정보 넘어오게 되면 item 찾고 회사 정보 찾아서 exPrice 계산후에 item 정보 return
        return quotationService.findItemByArticleNum(data.getArticleNum(), data.getCompany());
    }


    @Data
    static class searchDto{
        private String articleNum;
        private String company;
    }
}
