package org.example;




public class Main {
    public static void main(String[] args) {

        // Create the services used to calculate discounts and shipping costs
        DiscountService discountService = new DiscountService();
        ShippingService shippingService = new ShippingService();

        // Create the order processor that handles all order calculations
        OrderProcessor orderProcessor = new OrderProcessor(discountService, shippingService);

        // Create customers with different ages, loyalty point levels,
        // and membership tiers to exercise different discount paths
        Customer customer1 = new Customer("01", 12, CustomerTier.REGULAR, 100, true);
        Customer customer2 = new Customer("02", 13, CustomerTier.SILVER, 125, true);
        Customer customer3 = new Customer("03", 34, CustomerTier.GOLD, 175, false);
        Customer customer4 = new Customer("04",54,CustomerTier.PLATINUM, 200, false);

        // Create items that are able to buy
        Item shirt = new Item("Shirt", "CLOTHING", 12.50, true);
        Item phone = new Item("Phone", "ELECTRONICS", 250.00, false);
        Item eggs = new Item("Eggs", "GROCERY", 3.50, false);

        // create order with different shipping zone
        Order order1 = new Order("01", customer1, "REGIONAL");
        Order order2 = new Order("02", customer2, "DOMESTIC");
        Order order3 = new Order("03", customer3, "INTERNATIONAL");
        Order order4 = new Order("04", customer4, "REGIONAL");

        // Add products and quantities to each order
        order1.addItem(shirt, 1);
        order1.addItem(phone, 1);
        order1.addItem(eggs, 1);

        order2.addItem(eggs, 2);
        order2.addItem(phone, 2);

        order3.addItem(eggs, 3);
        order3.addItem(phone, 1);

        order4.addItem(shirt, 2);
        order4.addItem(phone,3);

        // create an order array to get all orders
        Order[] orders = {order1, order2, order3, order4};
         for (Order order : orders) {                                           // gets all orders to make receipt
             try {
                 double total = orderProcessor.processOrder(order);

                 double subtotal = 0.0;

                 for (var entry : order.getItems().entrySet()) {
                     subtotal += entry.getKey().getUnitPrice() * entry.getValue();
                 }

                 double discountRate =
                         discountService.calculateDiscountPercentage(order, subtotal);

                 double discountAmount = subtotal * discountRate;
                 double discoutedSubTotal = subtotal - discountAmount;
                 double shippingAmount = shippingService.calculateShippingCost(order, discoutedSubTotal );
                 double tax = OrderProcessor.getTax();



                 System.out.println("Customer ID: " +
                         order.getCustomer().getId());

                 System.out.println("Subtotal: $" + subtotal);

                 System.out.println("Discount Rate: " + (discountRate * 100) + "%");

                 System.out.println("Discount Amount: $" + discountAmount);

                 System.out.println("Shipping: $" + shippingAmount);
                 System.out.println("Tax: " + tax);

                 System.out.println("Final Total: $" + total);


                 System.out.println("Loyalty Points: " +
                         order.getCustomer().getLoyaltyPoints());

                 for (var entry : order.getItems().entrySet()) {
                     System.out.println(entry.getKey() +
                             ", Quantity: " + entry.getValue());
                 }

                 System.out.println("Order Processed: " +
                         order.isProcessed());

                 System.out.println(
                         "\n-----------------------------------------------\n");

             } catch (Exception e) {
                 System.out.println("Error processing order " + order.getOrderId());
             }
         }
    }
}