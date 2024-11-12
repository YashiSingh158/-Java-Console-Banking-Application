package Banking_Application;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private static int idCounter = 1;
    private int transactionId;
    private String type;  // Deposit or Withdrawal
    private double amount;
    private String date;

    public Transaction(String type, double amount) {
        this.transactionId = idCounter++;
        this.type = type;
        this.amount = amount;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + ", Type: " + type + ", Amount: " + amount + ", Date: " + date;
    }
}

