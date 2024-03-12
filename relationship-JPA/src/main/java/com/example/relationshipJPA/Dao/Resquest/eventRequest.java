package com.example.relationshipJPA.Dao.Resquest;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class eventRequest {

    private String title;

    private String funcType;

    private Date dateFrom;

    private Date dateTo;
}
