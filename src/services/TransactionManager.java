package services;

import models.Transaction;
import utils.FileHandler;
import java.util.List;
import java.util.ArrayList;

public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();
    private final String filePath = "transactions.csv";

    public TransactionManager() {
        transactions = FileHandler.loadTransactions(filePath);
    }

    public void addTransaction(String type, double amount, String description, String category) {
        Transaction t = new Transaction(type, amount, description, category);
        transactions.add(t);
        FileHandler.saveTransactions(transactions, filePath);
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public double getTotalIncome() {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("income"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpense() {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("expense"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpense();
    }
}
