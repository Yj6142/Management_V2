package js.management.service;

import js.management.domian.Item;
import js.management.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
