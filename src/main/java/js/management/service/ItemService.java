package js.management.service;

import js.management.domian.ItemList;
import js.management.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    //전체 조회
    public List<ItemList> findItemList() {
        return itemRepository.findAll();
    }
}
