package com.xinchao.services;

import com.xinchao.dto.ProductDTO;
import com.xinchao.exception.ImageSaveException;
import com.xinchao.exception.UserNotFoundException;
import com.xinchao.models.*;
import com.xinchao.payload.request.ProductRequest;
import com.xinchao.payload.response.ProductResponse;
import com.xinchao.payload.response.UserResponse;
import com.xinchao.repository.ProductRepository;
import com.xinchao.repository.StatusRepository;
import com.xinchao.repository.CompanyInfoRepository;
import com.xinchao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Value("${product.upload.dir}")
    private String uploadDir;

//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductById(String id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            ProductDTO productDTO = new ProductDTO(
                    product.getName(),
                    product.getType(),
                    product.getPrice(),
                    product.getElectricityFee(),
                    product.getWaterFee(),
                    product.getGasFee(),
                    product.getNumberOfTenantsByRoomRate(),
                    product.getAddress()
            );
            return Optional.of(productDTO);
        } else {
            return Optional.empty();
        }
    }

    public ProductResponse createProduct(ProductRequest productRequest, MultipartFile[] imageUrl, String userId, String companyInfoId) throws IOException {
        Optional<Status> optionalStatus = statusRepository.findByName(StatusEnum.FOR_RENT);
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setType(productRequest.getType());
        product.setPrice(productRequest.getPrice());
        product.setElectricityFee(productRequest.getElectricityFee());
        product.setWaterFee(productRequest.getWaterFee());
        product.setGasFee(productRequest.getGasFee());
        product.setNumberOfTenantsByRoomRate(productRequest.getNumberOfTenantsByRoomRate());
        product.setAddress(productRequest.getAddress());
        product.setDescription(productRequest.getDescription());
        product.setStatus(optionalStatus.orElseThrow(() -> new RuntimeException("Status not found")));

        List<Image> savedImages = new ArrayList<>();
        for (MultipartFile image : imageUrl) {
            ImageInfo imageInfo = saveImage(image);
            Image savedImage = new Image();
            savedImage.setImageUrl(imageInfo.getUniqueFileName());
            savedImage.setImagePath(String.valueOf(imageInfo.getFilePath()));
            savedImage.setProduct(product);
            savedImages.add(savedImage);
        }
        product.setImageUrl(savedImages);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
        product.setAuthor(user);

        // Gán CompanyInfo
        CompanyInfo companyInfo = companyInfoRepository.findById(companyInfoId)
                .orElseThrow(() -> new RuntimeException("CompanyInfo with ID " + companyInfoId + " not found"));
        product.setCompanyInfo(companyInfo);
        Product savedProduct = productRepository.save(product);

        return mapToProductResponse(savedProduct);
    }


    private ProductResponse mapToProductResponse(Product product) {
        UserResponse authorResponse = mapToUserResponse(product.getAuthor());

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getType(),
                product.getDescription(),
                product.getStatus(),
                product.getPrice(),
                product.getElectricityFee(),
                product.getWaterFee(),
                product.getGasFee(),
                product.getNumberOfTenantsByRoomRate(),
                product.getAddress(),
                product.getImageUrl(),
                authorResponse,
                product.getCompanyInfo()
        );
    }

    private UserResponse mapToUserResponse(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getArc(),
                user.getVnId(),
                user.getPassportNumber(),
                user.getRole(),
                user.getLineId(),
                user.getNumberZalo()
        );
    }

    public ProductResponse updateProduct(String id, ProductRequest productRequest, MultipartFile[] imageUrl) throws IOException {

        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Optional<Status> optionalStatus = statusRepository.findById(productRequest.getStatusId());
        if (!optionalStatus.isPresent()) {
            throw new IllegalArgumentException("Status not found");
        }

        // Cập nhật thông tin sản phẩm từ ProductRequest
        product.setName(productRequest.getName());
        product.setType(productRequest.getType());
        product.setPrice(productRequest.getPrice());
        product.setElectricityFee(productRequest.getElectricityFee());
        product.setWaterFee(productRequest.getWaterFee());
        product.setGasFee(productRequest.getGasFee());
        product.setNumberOfTenantsByRoomRate(productRequest.getNumberOfTenantsByRoomRate());
        product.setDescription(productRequest.getDescription());
        product.setAddress(productRequest.getAddress());
        product.setStatus(optionalStatus.get());

        List<Image> newImagesList = new ArrayList<>();
        for (MultipartFile image : imageUrl) {
            ImageInfo imageInfo = saveImage(image);
            Image newImage = new Image();
            newImage.setImageUrl(imageInfo.getUniqueFileName());
            newImage.setImagePath(String.valueOf(imageInfo.getFilePath()));
            newImage.setProduct(product);
            newImagesList.add(newImage);
        }

        product.getImageUrl().clear();
        product.getImageUrl().addAll(newImagesList);


        Product savedProduct = productRepository.save(product);


        return mapToProductResponse(savedProduct);
    }


    public boolean deleteProduct(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false; // Product not found
        }
    }

    public class ImageInfo {
        private String uniqueFileName;
        private Path filePath;

        public ImageInfo(String uniqueFileName, Path filePath) {
            this.uniqueFileName = uniqueFileName;
            this.filePath = filePath;
        }

        public String getUniqueFileName() {
            return uniqueFileName;
        }

        public Path getFilePath() {
            return filePath;
        }
    }

    private ImageInfo saveImage(MultipartFile file) throws IOException {
        try {
            String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + uniqueFileName);
            Files.copy(file.getInputStream(), filePath);
            return new ImageInfo(uniqueFileName, filePath);
        } catch (IOException e) {
            throw new ImageSaveException("Failed to save image: " + file.getOriginalFilename(), e);
        }
    }

}
