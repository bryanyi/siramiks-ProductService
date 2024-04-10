package com.siramiks.ProductService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "product_id")
  private UUID productId;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "price")
  private long price;

  @Column(name = "quantity")
  private long quantity;

  /* will be automatically invoked by the JPA provider before the entity is persisted */
  @PrePersist
  protected void onCreate() {
    if (this.productId == null) {
      // Generate a UUID if productId is not set
      this.productId = UUID.randomUUID();
    }
  }

}
