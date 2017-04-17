package com.damo.cor;

public class GoF {

  public static void main(String[] args) {
    OrderProcessor retailProcessor = new RetailOrderProcessor();
    OrderProcessor refundProcessor = new RefundOrderProcessor();
    retailProcessor.setNextProcessor(refundProcessor);

    Order order = new Order(Order.Type.SALE, "Red Toaster");
    Order order2 = new Order(Order.Type.REFUND, "Blue Toaster");
    retailProcessor.process(order);
    retailProcessor.process(order2);
  }

  interface OrderProcessor {
    void process(Order order);

    void setNextProcessor(OrderProcessor next);
  }

  public abstract static class AbstractOrderProcessor implements OrderProcessor {
    protected OrderProcessor next;

    @Override
    public void setNextProcessor(OrderProcessor next) {
      this.next = next;
    }
  }

  public static class RetailOrderProcessor extends AbstractOrderProcessor {
    @Override
    public void process(Order order) {
      if (order.getType() == Order.Type.SALE) {
        System.out.println("Retail Order: " + order.getProductId());
      }
      else if (next != null) {
        next.process(order);
      }
      else {
        throw new RuntimeException("Unknown order: " + order);
      }
    }
  }

  public static class RefundOrderProcessor extends AbstractOrderProcessor {
    @Override
    public void process(Order order) {
      if (order.getType() == Order.Type.REFUND) {
        System.out.println("Refund order: " + order.getProductId());
      }
      else if (next != null) {
        next.process(order);
      }
      else {
        throw new RuntimeException("Unknown order: " + order);
      }
    }
  }
}
