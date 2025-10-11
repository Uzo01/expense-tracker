import services.ExpenseManager;

import java.util.List;
import java.util.Scanner;

import models.Transaction;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Balance");
            System.out.println("4. View Transactions by Category");
            System.out.println("5. View All Transactions");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTransaction(scanner, manager, "income");
                    break;
                case 2:
                    addTransaction(scanner, manager, "expense");
                    break;
                case 3:
                    System.out.printf("Current Balance: $%.2f\n", manager.getBalance());
                    break;
                case 4:
                    System.out.print("Enter category: ");
                    String cat = scanner.nextLine();
                    System.out.println("Fetching transactions for category: " + cat);
                    List<Transaction> transByCat = manager.getTransactionsByCategory(cat);
                    System.out.println("Result list: " + (transByCat != null ? transByCat.size() : "null"));
                    if (transByCat != null) transByCat.forEach(System.out::println);
                    else System.out.println("No transactions for category.");
                    break;
                case 5:
                    System.out.println("Fetching all transactions...");
                    List<Transaction> allTrans = manager.getAllTransactions();
                    System.out.println("Result list: " + (allTrans != null ? allTrans.size() : "null"));
                    if (allTrans != null) allTrans.forEach(System.out::println);
                    else System.out.println("No transactions available.");
                    break;
                case 6: // Assuming you added this for monthly summary
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    System.out.print("Enter month (1-12): ");
                    int month = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    manager.getMonthlySummary(year, month).forEach(System.out::println); // Error here
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void addTransaction(Scanner scanner, ExpenseManager manager, String type) {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter category (e.g., Food, Bills): ");
        String cat = scanner.nextLine();
        try {
            manager.addTransaction(amount, type, desc, cat);
            System.out.println("Transaction added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}