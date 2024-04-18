package com.example.relationshipJPA.repository;



import com.example.relationshipJPA.entity.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByEmail(String email);

    @Query(value = "SELECT email FROM Member", nativeQuery = true)
    String[] findAllEmail();

    @Modifying
    @Transactional
    @Query("update Member u set u.password = ?2 where u.email = ?1")
    void updatePassword(String email, String password);

    @Query(value = "SELECT id FROM Member WHERE NOT role = 'GUARD'", nativeQuery = true)
    List<Long> getAllId();

    @Query(value = "SELECT id FROM Member WHERE email = :email", nativeQuery = true)
    Long findIdByEmail(String email);

}
