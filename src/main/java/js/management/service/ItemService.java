package js.management.service;

import js.management.domian.Item;
import js.management.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    //전체 조회
    public List<Item> findItemList() {
        return itemRepository.findAll();
    }

    //하나만 조회
    public Item findItemByArticle(Long articleNum) {
        return itemRepository.findByArticleNum(articleNum);
    }

}
