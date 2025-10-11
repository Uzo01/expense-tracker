package services;

import models.Transaction;
import utils.FileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseManager {
    private List<Transaction> transactions = new ArrayList<>();
    private String filePath = "data/transactions.csv";

    public ExpenseManager() {
        // Load from file on initialization
        transactions = FileHandler.loadTransactions(filePath);
    }

    public void addTransaction(double amount, String type, String description, String category) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (!type.equalsIgnoreCase("income") && !type.equalsIgnoreCase("expense")) {
            throw new IllegalArgumentException("Type must be 'income' or 'expense'");
        }

        Transaction t = new Transaction(amount, type, description, category);
        transactions.add(t);
        FileHandler.saveTransactions(transactions, filePath);
    }

    public double getBalance() {
        double income = transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("income"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double expense = transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("expense"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        return income - expense;
    }

    public List<Transaction> getTransactionsByCategory(String category) {
        return transactions.stream()
                .filter(t -> t.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public List<Transaction> getMonthlySummary(int year, int month) {
        return transactions.stream()
                .filter(t -> t.getDate().getYear() == year && t.getDate().getMonthValue() == month)
                .collect(Collectors.toList());
    }
}
