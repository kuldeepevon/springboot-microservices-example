package com.inventory.inventory.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  @JsonSerialize(using = ObjectIdSerializer.class)
  @Id
  private ObjectId id;
  private String name;
  private Long price;
  private Integer stock;
}
