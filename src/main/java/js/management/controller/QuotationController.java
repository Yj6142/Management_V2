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
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class QuotationController {

    private final QuotationService quotationService;
    private final ItemService itemService;

    //추가 버튼 클릭 -> Company 입력 창 & discountRate 입력
    @GetMapping("/quotation/add/{articleNum}")
    public String addQuotationForm(@PathVariable Long articleNum, Model model) {

        Item findItem = itemService.findItemByArticle(articleNum);
        QuotationDto quotation = new QuotationDto(LocalDate.now(), findItem);
        model.addAttribute("quotation", quotation);

        return "quotation/addQuotationForm";
    }

    //Company 와 discountRate 설정하고 add 클릭해서 post 방식으로 전송되면 repo에 저장
    @PostMapping("/quotation/add/{articleNum}")
    public String addQuotation(@PathVariable Long articleNum, @RequestParam String company,
                               @RequestParam int discountRate) {

        log.info("company, discountRate = [{}][{}]", company, discountRate);

        Item findItem = itemService.findItemByArticle(articleNum);
        float exPrice = calcExPrice((float) discountRate, findItem);
        quotationService.addQuotation(new Orders(OrderStatus.QUOTATION,LocalDate.now(), company, findItem, exPrice));

        return "redirect:/quotation?submit=true";
    }

    @GetMapping("/quotation")
    public String quotationList(@RequestParam(required = false) Boolean submit, Model model) {
        log.info("param[submit]={}", submit);

        if(submit == null || !submit){

            List<Orders> quotations = quotationService.quotationList();
            quotationToDto(quotations,model);

            return "quotation/quotationList";
        }

        List<Orders> todayQuotation = quotationService.todayQuotation();
        quotationToDto(todayQuotation, model);

        return "quotation/quotationSubmit";
    }

    @PostMapping("/quotation")
    public String filterQuotation(@RequestParam("filter") String filter,
                                  @RequestParam("value") String value,
                                  Model model) {

        if (filter.equals("com")) {

            List<Orders> quotationsByCompany = quotationService.findByCompanyQuotation(value);
            quotationToDto(quotationsByCompany, model);

        } else if (filter.equals("no")) {


            Item itemByArticle = itemService.findItemByArticle(Long.parseLong(value));
            List<Orders> quotationsByArticleNum = quotationService.findByArticleNumQuotation(itemByArticle);
            quotationToDto(quotationsByArticleNum, model);

        }

        return "quotation/quotationList";

    }
    private float calcExPrice(float discountRate, Item findItem) {
        float discount = (1 - discountRate / 100);
        float price = findItem.getPrice() / 160;
        float exPrice = price * discount;
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
