package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// 기존적으로 데이터 변경은 Transation이 꼭 있어야 한다.
// jpa가 조회하는 곳에서는 성능을 최적화한다. 읽기에는 가급적 readOnly = true 넣기
// 읽기가 아닌 쓰기에는 readOnly X
@Transactional(readOnly = true)
@RequiredArgsConstructor // final이 있는 필드만 가지고 생성자를 만들어 준다.
public class MemberService {
//    @Autowired // 필드 주입
//    private MemberRepository memberRepository; // 스프링이 스프링빈에 등록되어 있는 MemberRepository를 주입시켜준다.

    private final MemberRepository memberRepository; // 수정할 일이 없기에 final로 권장


    /* @RequiredArgsConstructor를 사용하면 생성자를 만들어주기 때문에 따로 작성해 줄 필요 없다.*/

//    @Autowired // 생성자 주입. 생성자가 하나만 있는 경우에는 스프링이 @autowired를 자동으로 걸어준다.
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }


    // 회원 가입
    @Transactional // 우선권을 가짐 default readOnly = false가 먹힘
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        /*
        * jpa에서 em.persist()하면 영속성 컨텍스트에 member객체를 올린다.
        * 그때, 영속성 컨텍스트의 key는 DB PK랑 매핑한 것이 key가 된다. 즉, Member.id
        * id값이 항상 생성되어 있다는 것이 보장을 받는다.
        * db에 들어간 시점이 아니여도 그렇게 해준다.
        * */
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 데이터베이스의 Member의 name을 유니크 제약조건으로 잡아두는 걸 권장 ! (안전)
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            //EXCEPTION
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 단건 조회
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    @Transactional
    // 커맨드랑 쿼리를 철저히 분리해라.
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
