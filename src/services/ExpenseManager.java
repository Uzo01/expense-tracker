package services;

import models.Transaction;
import utils.FileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class ExpenseManager {
    private List <Transaction> transaction = new ArrayList<>();
    private String filePath = "data/transactions.csv";

    public ExpenseManager() {
        // Load from File on init
        transaction = FileHandler.loadTransactions(filePath);
    }

    public void addTransaction(double amount, String type, String description, String category){
        if (amount <= 0){
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (!type.equals("income") && !type.equals("expense")){
            throw new IllegalArgumentException("Type must be 'income' or 'expense'");
        }
        Transaction t = new Transaction(amount, type, description, category);
        transaction.add(t);
        FileHandler.saveTransactions(transaction, filePath);
    }

    public double getBalance(){
        double income = transaction.stream()
                .filter(t -> t.getType().equals("income"))
                .mapToDouble(Transaction::getAmount)
                .sum();
        double expense = transaction.stream()
                .filter(t -> t.getType().equals("expense"))
                .mapToDouble(Transaction::getAmount)
                .sum();
        return income - expense;
    }

    public List<Transaction> getTransactionsByCategory(String category){
        return transaction.stream()
                .filter(t -> t.getCategory().equalsIgnoreCase(category))
                .collect(Collector.toList());
    }

    public List<Transaction> getAllTransactions(){
        // Implement filtering by month, calculate totals
        // For now, placeholder
        return "Monthly summary not implemented yet.";
    }



}
