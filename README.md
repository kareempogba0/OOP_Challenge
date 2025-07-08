
---

## 🛒 **README – Fawry Rise Journey Internship Challenge**

```markdown
# Fawry Rise Journey – Full Stack Development Internship Challenge

This project simulates a **lightweight e-commerce system** that covers key features of product management, cart operations, shipping logic, and checkout processing. It's built to showcase clean OOP design, practical logic flow, and extensibility.

---

## 💡 Features

### ✅ Products
- Each product has:
  - `name`
  - `price`
  - `available quantity`
- Product types:
  - **Perishable** (e.g. Cheese, Biscuits) – may expire.
  - **Non-Perishable** (e.g. TV, Mobile).
  - **Shippable** (e.g. TV, Cheese) – has a `weight`.
  - **Non-Shippable** (e.g. Mobile scratch cards).

### ✅ Cart
- Customers can add products to the cart with specified quantity.
- Quantity cannot exceed available stock.

### ✅ Checkout
- Verifies:
  - Cart is not empty.
  - No expired products.
  - Sufficient customer balance.
  - No out-of-stock items.
- Prints:
  - Item list with prices
  - Subtotal
  - Shipping cost
  - Total amount paid
  - Remaining customer balance
- Sends shippable items to `ShippingService`.

---

## 🚚 ShippingService Interface

```java
public interface Shippable {
    String getName();
    double getWeight();
}

---

## 🧪 Example Use Cases

```java
Customer customer = new Customer("Kareem", 500.0);
Cart cart = new Cart();

Product cheese = new Product("Cheese", 100.0, LocalDate.of(2025, 7, 5), true, 0.2, 10);
Product biscuits = new Product("Biscuits", 150.0, LocalDate.of(2025, 7, 4), true, 0.7, 5);
Product tv = new Product("TV", 200.0, null, true, 8.0, 3);
Product scratchCard = new Product("Recharge Card", 50.0, null, false, 0, 10);

cart.add(cheese, 2);
cart.add(biscuits, 1);
cart.add(scratchCard, 1);

checkout(customer, cart);

