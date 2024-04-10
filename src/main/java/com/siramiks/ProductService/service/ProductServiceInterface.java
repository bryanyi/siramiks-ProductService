package com.siramiks.ProductService.service;

import com.siramiks.ProductService.entity.Product;
import com.siramiks.ProductService.model.ProductRequest;
import com.siramiks.ProductService.model.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductServiceInterface {
  ProductResponse addProduct(ProductRequest productRequest);

  List<Product> getAllProducts();

  ProductResponse getProductById(UUID productId);

  Long decreaseQuantity(UUID productId, long quantity);
}
