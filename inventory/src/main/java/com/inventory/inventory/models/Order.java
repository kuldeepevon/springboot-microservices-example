package com.inventory.inventory.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  @JsonSerialize(using = ObjectIdSerializer.class)
  @Id
  private ObjectId id;
  private String userId;
  private List<LineItem> lineItems;
  private Long total;
  private OrderStatus orderStatus;
  private String responseMessage;

  
}
