package com.example.relationshipJPA.Entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Mail {

    private String[] to;
    private String subject;

    private String body;
}
