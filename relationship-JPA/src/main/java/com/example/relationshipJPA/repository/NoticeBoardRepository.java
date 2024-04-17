package com.example.relationshipJPA.repository;

import com.example.relationshipJPA.entity.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {

}
