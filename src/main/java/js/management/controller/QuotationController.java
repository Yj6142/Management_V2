package js.management.controller;

import js.management.domian.Item;
import js.management.domian.Quotation;
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
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class QuotationController {

    private final QuotationService quotationService;
    private final ItemService itemService;

    //추가 버튼 클릭 -> quotationRepo에 저장 & Company 입력 창
    @GetMapping("/quotation/add/{articleNum}")
    public String addQuotationForm(@PathVariable Long articleNum, Model model) {

        Item findItem = itemService.findItemByArticle(articleNum);
        QuotationDto quotation = new QuotationDto(LocalDate.now(), findItem);
        model.addAttribute("quotation", quotation);

        return "quotation/addQuotationForm";
    }

    //Company 와 discountRate 설정하고 add 클릭해서 post 방식으로 전송되면 repo에 저장
    @PostMapping("/quotation/add/{articleNum}")
    public String addQuotation(@PathVariable Long articleNum, @RequestParam String company, int discountRate) {
        Item findItem = itemService.findItemByArticle(articleNum);
        quotationService.addQuotation(company, findItem, discountRate);
        return "homePage";
    }

    @GetMapping("/quotation")
    public String quotationList(Model model) {

        List<Quotation> quotations = quotationService.quotationList();
        List<QuotationDto> quotationDtoList = new ArrayList<>();

        for (Quotation quotation : quotations) {
            QuotationDto quotationDto = new QuotationDto(quotation.getId(), quotation.getCompany(), quotation.getRequestDate(), quotation.getItem());
            quotationDtoList.add(quotationDto);
        }

        model.addAttribute("quotations", quotationDtoList);
        return "quotation/quotationList";
    }

    @Data
    static class QuotationDto {

        private Long id;
        private String company;
        private LocalDate requestDate;
        private Item item;
        private String exPrice;
        private String profit;
        private int discountRate;

        public QuotationDto(LocalDate requestDate, Item item) {
            this.requestDate = requestDate;
            this.item = item;
        }

        public QuotationDto(Long id, String company, LocalDate requestDate, Item item) {
            this.id = id;
            this.company = company;
            this.requestDate = requestDate;
            this.item = item;
            this.exPrice = String.format("%.2f", (float) this.item.getPrice() / 165);
            float calProfit = Float.parseFloat(this.exPrice) * 185 - this.item.getPrice();
            this.profit = String.format("%.2f", calProfit);
        }
    }

}
