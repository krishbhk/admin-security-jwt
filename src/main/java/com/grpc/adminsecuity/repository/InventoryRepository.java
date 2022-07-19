package com.grpc.adminsecuity.repository;

import com.grpc.adminsecuity.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Inventory findById(int id);
}
