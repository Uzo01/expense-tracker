package gui;

import javafx.scene.control.*;
import services.TransactionManager;

public class TransactionDialog {

    public static void show(TransactionManager manager, String type) {
        TextInputDialog amountDialog = new TextInputDialog();
        amountDialog.setTitle("Add " + type);
        amountDialog.setHeaderText("Enter amount:");
        String amountInput = amountDialog.showAndWait().orElse("");
        if (amountInput.isEmpty()) return;

        try {
            double amount = Double.parseDouble(amountInput);

            TextInputDialog descDialog = new TextInputDialog();
            descDialog.setTitle("Description");
            descDialog.setHeaderText("Enter a description:");
            String description = descDialog.showAndWait().orElse("No description");

            TextInputDialog catDialog = new TextInputDialog();
            catDialog.setTitle("Category");
            catDialog.setHeaderText("Enter a category:");
            String category = catDialog.showAndWait().orElse("General");

            manager.addTransaction(type, amount, description, category);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Transaction added successfully!");
            alert.showAndWait();

        } catch (NumberFormatException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid Input");
            error.setHeaderText("Please enter a valid number for amount.");
            error.showAndWait();
        }
    }
}
