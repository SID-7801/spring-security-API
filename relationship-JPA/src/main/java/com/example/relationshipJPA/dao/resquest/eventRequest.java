package com.example.relationshipJPA.dao.resquest;

import lombok.Data;

import java.util.Date;

@Data
public class eventRequest {

    private String title;

    private String funcType;

    private Date dateFrom;

    private Date dateTo;
}
