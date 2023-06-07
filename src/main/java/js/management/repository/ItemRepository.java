package js.management.repository;

import js.management.domian.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByArticleNum(Long articleNum);
}
