package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 컴포넌트스캔에 의해 자동으로 스프링 빈으로 관리
public class MemberRepository {
    @PersistenceContext
    private EntityManager em; // 스프링이 엔티티 매니저를 만들어서 주입시켜 줌

    public void save(Member member) {
        em.persist(member); // 영속성 컨텍스트에 member 객체를 넣는다
    }

    public Member findOne(Long id) {
        // JPA가 제공하는 find 메서드
        // 단건 조회 (type, PK)
        // ctrl + p : Parameter Info
        return em.find(Member.class, id);
    }
    public List<Member> findAll() {

        // ctrl + alt + n : Inline variable

        // jpql : 엔티티 객체 대상
        // sql : 테이블 대상
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        // 파라미터 바인딩 -> 공부하기
        return em.createQuery("select m from Member m where m.name = :named", Member.class)
                .setParameter("named", name)
                .getResultList();
    }
}
