package com.ll.shop.repository;

import com.ll.shop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
// ctrl + F6 : rename
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item) {
        // 값이 없을 때 신규 등록
        if (item.getId() == null) {
            em.persist(item);
        } else {
            // 값이 있으니 업데이트 해주는 -> 나중에 다시 보기
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
