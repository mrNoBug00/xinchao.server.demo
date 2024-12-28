package com.xinchao.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinchao.dto.ProductDTO;
import com.xinchao.endpoints.ProductApiEndpoints;
import com.xinchao.models.Product;
import com.xinchao.models.StatusEnum;
import com.xinchao.payload.request.ProductRequest;
import com.xinchao.payload.response.ProductResponse;
import com.xinchao.security.AdminPermission;
import com.xinchao.security.AllRolePermission;
import com.xinchao.services.ProductService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("checkstyle:Indentation")

@CrossOrigin
@RestController
@RequestMapping(ProductApiEndpoints.BASE_URL_PRODUCT)
//@Api(value = "Product Management System", tags = "Products")
public class ProductController {


    @Autowired
    private ProductService productService;
    @GetMapping
    @AllRolePermission
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }


    //@CrossOrigin(origins = "*")
    @AllRolePermission
    @GetMapping(ProductApiEndpoints.GET_PRODUCT_BY_ID)
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
        Optional<ProductResponse> productResponseOptional = productService.getProductById(id);

        if (productResponseOptional.isPresent()) {
            return ResponseEntity.ok(productResponseOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //@CrossOrigin(origins = "*")
    @PostMapping(ProductApiEndpoints.CREATE_PRODUCT)
    @AdminPermission
    public ResponseEntity<ProductResponse> createProduct(
            @RequestParam("product") String productJson,
            @RequestParam("images") MultipartFile[] images,
            @RequestParam("userId") String userId,
            @RequestParam("companyInfoId") String companyInfoId) throws IOException {

        // Chuyển đổi chuỗi JSON thành đối tượng ProductRequest
        ObjectMapper objectMapper = new ObjectMapper();
        ProductRequest productRequest = objectMapper.readValue(productJson, ProductRequest.class);

        ProductResponse createdProduct = productService.createProduct(productRequest, images, userId, companyInfoId);
        return ResponseEntity.ok(createdProduct);
    }


    //@CrossOrigin(origins = "*")
    @PutMapping(ProductApiEndpoints.UPDATE_PRODUCT)
    @AdminPermission
    public ResponseEntity<?> updateProduct(
            @PathVariable String id,
            @RequestParam("product") String productJson,
            @RequestParam("images") MultipartFile[] images,
            @RequestParam("userId") String userId,
            @RequestParam("companyInfoId") String companyInfoId) throws IOException {



        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ProductRequest productRequest = objectMapper.readValue(productJson, ProductRequest.class);

        try {
            ProductResponse updatedProduct = productService.updateProduct(id, productRequest, images , userId, companyInfoId);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating product: " + e.getMessage());
        }
    }


    //@CrossOrigin(origins = "*")
    @DeleteMapping(ProductApiEndpoints.DELETE_PRODUCT)
    @AdminPermission
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(ProductApiEndpoints.GET_PRODUCTS_BY_CATEGORY)
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable String categoryName) {
        try {
            List<ProductResponse> products = productService.getProductsByCategory(categoryName);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(ProductApiEndpoints.GET_PRODUCTS_BY_STATUS)
    public ResponseEntity<List<ProductResponse>> getProductsByStatus(@PathVariable String statusName) {
        try {
            // Chuyển đổi statusName thành StatusEnum
            StatusEnum statusEnum = StatusEnum.valueOf(statusName.toUpperCase());

            // Gọi service để lấy sản phẩm theo trạng thái
            List<ProductResponse> products = productService.getProductsByStatus(statusEnum);
            return ResponseEntity.ok(products);
        } catch (IllegalArgumentException e) {
            // Trả về lỗi nếu giá trị không hợp lệ
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(ProductApiEndpoints.GET_PRODUCTS_BY_PAGINATED)
    public ResponseEntity<Page<ProductResponse>> getPaginatedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ProductResponse> paginatedProducts = productService.getPaginatedProducts(page, size);
        return ResponseEntity.ok(paginatedProducts);
    }




}
