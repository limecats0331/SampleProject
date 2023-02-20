package edu.limecats.sample.controller;

import com.google.gson.Gson;
import edu.limecats.sample.dto.ErrorDto;
import edu.limecats.sample.dto.MemberSaveRequestDto;
import edu.limecats.sample.exception.UserNameErrorException;
import edu.limecats.sample.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    @MockBean
    private MemberService memberService;
    private Gson gson = new Gson();

    @Test
    public void 유저_저장_컨트롤러() throws Exception {
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
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(gson.toJson(errorDto)))
                .andDo(print());
    }
}