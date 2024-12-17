package com.xinchao.services;

import com.xinchao.models.*;
import com.xinchao.payload.request.ContractRequest;
import com.xinchao.payload.response.ContractResponse;
import com.xinchao.payload.response.ImageDTO;
import com.xinchao.payload.response.ProductDTO;
import com.xinchao.payload.response.UserDTO;
import com.xinchao.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Autowired
    private RegulationsRepository regulationsRepository;

    public List<ContractResponse> getAllContracts() {
        List<Contract> contracts = contractRepository.findAll();
        return contracts.stream()
                .map(this::mapToContractResponse)
                .collect(Collectors.toList());
    }


    public Optional<ContractResponse> getContractById(String id) {
        return contractRepository.findById(id).map(this::mapToContractResponse);
    }

    public ContractResponse createContract(ContractRequest contractRequest) {

        Optional<Product> optionalProduct = productRepository.findById(contractRequest.getProductId());
        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("Product not found");
        }
        Optional<Status> optionalStatus = statusRepository.findByName(StatusEnum.PENDING);

        Contract contract = new Contract();
        contract.setStatus(optionalStatus.orElseThrow(() -> new RuntimeException("Status not found")));
        setContractFields(contract, contractRequest);
        Contract savedContract = contractRepository.save(contract);
        return mapToContractResponse(savedContract);
    }

    public Optional<ContractResponse> updateContract(String id, ContractRequest contractRequest) {
        return contractRepository.findById(id).map(contract -> {
            setContractFields(contract, contractRequest);
            Contract updatedContract = contractRepository.save(contract);
            return mapToContractResponse(updatedContract);
        });
    }

    public boolean deleteContract(String id) {
        if (contractRepository.existsById(id)) {
            contractRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void setContractFields(Contract contract, ContractRequest contractRequest) {

        // user info
        contract.setUser(userRepository.findById(contractRequest.getUserId()).orElse(null));


        List<Image> identificationCards = contractRequest.getCustomerIdentificationCardIds().stream()
                .map(id -> imageRepository.findById(id).orElse(null))
                .collect(Collectors.toList());

        Image signatureImage = imageRepository.findById(contractRequest.getSignatureId()).orElse(null);
        contract.setSignatureId(signatureImage);

        // customer identification
        contract.setCustomerIdentificationCardIds(identificationCards);
        contract.setIdentificationId(contractRequest.getCustomerIdentificationId());



        //company info
        contract.setCompanyInfo(companyInfoRepository.findById(contractRequest.getCompanyId()).orElse(null));

        //customer info
        contract.setCustomerName(contract.getCustomerName());
        contract.setPhone(contractRequest.getCustomerPhone());
        contract.setCustomerLine(contractRequest.getCustomerLine());
        contract.setCustomerZalo(contractRequest.getCustomerZalo());

        //guarantor info
        contract.setGuarantorName(contractRequest.getGuarantorName());
        contract.setGuarantorPhone(contractRequest.getGuarantorPhone());
        contract.setGuarantorLine(contractRequest.getGuarantorLine());
        contract.setGuarantorZalo(contract.getGuarantorZalo());

        //time of rent
        contract.setRentTimeFrom(contractRequest.getRentTimeFrom());
        contract.setRentTimeTo(contractRequest.getRentTimeTo());

        //product info
        contract.setProduct(productRepository.findById(contractRequest.getProductId()).orElse(null));
        contract.setProductType(contractRequest.getProductType());

        //Equipment Provided By The Lessor
        contract.setEquipmentProvidedByTheLessor(contractRequest.getEquipmentProvidedByTheLessor());

        //contract info
        contract.setNumberOfRenter(contractRequest.getNumberOfRenter());
        contract.setRentFee(contractRequest.getRentFee());
        contract.setDayOfPayRentFee(contractRequest.getDayOfPayRentFee());
        contract.setElectricityFee(contractRequest.getElectricityFee());
        contract.setWaterFee(contractRequest.getWaterFee());
        contract.setTenancyDeposit(contractRequest.getTenancyDeposit());


        List<String> regulationsIds = contractRequest.getRegulationsId();
        List<Regulations> regulations = regulationsRepository.findAllByIdIn(regulationsIds);
        contract.setRegulations(regulations);


        contract.setAgree(contractRequest.getAgree());
        contract.setCreateAt(LocalDateTime.now());
    }

    private ContractResponse mapToContractResponse(Contract contract) {
        ContractResponse response = new ContractResponse();

        response.setId(contract.getId());
        response.setUser(mapToUserDTO(contract.getUser()));
        response.setCompanyInfo(contract.getCompanyInfo()); // Mapping for CompanyInfo
        response.setIdentificationCard(contract.getCustomerIdentificationCardIds().stream()
                .map(this::mapToImageDTO)
                .collect(Collectors.toList()));
        response.setCustomerName(contract.getCustomerName()); // Updated field mapping
        response.setIdentificationId(contract.getIdentificationId());
        response.setPhone(contract.getPhone());
        response.setCustomerLine(contract.getCustomerLine());
        response.setCustomerZalo(contract.getCustomerZalo());
        response.setGuarantorName(contract.getGuarantorName());
        response.setGuarantorPhone(contract.getGuarantorPhone());
        response.setGuarantorLine(contract.getGuarantorLine());
        response.setGuarantorZalo(contract.getGuarantorZalo());
        response.setRentTimeFrom(contract.getRentTimeFrom());
        response.setRentTimeTo(contract.getRentTimeTo());
        response.setProduct(mapToProductDTO(contract.getProduct()));
        response.setProductType(contract.getProductType());
        response.setEquipmentProvidedByTheLessor(contract.getEquipmentProvidedByTheLessor());
        response.setNumberOfRenter(contract.getNumberOfRenter());
        response.setRentFee(contract.getRentFee());
        response.setDayOfPayRentFee(contract.getDayOfPayRentFee());
        response.setElectricityFee(contract.getElectricityFee());
        response.setWaterFee(contract.getWaterFee());
        response.setTenancyDeposit(contract.getTenancyDeposit());
        response.setRegulations(contract.getRegulations());
        response.setAgree(contract.getAgree());
        response.setSignature(mapToImageDTO(contract.getSignatureId()));
        response.setStatus(contract.getStatus());
        response.setCreateTime(contract.getCreateAt());

        return response;
    }

    private UserDTO mapToUserDTO(User user) {
        if (user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getUserName());
        return dto;
    }

    private ImageDTO mapToImageDTO(Image image) {
        if (image == null) return null;
        ImageDTO dto = new ImageDTO();
        dto.setId(image.getId());
        dto.setImageUrl(image.getImageUrl());
        return dto;
    }

    private ProductDTO mapToProductDTO(Product product) {
        if (product == null) return null;
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        return dto;
    }
    public ContractResponse signContractConfirm(String id) {
        Optional<Contract> optionalContract = contractRepository.findById(id);

        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();
            Optional<Status> optionalStatus = statusRepository.findByName(StatusEnum.CONFIRMED);

            // Assuming you have a method to get the PENDING status
            Status pendingStatus = statusRepository.findByName(StatusEnum.PENDING).orElseThrow(() -> new RuntimeException("Pending status not found"));

            if (pendingStatus.equals(contract.getStatus())) {
                contract.setStatus(optionalStatus.orElseThrow(() -> new RuntimeException("Status not found")));
                Contract savedContract = contractRepository.save(contract);
                return mapToContractResponse(savedContract);
            } else {
                throw new RuntimeException("Contract is not in pending status");
            }
        }
        throw new RuntimeException("Contract not found");
    }



}