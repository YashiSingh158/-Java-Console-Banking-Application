package Banking_Application;
import java.util.ArrayList;
import java.time.LocalDate;

public class Account {
    private String accountNumber;
    private String accountHolderName;
    private String accountType;  // savings or checking
    private double balance;
    private ArrayList<Transaction> transactions;
//    To ensure interest is added once a month
    private LocalDate lastInterestDate;

    public Account(String accountNumber, String accountHolderName, String accountType, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
//        Initialize with account creation date
        this.lastInterestDate = LocalDate.now();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        Transaction transaction = new Transaction("Deposit", amount);
        transactions.add(transaction);
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
            return true;
        }
        return false;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addMonthlyInterest(double interestRate)
    {
        if (accountType.equalsIgnoreCase("savings"))
        {
            LocalDate now = LocalDate.now();
            if(lastInterestDate.plusMonths(1).isBefore(now) || lastInterestDate.plusMonths(1).isEqual(now))
            {
                double interest = balance * interestRate;
                balance += interest;
                transactions.add(new Transaction("Monthly Interest", interest));
                lastInterestDate = now;
                System.out.println("Interest added to account: " + accountNumber);
            }
            else
            {
                System.out.println("Interest already added this month for account:"+ accountNumber);
            }
        }
    }
}

