package js.management.controller;

import js.management.domian.Item;
import js.management.domian.Orders;
import js.management.domian.OrderStatus;
import js.management.service.ItemService;
import js.management.service.QuotationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class QuotationController {

    private final QuotationService quotationService;
    private final ItemService itemService;

    //quotation 등록
    @GetMapping("/quotation")
    public String selectQuotation(Model model) {

        model.addAttribute("status", "quotation");
        return "selectList/selectCompany";

    }

    //quotation 조회
    @GetMapping("/quotation/{company}")
    public String searchToQuotation(@PathVariable String company,Model model) {

        model.addAttribute("company", company);
        log.info("company = {}", company);

        return "item/searchItem";

    }

    //articleNum 으로 Item을 찾아서 Quotation에 등록하고 해당 company quotation 출력
    @PostMapping("/quotation/add/{company}/{articleNum}")
    public String addQuotation(@PathVariable String company,
                               @PathVariable String articleNum, Model model) {

        Item findItem = itemService.findItemByArticle(articleNum);
        float exPrice = calcExPrice(findItem);
        quotationService.addQuotation(new Orders(OrderStatus.QUOTATION,LocalDate.now(), company, findItem, exPrice));

        List<Orders> quotations = quotationService.findByCompanyQuotation(company);

        model.addAttribute("quotations", quotations);
        model.addAttribute("company", company);

        return "redirect:/quotations/{company}?submit=true";
    }

    //company별 quotation 조회 [submit용, 보관용]
    @GetMapping("/quotations/{company}")
    public String quotationList(@PathVariable String company,
                                @RequestParam(required = false) Boolean submit,
                                Model model) {

        if (submit == null || !submit) {

            List<Orders> quotations = quotationService.findByCompanyQuotation(company);
            quotationToDto(quotations, model);
            return "quotation/quotationList";
        }

        List<Orders> todayQuotations = quotationService.findByRequestDateQuotation(LocalDate.now(), company);
        quotationToDto(todayQuotations, model);
        return "quotation/quotationSubmit";
    }

    @PostMapping("/quotations/{company}")
    public String filterQuotation(@PathVariable String company,
                                  @RequestParam("value") String value,
                                  Model model) {

        Item itemByArticle = itemService.findItemByArticle(value);
        List<Orders> quotationsByArticleNum = quotationService.findByArticleNumQuotation(itemByArticle, company);
        quotationToDto(quotationsByArticleNum, model);

        return "quotation/quotationList";
    }
    private float calcExPrice(Item findItem) {

        float exPrice = findItem.getPrice() / 160;
        return exPrice;

    }

    private void quotationToDto(List<Orders> quotations, Model model) {

        List<QuotationDto> quotationDtoList = new ArrayList<>();

        for (Orders quotation : quotations) {
            QuotationDto quotationDto = new QuotationDto(quotation.getId(), quotation.getCompany(), quotation.getRequestDate(), quotation.getExPrice(), quotation.getItem());
            quotationDtoList.add(quotationDto);
        }

        model.addAttribute("quotations", quotationDtoList);
    }

    @Data
    static class QuotationDto {

        private Long id;
        private String company;
        private LocalDate requestDate;
        private Item item;
        private float exPrice;
        private float profit;
        private int discountRate;

        public QuotationDto(LocalDate requestDate, Item item) {
            this.requestDate = requestDate;
            this.item = item;
        }

        public QuotationDto(Long id, String company, LocalDate requestDate, float exPrice, Item item) {
            this.id = id;
            this.company = company;
            this.requestDate = requestDate;
            this.item = item;
            this.exPrice = exPrice;
            this.profit = this.exPrice * 185 - this.item.getPrice();
        }
    }

}
