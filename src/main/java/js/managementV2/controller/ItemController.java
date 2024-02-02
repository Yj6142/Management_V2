package js.managementV2.controller;

import js.managementV2.dto.SearchItemDto;
import js.managementV2.service.ItemService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/search")
    public List<SearchItemDto> searchItem(@RequestBody searchFormDto form){
        if(form.searchOption == 1){
            return itemService.findItemByArticleNum(form.companyName, form.searchData);
        } else{
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        }
    }

    @Data
    static class searchFormDto{
        private String companyName;
        private int searchOption;
        private String searchData;
    }
}
