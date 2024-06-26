package com.example.relationshipJPA.dao.resquest;

import com.example.relationshipJPA.entity.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class ComplainRequest {
    private Long comid;
    @NotEmpty(message = "invalid title")
    private String title;
    @NotEmpty(message = "invalid description")
    private String description;
    private String status;
    private LocalDateTime complaintDate;
    private MultipartFile photo;
    private Member mem_id;
}
