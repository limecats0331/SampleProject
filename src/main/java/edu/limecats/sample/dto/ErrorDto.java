package edu.limecats.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ErrorDto {
    private String errorMsg;
    private String errorName;
}
