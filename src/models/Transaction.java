package models;

public class Transaction {
    private String type; // income or expense
    private double amount;
    private String description;
    private String category;

    public Transaction(String type, double amount, String description, String category) {
        this.type = type;
        this.amount = amount;
        this.description = category;
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String toCSV() {
        return type + "," + amount + "," + description + "," + category;
    }

    public static Transaction fromCSV(String line) {
        String[] p = line.split(",", 4);
        if (p.length == 4) {
            return new Transaction(p[0], Double.parseDouble(p[1]), p[2], p[3]);
        }
        return null;
    }

    @Override
    public String toString() {
        return type.toUpperCase() + " | £" + amount + " | " + category + " - " + description;
    }
}
