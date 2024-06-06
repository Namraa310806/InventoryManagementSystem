import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

class Inventory {
    private HashMap<String, Product> products;

    public Inventory() {
        products = new HashMap<>();
    }

    public void addProduct(String name, double price, int quantity) {
        products.put(name, new Product(name, price, quantity));
    }

    public void removeProduct(String name) {
        products.remove(name);
    }

    public void updateQuantity(String name, int quantity) {
        Product product = products.get(name);
        if (product != null) {
            product.setQuantity(quantity);
        }
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }
}

class InventoryManager {
    private Inventory inventory;
    Scanner sc=new Scanner(System.in);
    public InventoryManager() {
        inventory = new Inventory();
    }

    public void addProduct() {
        
        System.out.print("Enter product name: ");
        String name = sc.next();
        System.out.print("Enter product price: ");
        double price = sc.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = sc.nextInt();
        inventory.addProduct(name, price, quantity);
        System.out.println("Product added successfully.");    
    }

    public void sellProduct() {
        System.out.print("Enter product name: ");
        String name = sc.next();
        Product product = findProduct(name);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        System.out.print("Enter quantity to sell: ");
        int quantity = sc.nextInt();
        System.out.println("Product sold successfully.");
        if (product != null && product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            System.out.println("Product sold successfully.");
        }
        else {
            System.out.println("Insufficient quantity. Cannot sell the product.");
        }
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        List<Product> products = inventory.getProducts();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private Product findProduct(String name) {
        for (Product product : inventory.getProducts()) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        Scanner sc = new Scanner(System.in);

        int choice = 0;

        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Product");
            System.out.println("2. Sell Product");
            System.out.println("3. Display All Products");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        inventoryManager.addProduct();
                    break;
                    case 2:
                        inventoryManager.sellProduct();
                        break;
                    case 3:
                        inventoryManager.displayInventory();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 4.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); 
            }
        } while (choice != 4);

        sc.close();

    }
}
