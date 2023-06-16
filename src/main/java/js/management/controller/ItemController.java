package js.management.controller;

import js.management.domian.Item;
import js.management.service.ItemService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    //item 조회
    @GetMapping ("/search")
    public String itemList(Model model){
        List<Item> items = itemService.findItemList();
        model.addAttribute("items",items);
        return "item/itemList";
    }

    //article No. 을 이용해서 Item 조회
    @PostMapping("/search")
    public String searchItemByArticleNum(@RequestParam("articleNum") String articleNum,
                                         @RequestParam(required = false) String company,
                                         Model model) {

        Item findItem = itemService.findItemByArticle(articleNum);

        searchItemDto searchItemDto = new searchItemDto(findItem.getArticleNum(), findItem.getName(), findItem.getPrice());
        model.addAttribute("item", searchItemDto);

        if (company==null) {
            return "item/itemList";
        }

        model.addAttribute("company", company);
        return "item/searchItem";
    }

    @Data
    static class searchItemDto {
        private String articleNum;
        private String name;
        private float price;
        private String exPrice;

        public searchItemDto(String articleNum, String name, float price) {
            this.articleNum = articleNum;
            this.name = name;
            this.price = price;
            this.exPrice = String.format("%.2f", this.getPrice() / 160);
        }
    }
}
