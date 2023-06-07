package js.management.controller;

import js.management.domian.ItemList;
import js.management.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    //전체 조회
    @GetMapping("/items")
    public String itemList(@ModelAttribute("items")) {
        itemService.findItemList();
    }
}
