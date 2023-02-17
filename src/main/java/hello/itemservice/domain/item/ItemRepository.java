package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository  // Component가 들어있기 때문에 ComponentScan의 대상이 된다.
public class ItemRepository {

    // 데이터베이스와 연동되면 조금 더 스프링에 맞게 코드를 작성하고, 데이터베이스 예외처리를 해주어야 하지만, 간단한 프로젝트이기 때문에 하지 않는다.
    private static final Map<Long, Item> store = new HashMap<>();  // static 사용, 실무는 동시성 문제로 그냥 hashmap 사용하지 않는다. ConcurrentHashMap을 사용해야 한다.
    private static long sequence = 0;      // 스프링 컨테이너 안에서 쓰면 어차피 싱글톤이기 때문에 static 사용 안해도 되지만 따로 new 해서 사용할 경우를 대비해서 static 사용
    // 그냥 long이 아닌 AtomicLong 과 같이 다른것을 사용해야 한다.


    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);

        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    // test 용
    public void clearStore() {
        store.clear();
    }
}
