package com.xinchao.controllers;

import com.xinchao.endpoints.CompanyInfoApiEndpoints;
import com.xinchao.models.CompanyInfo;
import com.xinchao.security.AdminPermission;
import com.xinchao.services.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping(CompanyInfoApiEndpoints.BASE_URL_COMPANY_INFO)
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
    @AdminPermission
    public ResponseEntity<CompanyInfo> createCompanyInfo(@RequestBody CompanyInfo companyInfo) {
        CompanyInfo createdRegulation = companyInfoService.createCompanyInfo(companyInfo);
        return ResponseEntity.status(201).body(createdRegulation);
    }

    // PUT cập nhật regulation theo ID
    @PutMapping(CompanyInfoApiEndpoints.UPDATE_COMPANY_INFO)
    @AdminPermission
    public ResponseEntity<CompanyInfo> updateCompanyInfo(@PathVariable String id, @RequestBody CompanyInfo companyInfo) {
        CompanyInfo updatedCompanyInfo = companyInfoService.updateCompanyInfo(id, companyInfo);
        return ResponseEntity.ok(updatedCompanyInfo);
    }

    // DELETE xóa regulation theo ID
    @DeleteMapping(CompanyInfoApiEndpoints.DELETE_COMPANY_INFO)
    @AdminPermission
    public ResponseEntity<Void> deleteCompanyInfo(@PathVariable String id) {
        if (companyInfoService.deleteRegulation(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}