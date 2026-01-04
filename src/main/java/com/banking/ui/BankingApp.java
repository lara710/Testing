package com.banking.ui;

import com.banking.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;



public class BankingApp extends Application {

    private Account account;
    private Account account2;
    private ClientController controller;

    private Label balanceLabel;
    private Label statusLabel;
    private Label messageLabel;

    @Override
    public void start(Stage stage) {
        account = new Account(1000, "Verified");
        account2 = new Account(200,"Verified");
        controller = new ClientController();

        Label titleLabel = new Label("Client Dashboard");
        titleLabel.setFont(Font.font(18));
        titleLabel.setStyle("-fx-text-fill: #1f4fd8; -fx-font-weight: bold;");

        statusLabel = new Label("Status: " + account.getStatus());
        balanceLabel = new Label("Balance: " + account.getBalance());
        messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: darkred;");

        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");
        amountField.setMaxWidth(200);

        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");
        Button transfer = new Button("Transfer");
        depositBtn.setStyle(
                "-fx-background-color: #1f4fd8; -fx-text-fill: white; -fx-font-weight: bold;"
        );
        withdrawBtn.setStyle(
                "-fx-background-color: #1f4fd8; -fx-text-fill: white; -fx-font-weight: bold;"
        );
        transfer.setStyle(
                "-fx-background-color: #1f4fd8; -fx-text-fill: white; -fx-font-weight: bold;"
        );
        statusLabel.setStyle("-fx-text-fill: #333333;");
        balanceLabel.setStyle("-fx-text-fill: #333333; -fx-font-weight: bold;");
        messageLabel.setStyle("-fx-text-fill: #b00020;");


        depositBtn.setPrefWidth(100);
        withdrawBtn.setPrefWidth(100);
        transfer.setPrefWidth(100);
        depositBtn.setOnAction(e -> handleAction("DEPOSIT", amountField));
        withdrawBtn.setOnAction(e -> handleAction("WITHDRAW", amountField));
        transfer.setOnAction(e-> handleTransfer(amountField));
        updateButtonStates(depositBtn, withdrawBtn,transfer);

        HBox buttonBox = new HBox(10, depositBtn, withdrawBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(15,
                titleLabel,
                statusLabel,
                balanceLabel,
                amountField,
                buttonBox,
                transfer,
                messageLabel
        );

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #eef3fb;");

        stage.setScene(new Scene(root, 350, 300));
        stage.setTitle("Banking System");
        stage.show();
    }


    private void handleAction(String type, TextField amountField) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String response = controller.handleTransactionRequest(account, type, amount);
            messageLabel.setText(response);
            balanceLabel.setText("Balance: " + account.getBalance());
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid amount input");
        }
    }


    private void updateButtonStates(Button depositBtn, Button withdrawBtn, Button transfer) {
        String status = account.getStatus().trim().toUpperCase();
        if (status.equals("SUSPENDED")) {
            withdrawBtn.setDisable(true);
            transfer.setDisable(true);
        }
        if (status.equals("CLOSED")) {
            depositBtn.setDisable(true);
            withdrawBtn.setDisable(true);
            transfer.setDisable(true);
        }
    }
    private void handleTransfer(TextField amountField) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            Account target = account2;
            String response = controller.handleTransferRequest(account, target, amount);
            messageLabel.setText(response);
            balanceLabel.setText("Balance: " + account.getBalance());
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid amount input");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
