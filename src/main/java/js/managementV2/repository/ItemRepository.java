package js.managementV2.repository;

import js.managementV2.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {

    Optional<Item> findByArticleNum(String articleNum);     //article 번호 이용해서 item 찾아오기
}
