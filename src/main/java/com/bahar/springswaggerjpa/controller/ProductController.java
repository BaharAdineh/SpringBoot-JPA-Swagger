package com.bahar.springswaggerjpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.bahar.springswaggerjpa.entity.Product;
import com.bahar.springswaggerjpa.exception.ResourceNotFoundException;
import com.bahar.springswaggerjpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Created by IntelliJ IDEA.
 * User: B_Adineh
 * Date: 12/14/2018
 * Time: 1:58 PM
 * To change this template use File | Settings | File and Code Templates.
 */
@RestController
@RequestMapping("/api/v1")
@Api(value="Product Management System", description="Operations pertaining to product in Product Management System")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @ApiOperation(value = "View a list of available products", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @ApiOperation(value = "Get an product by Id")
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(
            @ApiParam(value = "Product id from which product object will retrieve", required = true)
            @PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        return ResponseEntity.ok().body(product);
    }

    @ApiOperation(value = "Add an product")
    @PostMapping("/product")
    public Product createProduct(
            @ApiParam(value = "Product object store in database table", required = true)
            @Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @ApiOperation(value = "Update an product")
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(
            @ApiParam(value = "Product Id to update product object", required = true)
            @PathVariable(value = "id") Long productId,
            @ApiParam(value = "Update product object", required = true)
            @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

        product.setId(productDetails.getId());
        product.setName(productDetails.getName());
        product.setType(productDetails.getType());
        product.setPrice(productDetails.getPrice());
        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @ApiOperation(value = "Delete an product")
    @DeleteMapping("/product/{id}")
    public Map<String, Boolean> deleteProduct(
            @ApiParam(value = "Product Id from which product object will delete from database table", required = true)
            @PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
