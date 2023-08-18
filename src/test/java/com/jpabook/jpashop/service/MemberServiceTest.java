package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // junit 실행할 때 spring이랑 엮어서 실행할래 !
@SpringBootTest // springboot를 띄운 상태로 test를 하기 위해 필요한 어노테이션
@Transactional // @Transactional 어노테이션이 테스트케이스에 있으면 rollback 해버림 => insert 쿼리문이 안보일 것이다.
// rollback 해버리면 jpa 입장에서는 insert 쿼리를 db에 날리지 않음.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em; // db에 쿼리남기는 것을 보고싶다면

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        //given  이런 이런 것들이 주어졌을 때
        Member member = new Member();
        member.setName("kim");

        //when 이걸 실행하면
        Long savdId = memberService.join(member);

        //then 결과가 이게 나와야 해 요런 느낌 !

        // db에 쿼리남기는 것을 보고싶다면 flush 해주기.
        // flush를 하면 db에 강제로 쿼리가 나간다. -> 스프링 테스트가 끝나면 @Transactional이 rollback을 해버림 -> db에 저장되지 않고 확인은 할 수 있다.
        // em.flush();
        Assertions.assertEquals(member, memberRepository.findOne(savdId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);
//        try {
//            memberService.join(member2); // 예외가 발생해야 한다.
//        } catch (IllegalStateException e) {
//            return;
//        }

        //then
        Assertions.fail("예외가 발생해야 합니다. !!!");
    }

}