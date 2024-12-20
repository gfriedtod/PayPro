package com.example.paypromodulith.mail.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmailRequest {

    private String to;
    private String subject;
    private String message;

}
