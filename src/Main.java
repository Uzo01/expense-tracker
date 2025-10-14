package main;

import services.TransactionManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransactionManager manager = new TransactionManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Expense Tracker CLI - type 'exit' to quit");

        while (true) {
            System.out.print("Enter type (income/expense): ");
            String type = scanner.nextLine();
            if (type.equalsIgnoreCase("exit")) break;

            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter description: ");
            String desc = scanner.nextLine();

            System.out.print("Enter category: ");
            String cat = scanner.nextLine();

            manager.addTransaction(type, amount, desc, cat);
            System.out.println("Saved!\n");
        }
    }
}
