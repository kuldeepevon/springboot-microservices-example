package com.inventory.inventory.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.inventory.models.Order;
import com.inventory.inventory.models.OrderStatus;
import com.inventory.inventory.models.Product;
import com.inventory.inventory.models.ProductRepository;

@Service
public class ProductService {
  @Autowired
  ProductRepository productRepository;
  
  public Product addProduct(Product product) {
    productRepository.save(product);
    return product;
  }

  public Product editProduct(Product product) {
    productRepository.save(product);
    return product;
  }

  public void deleteProduct(ObjectId productId) {
    productRepository.deleteById(productId);
  }

  public Product getProduct(ObjectId productId) {
    return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
  }

  public List<Product> getProducts() {
    return productRepository.findAll();
  }

  @Transactional
  public Order handleOrder(Order order) {
    order.getLineItems().forEach(l -> {
      Product p = productRepository.findById(l.getProductId()).orElseThrow(() -> new RuntimeException("Could not find product"));

      if(p.getStock() >= l.getQuantity()) {
        p.setStock(p.getStock() - l.getQuantity());
        productRepository.save(p);
      } else {
        throw new RuntimeException("Out of stock");
      }
    });
    order.setOrderStatus(OrderStatus.SUCCESS);
    return order;
  }

  @Transactional
  public Order revertOrder(Order order) {
    order.getLineItems().forEach(l -> {
      Product p = productRepository.findById(l.getProductId()).orElseThrow(() -> new RuntimeException("Could not find product"));

      p.setStock(p.getStock() + l.getQuantity());
      productRepository.save(p);
      
    });
    order.setOrderStatus(OrderStatus.SUCCESS);
    return order;
  }

}
