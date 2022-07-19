package com.grpc.adminsecuity.controller;

import com.grpc.adminsecuity.model.Inventory;
import com.grpc.adminsecuity.repository.InventoryRepository;
import com.grpc.adminsecuity.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/admin/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getall")
    public List<Inventory> getAllInventory(){
        List<Inventory> allInventory = new ArrayList<>(inventoryRepository.findAll());

        return allInventory;

    }

    @PostMapping("/addvehicle")
    public ResponseEntity<Inventory> save(@RequestBody Inventory inventory){
        inventoryRepository.save(inventory);

        return new ResponseEntity<>(inventory, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Inventory> deleteById(@PathVariable int id){
        inventoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Inventory> updateById(@PathVariable int id, @RequestBody Inventory inventory){
        Inventory updatedInventory = inventoryService.update(id,inventory);

        return new ResponseEntity<>(updatedInventory, HttpStatus.ACCEPTED);
    }
}
