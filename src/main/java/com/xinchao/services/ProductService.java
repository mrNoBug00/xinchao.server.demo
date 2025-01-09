package com.xinchao.services;

import com.xinchao.dto.ProductDTO;
import com.xinchao.exception.ImageSaveException;
import com.xinchao.exception.UserNotFoundException;
import com.xinchao.models.*;
import com.xinchao.payload.request.ProductRequest;
import com.xinchao.payload.response.ProductResponse;
import com.xinchao.payload.response.UserResponse;
import com.xinchao.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private CategoryRepository categoryRepository;
    @Autowired
    private CompanyInfoRepository companyInfoRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private DepositToHoldRepository depositToHoldRepository;

    @Value("${product.upload.dir}")
    private String uploadDir;

//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    public ProductService(ProductRepository productRepository, ImageRepository imageRepository, DepositToHoldRepository depositToHoldRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
        this.depositToHoldRepository = depositToHoldRepository;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponse> getProductById(String id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return Optional.of(mapToProductResponse(product));  // Sử dụng mapToProductResponse thay vì ProductDTO
        } else {
            return Optional.empty();
        }
    }


    public ProductResponse createProduct(ProductRequest productRequest, MultipartFile[] imageUrl, String userId, String companyInfoId) throws IOException {
        Optional<Status> optionalStatus = statusRepository.findByName(StatusEnum.FOR_RENT);
        Optional<Category> optionalCategory = categoryRepository.findById(productRequest.getCategory());

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setCategory(optionalCategory.orElseThrow(() -> new RuntimeException("Category not found")));
        product.setPrice(productRequest.getPrice());
        product.setElectricityFee(productRequest.getElectricityFee());
        product.setWaterFee(productRequest.getWaterFee());
        product.setGasFee(productRequest.getGasFee());
        product.setNumberOfTenantsByRoomRate(productRequest.getNumberOfTenantsByRoomRate());
        product.setCity(productRequest.getCity());
        product.setArea(productRequest.getArea());
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
                product.getCategory(),
                product.getDescription(),
                product.getStatus(),
                product.getPrice(),
                product.getElectricityFee(),
                product.getWaterFee(),
                product.getGasFee(),
                product.getNumberOfTenantsByRoomRate(),
                product.getCity(),
                product.getArea(),
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

    public ProductResponse updateProduct(String id, ProductRequest productRequest, MultipartFile[] imageUrl, String userId, String companyInfoId) throws IOException {

        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Optional<Status> optionalStatus = statusRepository.findById(productRequest.getStatusId());
        Optional<Category> optionalCategory = categoryRepository.findById(productRequest.getCategory());

        if (!optionalStatus.isPresent()) {
            throw new IllegalArgumentException("Status not found");
        }

        // Cập nhật thông tin sản phẩm từ ProductRequest
        product.setName(productRequest.getName());
        product.setCategory(optionalCategory.orElseThrow(() -> new RuntimeException("Category not found")));
        product.setPrice(productRequest.getPrice());
        product.setElectricityFee(productRequest.getElectricityFee());
        product.setWaterFee(productRequest.getWaterFee());
        product.setGasFee(productRequest.getGasFee());
        product.setNumberOfTenantsByRoomRate(productRequest.getNumberOfTenantsByRoomRate());
        product.setDescription(productRequest.getDescription());
        product.setCity(productRequest.getCity());
        product.setArea(productRequest.getArea());
        product.setStatus(optionalStatus.get());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
        product.setAuthor(user);

        // Gán CompanyInfo
        CompanyInfo companyInfo = companyInfoRepository.findById(companyInfoId)
                .orElseThrow(() -> new RuntimeException("CompanyInfo with ID " + companyInfoId + " not found"));
        product.setCompanyInfo(companyInfo);

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


    @Transactional
    public boolean deleteProduct(String productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            // Lấy danh sách các DepositToHold liên quan đến sản phẩm
            List<DepositToHold> depositToHolds = depositToHoldRepository.findByProductId(productId);

            // 1. Xóa tất cả các hình ảnh liên quan đến từng DepositToHold
            depositToHolds.forEach(depositToHold -> {
                imageRepository.deleteByDepositToHoldId(depositToHold.getId());
            });

            // 2. Xóa các bản ghi DepositToHold
            depositToHoldRepository.deleteAll(depositToHolds);

            // 3. Xóa sản phẩm
            productRepository.deleteById(productId);

            return true;
        } else {
            return false; // Sản phẩm không tồn tại
        }
    }








    public List<ProductResponse> getProductsByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<Product> products = productRepository.findByCategory(category);

        return products.stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
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

    public List<ProductResponse> getProductsByStatus(StatusEnum statusEnum) {
        List<Product> products = productRepository.findByStatus_Name(statusEnum); // Tìm sản phẩm theo tên trạng thái
        return products.stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public Page<ProductResponse> getPaginatedProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        // Sử dụng Deferred Joins qua @EntityGraph hoặc JOIN FETCH
        Page<Product> productPage = productRepository.findAllWithCategory(pageable);

        // Chuyển đổi sang ProductResponse
        return productPage.map(this::mapToProductResponse);
    }


}
