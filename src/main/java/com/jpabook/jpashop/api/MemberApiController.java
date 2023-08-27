package com.jpabook.jpashop.api;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

// @Controller @ResponseBody
@RestController // 데이터 자체를 바로 json이나 xml로 보내자.
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    // Member 클래스의 name에 직접적으로 @NotEmpty를 적용했을 때의 문제점
    /*
     * 1. Member.name이 꼭 NotEmpty가 아닌 경우도 존재할 수 있다.
     * 2. Api 스펙 자체가 바뀌어버린 경우 문제가 된다. -> String name => String username으로 수정함
     *
     * => 엔티티를 그대로 쓰면 안된다!! 별도의 DTO를 만들어라.
     *
     * API를 만들 때는 엔티티를 파라미터로 받지 마라.
     * 엔티티를 외부에 노출해선 안된다.
     * */

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        // 엔티티 내부에서 값이 바뀌면 에러를 띄워준다. 그러므로 api 내부에선 전혀 영향을 받지 않게 된다.
        /*
        * ex) Member.name 이름을 username으로 변경하면 member.setUsername으로만 바꿔서 실행해주면 된다.
        * */
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    @Data
    static class CreateMemberRequest {
        private String name;
    }
    @Data
    private class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
