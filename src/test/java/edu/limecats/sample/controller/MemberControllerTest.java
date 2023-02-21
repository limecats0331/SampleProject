package edu.limecats.sample.controller;

import com.google.gson.Gson;
import edu.limecats.sample.dto.ErrorDto;
import edu.limecats.sample.dto.MemberSaveRequestDto;
import edu.limecats.sample.exception.UserNameErrorException;
import edu.limecats.sample.repository.MemberRepository;
import edu.limecats.sample.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //TODO : 찾아서 정리하기
    @SpyBean
    private MemberService memberService;

    //TODO : SpyBean, MockBean 차이 정리하기
    @MockBean
    private MemberRepository memberRepository;
    private Gson gson = new Gson();

    @Test
    public void 유저_저장_컨트롤러() throws Exception {
        //TODO : 얘도 정리하기
        MemberSaveRequestDto inputDto = MemberSaveRequestDto.builder()
                .username("member1")
                .email("member1@member1.com")
                .password("member1")
                .build();
        String content = gson.toJson(inputDto);

        mockMvc.perform(post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andExpect(content().string("ok"))
                .andDo(print());
    }

    @Test
    public void 유저_저장_컨트롤러_에러() throws Exception {
        MemberSaveRequestDto inputDto = MemberSaveRequestDto.builder()
                .username("error")
                .email("member1@member1.com")
                .password("member1")
                .build();
        String content = gson.toJson(inputDto);

        ErrorDto errorDto = ErrorDto.builder()
                .errorName(UserNameErrorException.class.toString())
                .errorMsg("유저이름이 옳지 않습니다.")
                .build();

        mockMvc.perform(post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(gson.toJson(errorDto)))
                .andDo(print());
    }
}