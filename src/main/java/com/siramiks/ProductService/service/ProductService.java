package com.siramiks.ProductService.service;

import com.siramiks.ProductService.entity.Product;
import com.siramiks.ProductService.exception.ProductServiceException;
import com.siramiks.ProductService.model.ProductRequest;
import com.siramiks.ProductService.model.ProductResponse;
import com.siramiks.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class ProductService implements ProductServiceInterface {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public ProductResponse addProduct(ProductRequest productRequest) {
    log.info("Adding product...");
    Product product = Product.builder()
            .productName(productRequest.getName())
            .quantity((productRequest.getQuantity()))
            .price((productRequest.getPrice()))
            .build();

    productRepository.save(product);
    log.info("product successfully added!");

    ProductResponse productResponse = ProductResponse.builder()
            .productId(product.getProductId())
            .name(product.getProductName())
            .price(product.getPrice())
            .quantity(product.getQuantity())
            .build();
    return productResponse;
  }

  public List<Product> getAllProducts() {
    log.info("Fetching all products");
    return productRepository.findAll();
  }

  public ProductResponse getProductById(UUID productId) {
    log.info("Fetching by product id...");

    Product product = productRepository.findByProductId(productId)
            .orElseThrow(() -> new ProductServiceException("Product id not found", "PRODUCT_NOT_FOUND"));

    ProductResponse productResponse = ProductResponse.builder()
            .productId(product.getProductId())
            .name(product.getProductName())
            .price(product.getPrice())
            .quantity(product.getQuantity())
            .build();

    log.info("Fetched product response: {}", productResponse);
    return productResponse;
  }

  public Long decreaseQuantity(UUID productId, long qtyToDecrease) {
    log.info("Decreasing quantity for productId: {} by {}", productId, qtyToDecrease);
    Product product = productRepository.findByProductId(productId)
            .orElseThrow(() -> new ProductServiceException("Product id not found", "PRODUCT_NOT_FOUND"));

    if (product.getQuantity() < qtyToDecrease) {
      throw new ProductServiceException("Not enough in stock!", "INSUFFICIENT_QUANTITY");
    }

    product.setQuantity(product.getQuantity() - qtyToDecrease);
    productRepository.save(product);
    log.info("Product stock count successfully updated. New stock count is {}", product.getQuantity());

    return product.getQuantity();
  }

}
