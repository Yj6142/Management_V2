package js.management.repository;

import js.management.domian.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, String> {

    Item findByArticleNum(String articleNum);

    List<Item> findByNameContaining(String name);
}
