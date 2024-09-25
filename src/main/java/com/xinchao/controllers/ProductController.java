package com.xinchao.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinchao.models.Product;
import com.xinchao.payload.request.ProductRequest;
import com.xinchao.payload.response.ProductResponse;
import com.xinchao.security.AdminPermission;
import com.xinchao.security.AllRolePermission;
import com.xinchao.services.ProductService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("checkstyle:Indentation")
@RestController
@RequestMapping("/api/v1/products")
//@Api(value = "Product Management System", tags = "Products")
public class ProductController {


    @Autowired
    private ProductService productService;
    @GetMapping
    @AllRolePermission
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/{id}")
    @AllRolePermission
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        return optionalProduct.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }




    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    @AdminPermission
    public ResponseEntity<ProductResponse> createProduct(
            @RequestParam("product") String productJson,
            @RequestParam("images") MultipartFile[] images,
            @RequestParam("userId") String userId) throws IOException {

        // Chuyển đổi chuỗi JSON thành đối tượng ProductRequest
        ObjectMapper objectMapper = new ObjectMapper();
        ProductRequest productRequest = objectMapper.readValue(productJson, ProductRequest.class);

        ProductResponse createdProduct = productService.createProduct(productRequest, images, userId);
        return ResponseEntity.ok(createdProduct);
    }


    //@CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    @AdminPermission
    public ResponseEntity<?> updateProduct(
            @PathVariable String id,
            @RequestParam("product") String productJson,
            @RequestParam("images") MultipartFile[] images) throws IOException {



        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ProductRequest productRequest = objectMapper.readValue(productJson, ProductRequest.class);

        try {
            ProductResponse updatedProduct = productService.updateProduct(id, productRequest, images);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating product: " + e.getMessage());
        }
    }






    @DeleteMapping("/{id}")
    @AdminPermission
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
