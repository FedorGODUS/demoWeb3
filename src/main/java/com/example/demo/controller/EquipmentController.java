package com.example.demo.controller;

import com.example.demo.model.Equipment;
import com.example.demo.model.enums.UniqEquipment;
import com.example.demo.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("/mint")
    private ResponseEntity<Equipment> mint(@RequestParam String accountId, @RequestBody UniqEquipment equipment) {
        return new ResponseEntity<>(equipmentService.mint(accountId, equipment), HttpStatus.CREATED);
    }

    @PostMapping("/used")
    private ResponseEntity<Equipment> makeUsed(@RequestParam String accountId, @RequestParam long equipmentId) {
        return new ResponseEntity<>(equipmentService.makeUsed(accountId, equipmentId), HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<List<Equipment>> findAll() {
        return new ResponseEntity<>(equipmentService.getAllEquipment(), HttpStatus.OK);
    }
}
