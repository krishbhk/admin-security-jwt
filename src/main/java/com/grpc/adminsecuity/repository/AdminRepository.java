package com.grpc.adminsecuity.repository;

import com.grpc.adminsecuity.model.AdminDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminDao, Integer> {
    AdminDao findByAdminname(String adminnname);
}
