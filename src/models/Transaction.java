package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private double amount;
    private String type;
    private String description;
    private LocalDateTime date;
    private String category;

    public Transaction(double amount, String type, String description, String category) {
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.category = category;
        this.date = LocalDateTime.now();
    }

    // Getters and Setters
    public double getAmount() { return amount; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public LocalDateTime getDate() { return date; }
    public String getCategory() { return category; }

    // ToString for easy printing / saving to CSV
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return String.format("%s,%s,%.2f,%s,%s",
                date.format(formatter),
                type,
                amount,
                description,
                category);
    }

    // From CSV string (for Loading)
    public static Transaction fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        LocalDateTime date = LocalDateTime.parse(parts[0], DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        String type = parts[1];
        double amount = Double.parseDouble(parts[2]);
        String description = parts[3];
        String category = parts.length > 4 ? parts[4] : "";

        Transaction t = new Transaction(amount, type, description, category);
        t.date = date; // Override with saved date
        return t;
    }
}
