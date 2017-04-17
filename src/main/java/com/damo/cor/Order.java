package com.damo.cor;

public class Order {

  public enum Type {SALE, REFUND}

  private final Type type;
  private final String productId;

  public Order(Type type, String productId) {
    this.type = type;
    this.productId = productId;
  }

  public Type getType() {
    return type;
  }

  public String getProductId() {
    return productId;
  }

  @Override
  public String toString() {
    return type + ": " + productId;
  }
}
