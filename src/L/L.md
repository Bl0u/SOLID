# Liskov Substitution Principle (LSP)

## Definition

The Liskov Substitution Principle, formulated by Barbara Liskov in 1987, states:

> If S is a subtype of T, then objects of type T in a program may be replaced with objects of type S without altering any of the desirable properties of that program.

In simpler terms: **Subtypes must be behaviorally substitutable for their base types**.

## Core Concepts

### 1. Behavioral Compatibility

Subclasses should honor the contracts established by their parent classes. This means:

- **Preconditions**: Subclasses cannot strengthen preconditions (impose more restrictions on inputs)
- **Postconditions**: Subclasses cannot weaken postconditions (provide fewer guarantees about outputs)
- **Invariants**: Subclasses must maintain parent class invariants (conditions that always hold true)

### 2. Signature Requirements

- Subclass methods should accept the same parameter types as the superclass methods
- Return types in the subclass can be the same as or subtypes of the superclass method's return type
- Exceptions thrown by the subclass method should be the same as or subtypes of exceptions thrown by the superclass method

## Problem Examples

### Incorrect Implementation: Violating LSP

```java
public class Green {
    public void getColor() {
        System.out.println("Green");
    }
}

public class Blue extends Green {
    @Override
    public void getColor() {
        System.out.println("Blue");
    }
}

public class Main {
    public static void main(String[] args) {
        // Violates LSP because a Green object is expected to display "Green"
        Green green = new Blue();
        green.getColor();   
        // Output: Blue (unexpected behavior for a Green object)
    }
}
```

In this example, LSP is violated because the behavior of the `Blue` subclass contradicts the expected behavior of the `Green` class. A consumer of `Green` would expect "Green" output, but gets "Blue" instead.

### Correct Implementation: Using Interfaces

```java
public interface IColor {
    void getColor();
}

public class Green implements IColor {
    @Override
    public void getColor() {
        System.out.println("Green");
    }
}

public class Blue implements IColor {
    @Override
    public void getColor() {
        System.out.println("Blue");
    }
}

public class Main {
    public static void main(String[] args) {
        IColor color = new Blue();
        color.getColor();   
        // Output: Blue (expected behavior for a Blue object)
    }
}
```

This design respects LSP by using an interface. Each class properly implements its behavior without violating expectations.

```java
class Rectangle {
    protected int width;
    protected int height;
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getArea() {
        return width * height;
    }
}

// Violates LSP because changing width also changes height
class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width;  // Square must maintain equal sides
    }
    
    @Override
    public void setHeight(int height) {
        this.height = height;
        this.width = height;  // Square must maintain equal sides
    }
}
```

This violates LSP because:
```java
void process(Rectangle r) {
    r.setWidth(5);
    r.setHeight(4);
    assert r.getArea() == 20; // Fails when r is a Square (returns 16)
}
```
## References

- [Baeldung: Java Liskov Substitution Principle](https://www.baeldung.com/java-liskov-substitution-principle)
- [Wikipedia: Behavioral Subtyping](https://en.wikipedia.org/wiki/Behavioral_subtyping)
- [OOP SOLID with Examples](https://muatik.medium.com/oop-solid-with-examples-d3dc310d72c3)
- [Stackify: SOLID Design - Liskov Substitution Principle](https://stackify.com/solid-design-liskov-substitution-principle/)
- [GitHub: Stackify SOLID Liskov Examples](https://github.com/thjanssen/Stackify-SOLID-Liskov)
- [Liskov Substitution Principle Explained](https://tusharghosh09006.medium.com/liskov-substitution-principle-lsp-744eceb29e8)