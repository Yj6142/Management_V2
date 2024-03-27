package js.managementV2.service;

import js.managementV2.domain.Company;
import js.managementV2.domain.Item;
import js.managementV2.dto.SearchItemDto;
import js.managementV2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CompanyService companyService;

    //Option1. articleNum 이용해서 item 검색하는 경우
    public List<SearchItemDto> findItemByArticleNum(String company, String articleNum) {

        List<Item> findItem = itemRepository.findByArticleNumContaining(articleNum);

        if (findItem.isEmpty()) {
            //만약에 반환된 item 이 하나도 없을 경우 "조회된 아이템이 없습니다" 라고 결과 화면에 출력되도록 설정
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        } else {
            Company findCom = companyService.findCompanyByName(company);
            float defaultDiscount = findCom.getDefaultDiscount();

            //결과로 나온 데이터들 모두 defaultDiscount 적용된 가격으로 나누기 해줘야함.
            return findItem.stream()
                    .map(item -> {
                        float exPrice = item.getPrice() / defaultDiscount;
                        return new SearchItemDto(item.getId(), item.getArticleNum(), item.getName(), item.getPrice(), exPrice, findCom.getCurrency().getCurrencyCode());
                    })
                    .toList();
        }
    }

    public Item findItemById(Long id) {
        return itemRepository.findById(id).orElseThrow();
    }
}
