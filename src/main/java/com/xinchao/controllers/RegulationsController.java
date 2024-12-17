package com.xinchao.controllers;


import com.xinchao.models.Regulations;
import com.xinchao.services.RegulationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/regulations")
public class RegulationsController {

    @Autowired
    private RegulationsService regulationsService;

    // GET tất cả regulations
    @GetMapping
    public List<Regulations> getAllRegulations() {
        return regulationsService.getAllRegulations();
    }

    // GET regulation theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Regulations> getRegulationById(@PathVariable String id) {
        Optional<Regulations> regulations = regulationsService.getRegulationById(id);
        return regulations.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST tạo mới regulation
    @PostMapping
    public ResponseEntity<Regulations> createRegulation(@RequestBody Regulations regulations) {
        Regulations createdRegulation = regulationsService.createRegulation(regulations);
        return ResponseEntity.status(201).body(createdRegulation);
    }

    // PUT cập nhật regulation theo ID
    @PutMapping("/{id}")
    public ResponseEntity<Regulations> updateRegulation(@PathVariable String id, @RequestBody Regulations regulations) {
        Regulations updatedRegulation = regulationsService.updateRegulation(id, regulations);
        return ResponseEntity.ok(updatedRegulation);
    }

    // DELETE xóa regulation theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegulation(@PathVariable String id) {
        if (regulationsService.deleteRegulation(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

