# Open/Closed Principle (OCP)

## Definition
The Open/Closed Principle states that software entities (classes, modules, functions, etc.) should be:
- **Open for extension**: You can add new functionality
- **Closed for modification**: Existing code remains unchanged when adding new functionality

## Key Concepts

- Software entities should be extendable without modifying their source code
- New behavior can be added by creating new classes or modules rather than changing existing ones

## Relation to Strategy Pattern

The Open/Closed Principle is closely related to the Strategy Design Pattern:

1. **Strategy Pattern**: Defines a family of algorithms, encapsulates each one, and makes them interchangeable
2. **OCP Implementation**: The strategy pattern allows new algorithms (strategies) to be added without modifying existing code

## References
- [Stackify: SOLID Design - Open/Closed Principle](https://stackify.com/solid-design-open-closed-principle/)
- [Medium: Open/Closed Principle (OCP) - SOLID Principle](https://medium.com/@ramdhas/2-open-closed-principle-ocp-solid-principle-cd12cbc6cb6e)