package com.xinchao.controllers;

import com.xinchao.payload.request.ContractRequest;
import com.xinchao.payload.response.ContractResponse;
import com.xinchao.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping
    public List<ContractResponse> getAllContracts() {
        return contractService.getAllContracts();
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<ContractResponse> getContractById(@PathVariable String id) {
        Optional<ContractResponse> contractResponse = contractService.getContractById(id);
        return contractResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/create")
    public ResponseEntity<ContractResponse> createContract(@RequestBody ContractRequest contractRequest) {
        ContractResponse contractResponse = contractService.createContract(contractRequest);
        return new ResponseEntity<>(contractResponse, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<ContractResponse> updateContract(@PathVariable String id, @RequestBody ContractRequest contractRequest) {
        Optional<ContractResponse> contractResponse = contractService.updateContract(id, contractRequest);
        return contractResponse.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable String id) {
        boolean deleted = contractService.deleteContract(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/{id}/signConfirm")
    public ResponseEntity<ContractResponse> signContractConfirm(@PathVariable String id) {
        ContractResponse confirmedContract = contractService.signContractConfirm(id);
        return confirmedContract != null ? ResponseEntity.ok(confirmedContract) : ResponseEntity.notFound().build();
    }
}
