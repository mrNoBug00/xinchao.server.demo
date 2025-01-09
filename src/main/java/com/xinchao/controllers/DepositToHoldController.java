package com.xinchao.controllers;

import com.xinchao.endpoints.DepositToHoldEnpoints;
import com.xinchao.models.DepositToHold;
import com.xinchao.payload.request.DepositToHoldRequest;
import com.xinchao.payload.response.DepositToHoldResponse;
import com.xinchao.security.AdminPermission;
import com.xinchao.security.AllRolePermission;
import com.xinchao.services.DepositToHoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DepositToHoldEnpoints.BASE_URL_DEPOSIT_TO_HOLD)
public class DepositToHoldController {

    @Autowired
    private DepositToHoldService service;

    // Create
    @AllRolePermission
    @PostMapping(DepositToHoldEnpoints.CREATE_DEPOSIT)
    public ResponseEntity<DepositToHoldResponse> create(@RequestBody DepositToHoldRequest request) {
        DepositToHoldResponse createdDeposit = service.create(request);
        return ResponseEntity.ok(createdDeposit);
    }

    // Read All
    @GetMapping
    @AdminPermission
    public ResponseEntity<List<DepositToHoldResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // Read By ID
    @GetMapping(DepositToHoldEnpoints.GET_DEPOSIT_BY_ID)
    @AdminPermission
    public ResponseEntity<DepositToHoldResponse> getById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping(DepositToHoldEnpoints.UPDATE_DEPOSIT)
    @AdminPermission
    public ResponseEntity<DepositToHoldResponse> update(@PathVariable String id, @RequestBody DepositToHoldRequest request) {
        try {
            DepositToHoldResponse updatedDeposit = service.update(id, request);
            return ResponseEntity.ok(updatedDeposit);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete
    @DeleteMapping(DepositToHoldEnpoints.DELETE_DEPOSIT)
    @AdminPermission
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    @AdminPermission
    public List<DepositToHoldResponse> getDepositsByIsViewed(@RequestParam Boolean isViewed) {
        return service.getDepositsByIsViewed(isViewed);
    }
}
