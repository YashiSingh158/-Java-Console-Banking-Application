package Banking_Application;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (loggedInUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> register(scanner);
                    case 2 -> login(scanner);
                    case 3 -> {
                        System.out.println("Thank you,Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice.Try again.");
                }
            }
            else {
                System.out.println("\nWelcome, " + loggedInUser.getUsername());
                System.out.println("1. Open Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. View Statement");
                System.out.println("5. Check Balance");
                System.out.println("6. Add Monthly Interest (for Savings Accounts)");
                System.out.println("7. Logout\n");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> openAccount(scanner);
                    case 2 -> deposit(scanner);
                    case 3 -> withdraw(scanner);
                    case 4 -> viewStatement(scanner);
                    case 5 -> checkBalance(scanner);
                    case 6 -> applyMonthlyInterest(scanner);
                    case 7 -> logout();
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists.");
                return;
            }
        }

        users.add(new User(username, password));
        System.out.println("Registration successful!");
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful!");
                return;
            }
        }

        System.out.println("Invalid credentials.");
    }

    private static void openAccount(Scanner scanner) {
        System.out.print("Enter account holder name: ");
        String holderName = scanner.nextLine();
        System.out.print("Enter account type (savings/checking): ");
        String accountType = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine();

        String accountNumber = "AC" + (int) (Math.random() * 10000);
        Account account = new Account(accountNumber, holderName, accountType, initialDeposit);
        loggedInUser.addAccount(account);

        System.out.println("Account created successfully! Account Number: " + accountNumber);
    }

    private static void deposit(Scanner scanner) {
        Account account = chooseAccount(scanner);
        if (account == null) return;

        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful.");
    }

    private static void withdraw(Scanner scanner) {
        Account account = chooseAccount(scanner);
        if (account == null) return;

        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private static void viewStatement(Scanner scanner) {
        Account account = chooseAccount(scanner);
        if (account == null) return;

        System.out.println("Selected Account: " + account.getAccountNumber());
        System.out.println("Transaction History:\n");
        for (Transaction t : account.getTransactions()) {
            System.out.println(t);
        }
    }

    private static void checkBalance(Scanner scanner) {
        Account account = chooseAccount(scanner);
        if (account == null) return;

        System.out.println("Current Balance: " + account.getBalance());
    }

    private static Account chooseAccount(Scanner scanner) {
        ArrayList<Account> accounts = loggedInUser.getAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return null;
        }

        System.out.println("Select an account:");
//        If accounts exist, the method displays them with their account numbers, it retrieves all accounts associated with the logged-in use
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i).getAccountNumber());
        }
        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > accounts.size()) {
            System.out.println("Invalid choice.");
            return null;
        }

        return accounts.get(choice - 1);
    }

    private static void applyMonthlyInterest(Scanner scanner) {
        for (Account account : loggedInUser.getAccounts()) {
            account.addMonthlyInterest(0.02);  // fixed interest rate = 2% per month
        }
    }


    private static void logout() {
        loggedInUser = null;
        System.out.println("Logged out.");
    }
}

