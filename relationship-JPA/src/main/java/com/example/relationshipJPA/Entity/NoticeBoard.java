package com.example.relationshipJPA.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class NoticeBoard {
    private long noticeId;
    private String title;
    private String description;
    private LocalDate createDate;
}
