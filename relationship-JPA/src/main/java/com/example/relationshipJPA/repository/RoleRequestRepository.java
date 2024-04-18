package com.example.relationshipJPA.repository;

import com.example.relationshipJPA.entity.RoleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRequestRepository extends JpaRepository<RoleRequest, Long> {

    @Query(value = "SELECT * FROM role_request WHERE status LIKE 'NOT_APPROVED'", nativeQuery = true)
    List<RoleRequest> findUserByStatus();

    @Query(value = "SELECT * FROM role_request WHERE member_id = ? AND status = 'NOT_APPROVED'", nativeQuery = true)
    RoleRequest checkPendingRequestRole(Long id);

}
