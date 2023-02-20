package edu.limecats.sample.controller;

import edu.limecats.sample.domain.Member;
import edu.limecats.sample.dto.MemberSaveRequestDto;
import edu.limecats.sample.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUser(@RequestBody MemberSaveRequestDto memberSaveRequestDto){
        log.debug("DTO={}",memberSaveRequestDto);
        //DTO는 자주 바뀔 수 있는 요구사항이기 때문에 controller에서 변경
        Member saveMember = Member.builder()
                .username(memberSaveRequestDto.getUsername())
                .email(memberSaveRequestDto.getEmail())
                .password(memberSaveRequestDto.getPassword())
                .build();

        memberService.memberSave(saveMember);
        return "ok";
    }
}
