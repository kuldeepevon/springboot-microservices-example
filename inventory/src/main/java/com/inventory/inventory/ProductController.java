package com.inventory.inventory;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.inventory.models.Product;
import com.inventory.inventory.services.ProductService;


@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping
  public List<Product> getProducts() {
    return this.productService.getProducts();
  }

  @PostMapping
  public Product addProduct(@RequestBody Product product) {
    return this.productService.addProduct(product);
  }

  @PatchMapping(value = "/{id}")
  public Product editProduct(@PathVariable("id") ObjectId id, @RequestBody Product product) {
    product.setId(id);
    return this.productService.editProduct(product);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteProduct(@PathVariable("id") ObjectId id) {
    this.productService.deleteProduct(id);
  }

  @GetMapping(value = "/{id}")
  public Product getProduct(@PathVariable("id") ObjectId id) {
    return this.productService.getProduct(id);
  }

}
