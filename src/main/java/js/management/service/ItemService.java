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
    public Item findItemByArticle(String articleNum) {
        return itemRepository.findByArticleNum(articleNum);
    }

    //이름 일부 들어있나 조회 후 return
    public List<Item> findItemsByName(String name) {
        return itemRepository.findByNameContaining(name);
    }



}
