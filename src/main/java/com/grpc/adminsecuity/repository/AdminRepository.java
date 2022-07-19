package com.grpc.adminsecuity.repository;

import com.grpc.adminsecuity.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByAdminname(String adminnname);
}
