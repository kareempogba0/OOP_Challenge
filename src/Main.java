import java.time.LocalDate;
import java.util.*;
public class Main {
    static final double SHIPPING_PRICE_PER_1KG = 10.0;
    static final ShippingProcess shippingProcess = new ShippingProcess();

    static void checkout(Customer customer, Cart cart){
        System.out.println("--- Initiating checkout for " + customer.getName() + " ---");

        //error case
        if (cart.isEmpty()) {
            System.out.println("Error: Cart is empty. Cannot proceed with checkout.");
            return;
        }

        double subtotal = 0, noOfProducts = 0;
        Map<Shippable, Integer> shippableItems = new LinkedHashMap<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            //error case
            if(quantity > product.getAvailableQuantity()){
                System.out.println("Error: Insufficient stock for " + product.getName() + ". Available: " + product.getAvailableQuantity() + ", Required: " + quantity);
                return;
            }

            //error case
            if(product.isExpired()){
                System.out.println("Error: Cannot checkout expired product " + product.getName() + ".");
                return;
            }

            subtotal += product.getPrice() * quantity;
            if (product.isShippable()) {
                shippableItems.put(product, quantity);
                noOfProducts += quantity;
            }
        }

        double shipping = noOfProducts * SHIPPING_PRICE_PER_1KG, amount = subtotal + shipping;

        //error case
        if(customer.getBalance() < amount){
            System.out.println("Error: Insufficient balance. Required: " + amount + ", Available: " + customer.getBalance());
            return;
        }

        shippingProcess.sendToShipping(shippableItems);

        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%dx %-10s %.2f%n", quantity, product.getName(), product.getPrice() * quantity);
        }
        System.out.println("----------------------");
        System.out.printf("%-10s %.2f%n", "Subtotal", subtotal);
        System.out.printf("%-10s %.2f%n", "Shipping", shipping);
        System.out.printf("%-10s %.2f%n", "Amount", amount);

        customer.canAfford(amount);
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            entry.getKey().updateAvailableQuantity(entry.getValue());
        }

        System.out.printf("Customer current balance: %.2f%n", customer.getBalance());

        cart.clear();
        System.out.println("--- Checkout completed ---");
    }

    public static void main(String[] args) {
        Product cheese = new Product("Cheese", 100.0, LocalDate.now().plusDays(7), true, 0.2, 10);// 400g
        Product tv = new Product("TV", 5000.0, null, true, 8.5, 5); //8.5kg
        Product biscuits = new Product("Biscuits", 15.0, LocalDate.now().plusMonths(6), true, 0.7, 50); //700g
        Product scratchCard = new Product("Mobile Scratch Card", 25.0,null , false, 0, 100);
        Product expiredCheese = new Product("Expired Cheese", 50.0, LocalDate.now().minusDays(1), true, 0.4, 20);

        Customer customer = new Customer("Kareem", 5000.0);

        System.out.println("--- Use Case 1 ---");
        Cart cart1 = new Cart();
        cart1.add(cheese, 2);
//        cart1.add(tv, 1);
        cart1.add(biscuits, 1);
//        cart1.add(scratchCard, 4);
        checkout(customer, cart1);
        System.out.println("=======================================\n");

        System.out.println("available quantity of cheese: " + cheese.getAvailableQuantity());//8
//        System.out.println("available quantity of tv: " + tv.getAvailableQuantity()); //4

        System.out.println("--- Use Case 2 ---");
        Cart cart2 = new Cart();
        cart2.add(tv, 1);
        checkout(customer, cart2);
        System.out.println("=======================================\n");

        System.out.println("--- Use Case 3 ---");
        Cart cart3 = new Cart();
        cart3.add(tv, 10);
        checkout(customer, cart3);
        System.out.println("=======================================\n");

        System.out.println("--- Use Case 4 ---");
        Cart cart4 = new Cart();
        cart4.add(expiredCheese, 1);
        checkout(customer, cart4);
        System.out.println("=======================================\n");

        System.out.println("--- Use Case 5 ---");
        Cart cart5 = new Cart();
        checkout(customer, cart5);
        System.out.println("=======================================\n");
    }
}