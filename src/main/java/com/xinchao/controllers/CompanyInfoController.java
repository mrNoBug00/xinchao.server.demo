package com.xinchao.controllers;

import com.xinchao.models.CompanyInfo;
import com.xinchao.services.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/company-info")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoService companyInfoService;

    // GET tất cả regulations
    @GetMapping
    public List<CompanyInfo> getAllCompanyInfo() {
        return companyInfoService.getAllCompanyInfo();
    }


    // POST tạo mới regulation
    @PostMapping
    public ResponseEntity<CompanyInfo> createCompanyInfo(@RequestBody CompanyInfo companyInfo) {
        CompanyInfo createdRegulation = companyInfoService.createCompanyInfo(companyInfo);
        return ResponseEntity.status(201).body(createdRegulation);
    }

    // PUT cập nhật regulation theo ID
    @PutMapping("/{id}")
    public ResponseEntity<CompanyInfo> updateCompanyInfo(@PathVariable String id, @RequestBody CompanyInfo companyInfo) {
        CompanyInfo updatedCompanyInfo = companyInfoService.updateCompanyInfo(id, companyInfo);
        return ResponseEntity.ok(updatedCompanyInfo);
    }

    // DELETE xóa regulation theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompanyInfo(@PathVariable String id) {
        if (companyInfoService.deleteRegulation(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}