package com.kdu.smarthome.controller;


import com.kdu.smarthome.request.InventoryRequestDTO;
import com.kdu.smarthome.response.InventoryResponse;
import com.kdu.smarthome.response.InventoryResponseDTOForPOST;
import com.kdu.smarthome.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService)
    {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public InventoryResponse getInventory()
    {
        return inventoryService.getInventory();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InventoryResponseDTOForPOST> addItemToInventory(@RequestBody InventoryRequestDTO requestDTO)
    {
        InventoryResponseDTOForPOST response = inventoryService.addItemToInventory(requestDTO);
        return ResponseEntity.ok(response);
    }
}
