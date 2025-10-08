import services.ExpenseManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running){
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Balance");
            System.out.println("4. View Transaction by Category");
            System.out.println("5. View All Transactions");
            System.out.println("6. Exit");
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextline(); // Consume newline

            switch (choice){
                case 1:
                    addTransaction(scanner, manager, "income");
                    break;
                case 2:
                    addTransaction(scanner, manager, "expense");
                case 3:
                    System.out.printf("Current Balance: $%.2f\n", manager.getBalance());
                    break;
                case 4:
                    System.out.print("Enter category: ");
                    String cat = scanner.nextLine();
                    manager.getTransactionByCategory(cat).forEach(Ssytem.out::printLn);
                    break;
                case 5: 
                    manager.getAllTransaction().forEach(System.out::println);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
    private static void addTransaction(Scanner scanner, ExpenseManger manager, String type){
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        try {
            manager.addTransaction(amount, type, desc, cat);
            System.out.println("Transaction added.");
        }catch (IllgalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
