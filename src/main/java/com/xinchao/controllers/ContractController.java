package com.xinchao.controllers;

import com.xinchao.endpoints.ContractApiEndpoints;
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
@RequestMapping(ContractApiEndpoints.BASE_URL_CONTRACT)
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping
    public List<ContractResponse> getAllContracts() {
        return contractService.getAllContracts();
    }
    @CrossOrigin(origins = "*")
    @GetMapping(ContractApiEndpoints.GET_CONTRACT_BY_ID)
    public ResponseEntity<ContractResponse> getContractById(@PathVariable String id) {
        Optional<ContractResponse> contractResponse = contractService.getContractById(id);
        return contractResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @CrossOrigin(origins = "*")
    @PostMapping(ContractApiEndpoints.CREATE_CONTRACT)
    public ResponseEntity<ContractResponse> createContract(@RequestBody ContractRequest contractRequest) {
        ContractResponse contractResponse = contractService.createContract(contractRequest);
        return new ResponseEntity<>(contractResponse, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "*")
    @PutMapping(ContractApiEndpoints.UPDATE_CONTRACT)
    public ResponseEntity<ContractResponse> updateContract(@PathVariable String id, @RequestBody ContractRequest contractRequest) {
        Optional<ContractResponse> contractResponse = contractService.updateContract(id, contractRequest);
        return contractResponse.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping(ContractApiEndpoints.DELETE_CONTRACT)
    public ResponseEntity<Void> deleteContract(@PathVariable String id) {
        boolean deleted = contractService.deleteContract(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping(ContractApiEndpoints.SIGN_CONTRACT_CONFIRM)
    public ResponseEntity<ContractResponse> signContractConfirm(@PathVariable String id) {
        ContractResponse confirmedContract = contractService.signContractConfirm(id);
        return confirmedContract != null ? ResponseEntity.ok(confirmedContract) : ResponseEntity.notFound().build();
    }
}
