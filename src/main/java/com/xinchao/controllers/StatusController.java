package com.xinchao.controllers;

import com.xinchao.models.Status;
import com.xinchao.models.StatusEnum;
import com.xinchao.security.AdminPermission;
import com.xinchao.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/statuses")
public class StatusController {

    @Autowired
    private StatusService statusService;
    @CrossOrigin(origins = "*")
    @GetMapping
    @AdminPermission
    public ResponseEntity<List<Status>> getAllStatuses() {
        List<Status> statuses = statusService.getAllStatuses();
        return ResponseEntity.ok(statuses);
    }
    @CrossOrigin(origins = "*")
    @PostMapping
    @AdminPermission
    public ResponseEntity<Status> addStatus(@RequestBody Status status) {
        Status newStatus = statusService.addStatus(status);
        return ResponseEntity.ok(newStatus);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    @AdminPermission
    public ResponseEntity<String> deleteStatus(@PathVariable Integer id) {
        boolean isDeleted = statusService.deleteStatus(id);
        if (isDeleted) {
            return ResponseEntity.ok("Status deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Status not found");
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/name/{name}")
    @AdminPermission
    public ResponseEntity<Status> getStatusByName(@PathVariable StatusEnum name) {
        Optional<Status> status = statusService.getStatusByName(name);
        if (status.isPresent()) {
            return ResponseEntity.ok(status.get());
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
}
