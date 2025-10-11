package utils;

import java.util.List;
import java.io.*;
import java.util.ArrayList;
import models.Transaction;

public class FileHandler {

    public static void saveTransactions(List<Transaction> transactions, String filePath) {
        try {
            // Ensure folder exists
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Transaction t : transactions) {
                    writer.write(t.toString());
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.err.println("❌ Error saving file: " + e.getMessage());
        }
    }

    public static List<Transaction> loadTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            // No transactions file yet — return empty list
            return transactions;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    transactions.add(Transaction.fromCSV(line));
                } catch (Exception e) {
                    System.err.println("⚠️ Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Error loading file: " + e.getMessage());
        }

        return transactions;
    }
}
