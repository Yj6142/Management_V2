package js.management.repository;

import js.management.domian.ItemList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemList, Long> {

}
