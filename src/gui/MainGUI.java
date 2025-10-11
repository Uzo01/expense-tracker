package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import services.ExpenseManager;
import java.io.IOException;

public class MainGUI extends Application {

    private ExpenseManager manager = new ExpenseManager();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Expense Tracker - GUI");

        Button addIncome = new Button("Add Income");
        Button addExpense = new Button("Add Expense");
        Button showAll = new Button("Show All Transactions");
        Button exit = new Button("Exit");

        addIncome.setOnAction(e -> handleAddIncome());
        addExpense.setOnAction(e -> handleAddExpense());
        showAll.setOnAction(e -> handleShowAll());
        exit.setOnAction(e -> {
            primaryStage.close();
            System.exit(0); // ensures full exit
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(addIncome, addExpense, showAll, exit);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // --- Handlers (we’ll make them connect to your logic later) ---
    private void handleAddIncome() {
        System.out.println("Income button clicked");
    }

    private void handleAddExpense() {
        System.out.println("Expense button clicked");
    }

    private void handleShowAll() {
        try {
            manager.getAllTransactions().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 👇 This is your main entry point!
    public static void main(String[] args) {
        launch(args);
    }
}
