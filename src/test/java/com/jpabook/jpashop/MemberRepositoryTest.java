package com.jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    // 스프링 종속적으로 설계하기 때문에 스프링 어노테이션을 쓰자 ! -> 쓸 수 있는 옵션이 많다.
    // Transactional 일반적으로 잘 동작하지만,
    // @Test에 있으면 DB를 rollback해버린다. -> 데이터가 들어가 있으면 반복적인 테스트를 못하기 때문에
    // ...TransactionContext : Rolled back transaction for test ....
    @Transactional
    // 롤백을 잠깐 false로 설정하자.
    @Rollback(value = false)
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        // 같은 영속성 컨텍스트 안에서는 ID값이(식별자가) 같으면 같은 엔티티로 식별한다.
        // 1차 캐시에서 그냥 꺼내옴.
        Assertions.assertThat(findMember).isEqualTo(member);
    }

}