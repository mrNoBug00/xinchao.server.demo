package com.xinchao.services;

import com.xinchao.models.CompanyInfo;
import com.xinchao.models.Regulations;
import com.xinchao.repository.CompanyInfoRepository;
import com.xinchao.repository.RegulationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyInfoService {

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    public List<CompanyInfo> getAllCompanyInfo() {
        return companyInfoRepository.findAll();
    }


    public CompanyInfo createCompanyInfo(CompanyInfo companyInfo) {
        return companyInfoRepository.save(companyInfo);
    }

    public CompanyInfo updateCompanyInfo(String id, CompanyInfo companyInfo) {
        companyInfo.setId(id);
        return companyInfoRepository.save(companyInfo);
    }

    public boolean deleteRegulation(String id) {
        if (companyInfoRepository.existsById(id)) {
            companyInfoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
