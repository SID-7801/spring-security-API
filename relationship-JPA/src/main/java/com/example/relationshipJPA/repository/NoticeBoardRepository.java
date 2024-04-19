package com.example.relationshipJPA.repository;

import com.example.relationshipJPA.entity.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {

    @Query(value = "SELECT * FROM notice_board WHERE create_date = :curDate", nativeQuery = true)
    List<NoticeBoard> findNoticeOfTheDay(LocalDate curDate);

}
