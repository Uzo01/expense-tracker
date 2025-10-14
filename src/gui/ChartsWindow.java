package gui;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import services.TransactionManager;

public class ChartsWindow {

    public static void show(TransactionManager manager) {
        Stage stage = new Stage();
        stage.setTitle("Expense Chart");

        double income = manager.getTotalIncome();
        double expense = manager.getTotalExpense();

        PieChart chart = new PieChart();
        chart.getData().add(new PieChart.Data("Income", income));
        chart.getData().add(new PieChart.Data("Expense", expense));

        Scene scene = new Scene(chart, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}
