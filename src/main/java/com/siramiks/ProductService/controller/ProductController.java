package com.siramiks.ProductService.controller;

import com.siramiks.ProductService.entity.Product;
import com.siramiks.ProductService.model.ProductRequest;
import com.siramiks.ProductService.model.ProductResponse;
import com.siramiks.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        value = "/api/v1/product",
        produces = "application/json",
        method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/getAllProducts")
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> productList = productService.getAllProducts();
    return new ResponseEntity<>(productList, HttpStatus.OK);
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponse> getProductById(@PathVariable("productId") UUID productId) {
    ProductResponse productResponse = productService.getProductById(productId);
    return new ResponseEntity<>(productResponse, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
    ProductResponse productResponse = productService.addProduct(productRequest);
    return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
  }

  @PutMapping("/checkStock/{productId}")
  public ResponseEntity<Boolean> hasEnoughStock(@PathVariable("productId") UUID productId, @RequestParam long qtyToDecrease) {
    return new ResponseEntity<>(productService.hasEnoughStock(productId, qtyToDecrease), HttpStatus.OK);
  }

  @PutMapping("/decreaseQuantity/{productId}")
  public ResponseEntity<Long> decreaseQuantity(@PathVariable("productId") UUID productId, @RequestParam long qtyToDecrease) {
    long newQuantity = productService.decreaseQuantity(productId, qtyToDecrease);
    return new ResponseEntity<>(newQuantity, HttpStatus.OK);
  }
}
