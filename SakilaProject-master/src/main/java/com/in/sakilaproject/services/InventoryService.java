package com.in.sakilaproject.services;

import com.in.sakilaproject.entities.Inventory;
import com.in.sakilaproject.respositories.InventoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAllInventory(){
        return inventoryRepository.findAll();
    }

    public Inventory getInventoriesById(Integer id){
        return inventoryRepository.getInventoriesByInventoryId(id);
    }

    @Transactional
    public void deleteInventoryItemById(Integer id){
        inventoryRepository.deleteInventoryByInventoryId(id);
    }

    public Integer getInventoryCount(){
        return inventoryRepository.getInventoryCount();
    }

}
