package com.inventory.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.inventory.models.Order;
import com.inventory.inventory.services.ProductService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
  @Autowired
  ProductService productService;

  @PostMapping
  public Order processOrder(@RequestBody Order order) {
    return productService.handleOrder(order);
  }

  @DeleteMapping
  public Order revertOrder(@RequestBody Order order) {
    return productService.revertOrder(order);
  }
}
