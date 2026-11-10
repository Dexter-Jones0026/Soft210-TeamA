package org.example;

import static org.example.CustomerTier.REGULAR;


public class Main {
    public static void main(String[] args) {
        DiscountService discountService = new DiscountService();  // creates the service needed
        ShippingService shippingService = new ShippingService();

        OrderProcessor orderProcessor = new OrderProcessor(discountService, shippingService);


        Customer customer1 = new Customer("01", 12, REGULAR, 100, true);

        // items
        Item shirt = new Item("Shirt", "CLOTHING", 12.50, true);
        Item phone = new Item("Phone", "ELECTRONICS", 250.00, false);
        Item eggs = new Item("Eggs", "GROCERY", 3.50, false);


        Order order1 = new Order("01", customer1, "REGIONAL");

        order1.addItem(shirt, 1);
        order1.addItem(phone, 1);

        double total;
        try {
            total = orderProcessor.processOrder(order1);
        } catch (OrderProcessingException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Price: $" + total);
        System.out.println("Loyalty points: " + customer1.getLoyaltyPoints());
        System.out.println();
//        System.out.println("your Items ordered are " + order1.getItems());
        System.out.println("Items Ordered:");

        for (var entry : order1.getItems().entrySet()) {
            System.out.println(entry.getKey() +
                    ", Quantity: " + entry.getValue());
        }

        System.out.println("OrderID: " + order1.getOrderId());
        System.out.println( "Order Processed: " + order1.isProcessed());
    }
}