package com.siramiks.ProductService.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductResponse {
  private UUID productId;
  private String name;
  private long price;
  private long quantity;
}
