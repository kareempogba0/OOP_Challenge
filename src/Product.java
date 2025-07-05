import java.time.LocalDate;
import java.util.*;

public class Product implements Shippable {
    String name;
    double price;
    LocalDate expiryDate;
    boolean isShippable;
    double weight; // in kg
    int availableQuantity;

    public Product(String name, double price, LocalDate expiryDate, boolean isShippable, double weight, int availableQuantity) {
        if(price <= 0 || weight < 0 || availableQuantity < 0) throw new IllegalArgumentException("Price, weight, and available quantity must be non negative.");
        if(isShippable && weight == 0) throw new IllegalArgumentException("Weight must be greater than 0 for shippable products.");
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
        this.isShippable = isShippable;
        this.weight = isShippable ? weight : 0; //assume non-shippable products have weight = 0
        this.availableQuantity = availableQuantity;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isShippable() {
        return isShippable;
    }

    public void setShippable(boolean shippable) {
        isShippable = shippable;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    boolean isExpired() {
        if (this.expiryDate == null) return false;
        return LocalDate.now().isAfter(expiryDate);
    }

    void updateAvailableQuantity(int quantity) {
        if (quantity > availableQuantity) throw new IllegalArgumentException("Cannot decrease available quantity to less than zero.");
        this.availableQuantity -= quantity;
    }

    //override equals and hashCode for using Product -> key in Map
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
