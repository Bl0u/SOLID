# High-Level vs Low-Level Modules

*Created by: Bl0u*  
*Date: 2025-06-29*

## Definition

**High-level modules** and **low-level modules** represent different layers of abstraction in software architecture. Understanding the distinction between them is crucial for building maintainable, flexible systems.

## High-Level Modules

High-level modules focus on **business logic** and **policy decisions**. They represent the core purpose of the application and are closer to the problem domain.

### Characteristics:
- Define business rules and workflows
- Work with abstractions rather than implementation details
- Should be stable and change less frequently
- Depend on abstractions, not concrete implementations
- Often contain the "what" of a system

### Examples:

```java
// High-level module focusing on business logic
public class OrderProcessor {
    private PaymentGateway paymentGateway;  // Abstraction, not implementation
    private InventoryManager inventoryManager;  // Abstraction, not implementation
    private NotificationService notificationService;  // Abstraction, not implementation
    
    public OrderProcessor(PaymentGateway paymentGateway, 
                          InventoryManager inventoryManager,
                          NotificationService notificationService) {
        this.paymentGateway = paymentGateway;
        this.inventoryManager = inventoryManager;
        this.notificationService = notificationService;
    }
    
    public boolean processOrder(Order order) {
        // High-level business workflow
        if (inventoryManager.checkAvailability(order.getItems())) {
            if (paymentGateway.processPayment(order.getPaymentDetails(), order.getTotalAmount())) {
                inventoryManager.updateInventory(order.getItems());
                notificationService.notifyCustomer(order.getCustomer(), "Order confirmed");
                return true;
            } else {
                notificationService.notifyCustomer(order.getCustomer(), "Payment failed");
                return false;
            }
        } else {
            notificationService.notifyCustomer(order.getCustomer(), "Items not available");
            return false;
        }
    }
}
```

## Low-Level Modules

Low-level modules focus on **implementation details** and **technical concerns**. They represent how the system actually executes its functions.

### Characteristics:
- Implement specific operations and algorithms
- Deal with concrete implementation details
- May change more frequently as technology evolves
- Often contain the "how" of a system
- Should be easily replaceable without affecting high-level modules

### Examples:

```java
// Low-level module implementing a specific payment gateway
public class StripePaymentGateway implements PaymentGateway {
    private StripeAPI stripeAPI;
    private String apiKey;
    
    public StripePaymentGateway(String apiKey) {
        this.stripeAPI = new StripeAPI();
        this.apiKey = apiKey;
    }
    
    @Override
    public boolean processPayment(PaymentDetails details, double amount) {
        // Low-level implementation details
        try {
            StripeCharge charge = stripeAPI.createCharge(
                details.getCardNumber(),
                details.getExpiryDate(),
                details.getCvv(),
                amount,
                apiKey
            );
            return charge.isSuccessful();
        } catch (StripeException e) {
            Logger.log(e);
            return false;
        }
    }
}

// Another low-level module implementing database operations
public class MySQLInventoryManager implements InventoryManager {
    private Connection dbConnection;
    
    public MySQLInventoryManager(String connectionString) {
        // Database connection setup
        this.dbConnection = DatabaseConnector.connect(connectionString);
    }
    
    @Override
    public boolean checkAvailability(List<Item> items) {
        // Low-level database query implementation
        try {
            for (Item item : items) {
                PreparedStatement stmt = dbConnection.prepareStatement(
                    "SELECT quantity FROM inventory WHERE item_id = ?"
                );
                stmt.setString(1, item.getId());
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    int availableQuantity = rs.getInt("quantity");
                    if (availableQuantity < item.getQuantity()) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            Logger.log(e);
            return false;
        }
    }
    
    @Override
    public void updateInventory(List<Item> items) {
        // Implementation details for updating inventory in database
        // ...
    }
}
```

## The Relationship Between High and Low-Level Modules

In well-designed systems:

1. **High-level modules should not depend on low-level modules**. Both should depend on abstractions.
2. **Abstractions should not depend on details**. Details should depend on abstractions.

This is known as the **Dependency Inversion Principle** (the 'D' in SOLID).

### Visualization:

```
Before Dependency Inversion:
┌────────────────┐     depends on     ┌────────────────┐
│  High-Level    │ ----------------> │   Low-Level    │
│    Module      │                   │    Module      │
└────────────────┘                   └────────────────┘

After Dependency Inversion:
┌────────────────┐     depends on     ┌────────────────┐
│  High-Level    │ ----------------> │  Abstraction   │
│    Module      │                   │  (Interface)   │
└────────────────┘                   └────────────────┘
                                            ▲
                                            │ implements
                                            │
                                     ┌────────────────┐
                                     │   Low-Level    │
                                     │    Module      │
                                     └────────────────┘
```

## Benefits of Proper Separation

1. **Maintainability**: Changes to low-level implementations don't affect high-level business logic
2. **Testability**: High-level modules can be tested with mock implementations of low-level modules
3. **Flexibility**: Low-level implementations can be swapped without changing high-level modules
4. **Parallel Development**: Teams can work on different levels simultaneously

## Common Pitfalls

1. **Leaky Abstractions**: When low-level details leak into high-level modules
2. **Tight Coupling**: When high-level modules directly depend on specific implementations
3. **Improper Layering**: Mixing business logic with implementation details

## Conclusion

Understanding the distinction between high-level and low-level modules is essential for creating clean, maintainable architectures. By properly separating concerns and following the Dependency Inversion Principle, you can build systems that are easier to extend, maintain, and test.