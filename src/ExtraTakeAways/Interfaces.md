# Interfaces and the Open/Closed Principle

## Understanding Interfaces

Interfaces define contracts that implementing classes must follow. A key insight about interfaces:

> You can declare variables of interface types and these variables can hold references to objects that implement those interfaces.

```java
// Example in Java
public interface PaymentProcessor {
    void processPayment(double amount);
}

// Implementation
public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        // Credit card processing logic
    }
}

// Using the interface
PaymentProcessor processor = new CreditCardProcessor(); // Interface variable holds implementing object
processor.processPayment(100.00);
```