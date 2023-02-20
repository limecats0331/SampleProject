package edu.limecats.sample.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveRequestDto {
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
