package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.TransactionManager;
import javafx.scene.control.ListView;
import models.Transaction;

public class MainGUI extends Application {

    private TransactionManager manager;

    @Override
    public void start(Stage primaryStage) {
        manager = new TransactionManager();

        primaryStage.setTitle("Expense Tracker - GUI");

        Button addIncome = new Button("Add Income");
        Button addExpense = new Button("Add Expense");
        Button showAll = new Button("Show Transactions");
        Button showCharts = new Button("Show Chart");
        Button exit = new Button("Exit");

        addIncome.setOnAction(e -> TransactionDialog.show(manager, "income"));
        addExpense.setOnAction(e -> TransactionDialog.show(manager, "expense"));
        showAll.setOnAction(e -> handleShowAll());
        showCharts.setOnAction(e -> ChartsWindow.show(manager));
        exit.setOnAction(e -> {
            primaryStage.close();
            System.exit(0);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(addIncome, addExpense, showAll, showCharts, exit);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleShowAll() {
        Stage stage = new Stage();
        stage.setTitle("All Transactions");

        ListView<String> listView = new ListView<>();
        for (Transaction t : manager.getAllTransactions()) {
            listView.getItems().add(t.toString());
        }

        Scene scene = new Scene(listView, 500, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
