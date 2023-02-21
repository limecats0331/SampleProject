package edu.limecats.sample.service;

import edu.limecats.sample.domain.Member;
import edu.limecats.sample.exception.UserNameErrorException;
import edu.limecats.sample.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @Mock
    MemberRepository memberRepository;
    MemberService memberService;

    @Test
    void 멤버_저장_일반() {
        Member member1 = Member.builder()
                .username("member1")
                .email("member1@mameber1.com")
                .password("member1")
                .build();
        Member returnMember = Member.builder()
                .Id(1L)
                .username("member1")
                .email("member1@mameber1.com")
                .password("member1")
                .build();

        doReturn(returnMember).when(memberRepository).save(member1);
        memberService = new MemberService(memberRepository);

        Member resultMember = memberService.memberSave(member1);

        assertThat(resultMember).isEqualTo(returnMember);
    }

    @Test
    void 멤버_저장_오류() {
        memberService = new MemberService(memberRepository);
        Member errorMember = Member.builder()
                .username("error")
                .email("error@error.com")
                .password("error")
                .build();

        assertThatThrownBy(() -> memberService.memberSave(errorMember))
                .isInstanceOf(UserNameErrorException.class);
    }

}