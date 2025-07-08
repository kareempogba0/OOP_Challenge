
---

## 🛒 **README – Fawry Rise Journey Internship Challenge**

# Fawry Rise Journey – Full Stack Development Internship Challenge

This project simulates a **lightweight e-commerce system** that covers essential features such as product definition, cart operations, checkout processing, and shipping service integration. It demonstrates robust object-oriented design in Java.

---

## 💡 Features

### ✅ Products
Each product contains:
- `name`
- `price`
- `quantity`

Product behaviors:
- Some products may **expire** (e.g. Cheese, Biscuits)
- Some products are **non-expiring** (e.g. TV, Mobile)
- Some products require **shipping** (e.g. Cheese, TV)
- Others don’t (e.g. Mobile Scratch Cards)

Shippable products have a **weight**.

---

### ✅ Cart
- Customers can add products to the cart in a specified quantity.
- Quantity cannot exceed the available product quantity.

---

### ✅ Checkout
When a customer checks out:
- The system validates:
  - The cart is not empty.
  - None of the items are expired.
  - Customer has enough balance.
  - No item is out of stock.

- The system prints:
  - Line item details
  - Order subtotal
  - Shipping cost (calculated from shippable items)
  - Total amount
  - Customer's remaining balance

- Shippable items are collected and sent to `ShippingService`.

---

## 🚚 ShippingService Interface

```java
public interface Shippable {
    String getName();
    double getWeight();
}
```
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
```

---

## 🖨️ Sample Console Output

```yaml
** Shipment notice **
2x Cheese 400g
1x Biscuits 700g
Total package weight 1.1kg

** Checkout receipt **
2x Cheese 200
1x Biscuits 150
1x Recharge Card 50
----------------------
Subtotal: 400
Shipping: 30
Amount: 430
Customer's current balance: 70
