package com.grpc.adminsecuity.service;

import com.grpc.adminsecuity.model.Inventory;
import com.grpc.adminsecuity.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;


    public Inventory update(int id, Inventory inventory){

       Inventory updatedInventory = new Inventory();
       updatedInventory.setId(id);
       updatedInventory.setVehicleColor(inventory.getVehicleColor());
       updatedInventory.setVehicleType(inventory.getVehicleType());

       inventoryRepository.save(updatedInventory);

       return updatedInventory;
    }
}
