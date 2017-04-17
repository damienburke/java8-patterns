package com.damo.cor;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Lambda {


  public static Optional<String> parseSale(Order file) {
    return Optional.ofNullable(file)
        .filter(f -> f.getType() == Order.Type.SALE)
        .map(f -> "Retail Order: " + f.getProductId());
  }

  public static Optional<String> parseRefund(Order file) {
    return Optional.ofNullable(file)
        .filter(f -> f.getType() == Order.Type.REFUND)
        .map(f -> "Refund Order: " + f.getProductId());
  }

  public static void main( String[] args ) {
    Order order = new Order( Order.Type.REFUND, "Green Toaster" );
    Order order2 = new Order( Order.Type.SALE, "Orange Toaster" );

    process(order);
    process(order2);

  }

  public static void process(Order order) {
    System.out.println(
        Stream.<Function<Order, Optional<String>>>of(
            Lambda::parseSale,
            Lambda::parseRefund)
            .map(f -> f.apply( order ))
            .filter( Optional::isPresent )
            .map( Optional::get )
            .findFirst()
            .orElseThrow( () -> new RuntimeException( "Unknown file: " + order ) )
    );
  }
}
