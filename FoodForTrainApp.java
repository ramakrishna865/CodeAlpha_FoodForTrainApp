import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FoodItem {
    private String name;
    private double price;

    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Menu {
    private List<FoodItem> foodItems;

    public Menu() {
        foodItems = new ArrayList<>();
    }

    public void addFoodItem(FoodItem foodItem) {
        foodItems.add(foodItem);
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }
}

class User {
    private String username;
    private double walletBalance;

    public User(String username, double walletBalance) {
        this.username = username;
        this.walletBalance = walletBalance;
    }

    public String getUsername() {
        return username;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void updateWalletBalance(double amount) {
        walletBalance += amount;
    }
}

class FoodOrderApp {
    private Menu menu;
    private User user;

    public FoodOrderApp() {
        menu = new Menu();
        initializeMenu();
        user = new User("JohnDoe", 100.0);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            processOrder(choice);
        }
        scanner.close();
    }

    private void initializeMenu() {
        menu.addFoodItem(new FoodItem("Burger", 8.0));
        menu.addFoodItem(new FoodItem("Pizza", 10.0));
        // Add more food items
    }

    private void displayMenu() {
        System.out.println("Welcome to Food for Train App!");
        System.out.println("Available Food Items:");
        List<FoodItem> foodItems = menu.getFoodItems();
        for (int i = 0; i < foodItems.size(); i++) {
            FoodItem item = foodItems.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - $" + item.getPrice());
        }
        System.out.println("0. Exit");
        System.out.println("Please select an option:");
    }

    private void processOrder(int choice) {
        List<FoodItem> foodItems = menu.getFoodItems();
        if (choice >= 1 && choice <= foodItems.size()) {
            FoodItem selectedFoodItem = foodItems.get(choice - 1);
            if (selectedFoodItem.getPrice() <= user.getWalletBalance()) {
                user.updateWalletBalance(-selectedFoodItem.getPrice());
                System.out.println("Order placed: " + selectedFoodItem.getName());
            } else {
                System.out.println("Insufficient wallet balance. Please add funds.");
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }
}

public class MainApp {
    public static void main(String[] args) {
        FoodOrderApp app = new FoodOrderApp();
        app.start();
    }
}
