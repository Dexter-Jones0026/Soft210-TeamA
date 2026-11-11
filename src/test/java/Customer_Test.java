public class Customer_Test {
}
/*
 * BVA Tests
 * age = -1
 * Expected: IllegalArgumentException

 * age = 0
 * Expected: Customer Created Successfully

 * age = 120
 * Expected: Customer Created Successfully

 * age = 121
 * Expected: IllegalArgumentException

 * EP Tests

 * age = 25
 * Expected: Valid Customer

 * age = 50
 * Expected: Valid Customer

 * age = 150
 * Expected: Invalid Customer

 * Loyalty Point Validation

 * loyaltyPoints = -50
 * Expected: Stored as 0

 * loyaltyPoints = 0
 * Expected: Stored as 0

 * loyaltyPoints = 100
 * Expected: Stored as 100

 * Setter Tests

 * setTier(GOLD)
 * Expected: Tier changes to GOLD

 * setLoyaltyPoints(500)
 * Expected: Loyalty points updated to 500
 */