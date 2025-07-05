import java.util.*;

public class Cart {
    Map<Product, Integer> items;

    public Cart() {
        this.items = new LinkedHashMap<>(); //to preserve insertion order
    }

    void add(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Product cannot be null and quantity must be positive.");
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
        System.out.println(quantity + "x " +  product.getName() + " added to the cart.");
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}
