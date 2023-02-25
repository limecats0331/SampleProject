package edu.limecats.sample.repository;

import edu.limecats.sample.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    @SpyBean
    private MemberRepository memberRepository;
    private Member member1;
    private Member member2;

    @BeforeEach
    void init() {
        member1 = Member.builder()
                .username("member1")
                .email("member1@member1.com")
                .password("member1")
                .build();
        member2 = Member.builder()
                .username("member2")
                .email("member2@member2.com")
                .password("member2")
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
    }

    //TODO : 한번에 처리할 수 있는 방법 찾이보기
    @Test
    void 아이디로_맴버_찾기() {
        Optional<Member> findMember1 = memberRepository.findById(this.member1.getId());
//        assertThat(findMember1.get()).isEqualTo(member1);
        assertThat(findMember1.get().getId()).isEqualTo(member1.getId());
        assertThat(findMember1.get().getUsername()).isEqualTo(member1.getUsername());
        assertThat(findMember1.get().getEmail()).isEqualTo(member1.getEmail());
        assertThat(findMember1.get().getPassword()).isEqualTo(member1.getPassword());
    }
}