package com.xinchao.services;

import com.xinchao.models.DepositToHold;
import com.xinchao.models.Image;
import com.xinchao.models.Product;
import com.xinchao.payload.request.DepositToHoldRequest;
import com.xinchao.payload.response.DepositToHoldResponse;
import com.xinchao.repository.DepositToHoldRepository;
import com.xinchao.repository.ImageRepository;
import com.xinchao.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepositToHoldService {

    @Autowired
    private DepositToHoldRepository repository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductRepository productRepository;

    // Helper: Map Request to Entity
    private DepositToHold mapToEntity(DepositToHoldRequest request) {

        Optional<Product> productOptional = productRepository.findById(request.getProductId());

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            DepositToHold deposit = new DepositToHold();
            deposit.setName(request.getName());
            deposit.setPhone(request.getPhone());
            deposit.setProduct(product);
            deposit.setCreateAt(request.getCreateAt() != null ? request.getCreateAt() : new Date());

            // Map image IDs to Image entities
            List<Image> contactImages = request.getContactImageId().stream()
                    .map(id -> imageRepository.findById(id).orElse(null))
                    .filter(image -> image != null) // Filter out null values in case of invalid IDs
                    .collect(Collectors.toList());
            deposit.setContactImage(contactImages);

            List<Image> receiptImages = request.getReceiptImageId().stream()
                    .map(id -> imageRepository.findById(id).orElse(null))
                    .filter(image -> image != null) // Filter out null values in case of invalid IDs
                    .collect(Collectors.toList());
            deposit.setReceiptImage(receiptImages);

            deposit.setIsViewed(false);

            return deposit;
        }else {
            throw new RuntimeException("Product not found");
        }
    }

    // Helper: Map Entity to Response
    private DepositToHoldResponse mapToResponse(DepositToHold deposit) {
        DepositToHoldResponse response = new DepositToHoldResponse();
        response.setId(deposit.getId());
        response.setName(deposit.getName());
        response.setPhone(deposit.getPhone());
        response.setProduct(deposit.getProduct());
        response.setCreateAt(deposit.getCreateAt());

        // Trả về danh sách Image từ đối tượng Image
        List<Image> contactImages = deposit.getContactImage(); // Đã là List<Image> rồi
        response.setContactImage(contactImages);

        List<Image> receiptImages = deposit.getReceiptImage(); // Đã là List<Image> rồi
        response.setReceiptImage(receiptImages);
        response.setIsViewed(deposit.getIsViewed());

        return response;
    }

    // Create
    public DepositToHoldResponse create(DepositToHoldRequest request) {
        DepositToHold depositToHold = mapToEntity(request);
        DepositToHold savedDeposit = repository.save(depositToHold);
        return mapToResponse(savedDeposit);
    }

    // Read All
    public List<DepositToHoldResponse> findAll() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Read By ID
    public Optional<DepositToHoldResponse> findById(String id) {
        return repository.findById(id).map(this::mapToResponse);
    }

    // Update
    public DepositToHoldResponse update(String id, DepositToHoldRequest request) {

        Optional<Product> productOptional = productRepository.findById(request.getProductId());
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return repository.findById(id).map(existingDeposit -> {
                existingDeposit.setName(request.getName());
                existingDeposit.setPhone(request.getPhone());
                existingDeposit.setProduct(product);

                // Update images
                List<Image> updatedContactImages = request.getContactImageId().stream()
                        .map(imageId -> imageRepository.findById(imageId).orElse(null))
                        .filter(image -> image != null)
                        .collect(Collectors.toList());
                existingDeposit.setContactImage(updatedContactImages);

                List<Image> updatedReceiptImages = request.getReceiptImageId().stream()
                        .map(imageId -> imageRepository.findById(imageId).orElse(null))
                        .filter(image -> image != null)
                        .collect(Collectors.toList());
                existingDeposit.setReceiptImage(updatedReceiptImages);

                DepositToHold updatedDeposit = repository.save(existingDeposit);
                return mapToResponse(updatedDeposit);
            }).orElseThrow(() -> new RuntimeException("DepositToHold not found with id " + id));
        }else {
            throw new RuntimeException("Product not found");
        }
    }

    public void delete(String id) {
        DepositToHold deposit = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DepositToHold not found with id " + id));

        deposit.setProduct(null);
        repository.save(deposit);
        // Xóa hình ảnh liên quan đến ContactImage
        deposit.getContactImage().forEach(image -> {
            Image existingImage = imageRepository.findById(image.getId())
                    .orElseThrow(() -> new RuntimeException("Image not found with id " + image.getId()));
            imageRepository.delete(existingImage);
        });

        // Xóa hình ảnh liên quan đến ReceiptImage
        deposit.getReceiptImage().forEach(image -> {
            Image existingImage = imageRepository.findById(image.getId())
                    .orElseThrow(() -> new RuntimeException("Image not found with id " + image.getId()));
            imageRepository.delete(existingImage);
        });

        // Xóa bản ghi chính
        repository.delete(deposit);
    }

    public List<DepositToHoldResponse> getDepositsByIsViewed(Boolean isViewed) {

        List<DepositToHold> deposits = repository.findByIsViewed(isViewed);

        // Chuyển đổi các DepositToHold thành DepositToHoldResponse
        return deposits.stream()
                .map(this::mapToResponse)  // Sử dụng phương thức mapToResponse để chuyển đổi
                .collect(Collectors.toList());
    }

}
