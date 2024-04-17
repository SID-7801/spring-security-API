package com.example.relationshipJPA.dao.resquest;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Mail {

    private String[] to;

    private String sendto;
    private String subject;

    private String body;
}
