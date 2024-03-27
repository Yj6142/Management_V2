package js.managementV2.repository;

import js.managementV2.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {

    List<Item> findByArticleNumContaining(String articleNum);     //article 번호 이용해서 item 찾아오기

    Optional<Item> findByNameContains(String name);     //name 이 들어간 item 객체 모두 찾아오기
}
