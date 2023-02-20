package edu.limecats.sample.service;

import edu.limecats.sample.domain.Member;
import edu.limecats.sample.exception.UserNameErrorException;
import edu.limecats.sample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member memberSave(Member member) {
        String username = member.getUsername();
        if (username.equals("error")) {
            throw new UserNameErrorException("유저이름이 옳지 않습니다.");
        }

        return memberRepository.save(member);
    }
}
