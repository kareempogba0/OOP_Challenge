import java.util.*;

public class ShippingProcess {

    public void sendToShipping(Map<Shippable, Integer> shippableItems) {
        if (shippableItems.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");
        double totalPackageWeight = 0;

        for (Map.Entry<Shippable, Integer> entry : shippableItems.entrySet()) {
            Shippable item = entry.getKey();
            int quantity = entry.getValue();
            double itemTotalWeight = item.getWeight() * quantity;
            totalPackageWeight += itemTotalWeight;
            System.out.printf("%dx %-10s %.0fg%n", quantity, item.getName(), item.getWeight() * quantity * 1000); // in g, -10 for alignment
        }

        System.out.printf("Total package weight %.1fkg%n", totalPackageWeight); // total weight in kg
    }
}
