package edu.limecats.sample.repository;

import edu.limecats.sample.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
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


    @Test
    void 아이디로_맴버_찾기() {
        Optional<Member> findMember1 = memberRepository.findById(this.member1.getId());
        Assertions.assertThat(findMember1.get()).isEqualTo(member1);
    }
}