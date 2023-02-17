package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping    // /basic/items에 get으로 오면 이 메서드가 실행된다.
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);      // 스프링 저장소 같은 곳에 넣는 것이다. (스프링 기본편에 있는 내용)

        return "basic/items";       // 이 경로로 들어간다.
    }


    @GetMapping("/{itemId}")  // /basic/items/{itemId} 로 연결된다.
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);      // model 에서 추가해주고

        return "basic/item";      // view 로 간다.
    }



    /**
     * 테스트용 데이터 추가
     */

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
