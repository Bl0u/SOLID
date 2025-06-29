# "What" vs "How" in System Design

*Created by: Bl0u*  
*Date: 2025-06-29*

## The Fundamental Distinction

In software engineering, there's a crucial distinction between:

- **"What"**: The system's requirements, goals, and responsibilities (policy)
- **"How"**: The implementation details, algorithms, and techniques (mechanism)

This separation is fundamental to good software design and aligns with the principle: "Focus on what your system should do, rather than how it does it."

## Understanding "What" (System Requirements)

The "what" describes the system's purpose, responsibilities, and behavior from a business perspective. It defines:

- Business rules and policies
- System capabilities and features
- Expected outcomes and results
- Domain concepts and relationships

### Characteristics of "What":

- Expressed in domain language
- Stable and changes less frequently
- Independent of implementation details
- Focuses on business value
- Described in requirements, user stories, and specifications

### Examples of "What":

```java
// Interface defining WHAT the system should do
public interface PaymentProcessor {
    /**
     * Process a payment for the given amount
     * @return true if payment is successful, false otherwise
     */
    boolean processPayment(PaymentDetails details, double amount);
}

// Interface defining WHAT the system should do
public interface UserAuthenticator {
    /**
     * Authenticate a user with the given credentials
     * @return User object if authentication successful, null otherwise
     */
    User authenticate(String username, String password);
}

// High-level business rule (WHAT)
public interface LoanApprovalPolicy {
    /**
     * Determine if a loan application should be approved
     */
    ApprovalResult evaluateLoanApplication(LoanApplication application);
}
```

## Understanding "How" (Implementation Details)

The "how" describes the specific implementation approaches, technologies, and algorithms used to fulfill the "what". It defines:

- Specific algorithms and data structures
- Technology choices and frameworks
- Implementation patterns and techniques
- Optimization strategies

### Characteristics of "How":

- Expressed in technical language
- Changes more frequently as technology evolves
- Depends on technical constraints and platforms
- Focuses on efficiency and technical quality
- Described in design documents, code, and technical specifications

### Examples of "How":

```java
// Concrete class implementing HOW the system processes payments
public class StripePaymentProcessor implements PaymentProcessor {
    private final String apiKey;
    
    public StripePaymentProcessor(String apiKey) {
        this.apiKey = apiKey;
    }
    
    @Override
    public boolean processPayment(PaymentDetails details, double amount) {
        // HOW: Using Stripe API to process payment
        Stripe.apiKey = this.apiKey;
        
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("amount", (int)(amount * 100)); // Convert to cents
            params.put("currency", "usd");
            params.put("source", details.getCardToken());
            params.put("description", "Charge for " + details.getCustomerEmail());
            
            Charge.create(params);
            return true;
        } catch (StripeException e) {
            Logger.error("Payment processing failed", e);
            return false;
        }
    }
}

// Another implementation showing a different HOW
public class PayPalPaymentProcessor implements PaymentProcessor {
    // HOW: Using PayPal SDK to process payment
    // Implementation details...
    
    @Override
    public boolean processPayment(PaymentDetails details, double amount) {
        // HOW: Using PayPal-specific implementation
        // ...
    }
}
```

## Real-World Example: E-commerce Order Processing

### The "What":

```java
// WHAT: Domain model and business rules
public class Order {
    private List<OrderItem> items;
    private Customer customer;
    private ShippingAddress shippingAddress;
    private PaymentMethod paymentMethod;
    private OrderStatus status;
    
    // Business rule: Order validation
    public boolean isValid() {
        return !items.isEmpty() && 
               customer != null && 
               shippingAddress != null && 
               paymentMethod != null;
    }
    
    // Business rule: Calculate total
    public double calculateTotal() {
        double subtotal = items.stream()
            .mapToDouble(item -> item.getPrice() * item.getQuantity())
            .sum();
            
        return subtotal + calculateTax() + calculateShipping();
    }
    
    // Additional business logic...
}

// WHAT: Service interface defining order processing
public interface OrderService {
    OrderResult placeOrder(Order order);
    Order getOrderById(String orderId);
    List<Order> getOrdersForCustomer(Customer customer);
    boolean cancelOrder(String orderId);
}
```

### The "How":

```java
// HOW: Concrete implementation of order service
public class SQLOrderService implements OrderService {
    private final DatabaseConnection dbConnection;
    private final PaymentProcessor paymentProcessor;
    private final InventoryService inventoryService;
    private final EmailService emailService;
    
    // Constructor with dependencies...
    
    @Override
    public OrderResult placeOrder(Order order) {
        // HOW: Implementation using SQL transactions
        try {
            dbConnection.beginTransaction();
            
            // Check inventory
            if (!inventoryService.checkAvailability(order.getItems())) {
                return new OrderResult(false, "Items not available");
            }
            
            // Process payment
            boolean paymentSuccessful = paymentProcessor.processPayment(
                order.getPaymentMethod(), 
                order.calculateTotal()
            );
            
            if (!paymentSuccessful) {
                return new OrderResult(false, "Payment failed");
            }
            
            // Update inventory
            inventoryService.reduceStock(order.getItems());
            
            // Save order to database
            String orderId = saveOrderToDatabase(order);
            
            // Send confirmation email
            emailService.sendOrderConfirmation(order.getCustomer().getEmail(), orderId);
            
            dbConnection.commitTransaction();
            return new OrderResult(true, orderId);
            
        } catch (Exception e) {
            dbConnection.rollbackTransaction();
            Logger.error("Order processing failed", e);
            return new OrderResult(false, "System error");
        }
    }
    
    // Private method showing HOW orders are saved
    private String saveOrderToDatabase(Order order) {
        // SQL-specific implementation details
        // ...
    }
    
    // Other method implementations...
}
```

## Benefits of Separating "What" from "How"

1. **Improved Maintainability**: Changes to implementation details don't affect business logic
2. **Better Testability**: Business rules can be tested independently of implementation
3. **Enhanced Flexibility**: Different implementations can be swapped without affecting core logic
4. **Clearer Communication**: Business stakeholders can focus on "what" without technical details
5. **Increased Reusability**: Core business logic can be reused across different implementations

## Practical Guidelines

1. **Use Interfaces and Abstract Classes** to define the "what" without specifying the "how"
2. **Apply the Dependency Inversion Principle** to ensure high-level modules don't depend on low-level details
3. **Create Domain Models** that reflect business concepts and rules, not implementation details
4. **Separate Business Logic** from infrastructure concerns
5. **Document the "What"** in business terms that stakeholders can understand

## Common Pitfalls

1. **Leaky Abstractions**: When implementation details leak into business logic
2. **Premature Optimization**: Focusing on "how" before clearly defining "what"
3. **Over-engineering**: Creating unnecessarily complex abstractions
4. **Mixing Concerns**: Embedding business rules within infrastructure code

## Conclusion

Focusing on "what your system should do, rather than how it does it" leads to more maintainable, flexible, and understandable software. This separation of concerns is fundamental to clean architecture and good software design.

By clearly separating business rules and policies (the "what") from implementation details (the "how"), you create systems that can evolve more easily as business needs and technologies change.