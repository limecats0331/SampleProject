package edu.limecats.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorDto {
    private String errorMsg;
    private String errorName;
}
