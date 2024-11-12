package Banking_Application;
import java.util.*;

public class User {
    private String username;
    private String password;
    private ArrayList<Account> accounts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    public void addAccount(Account account) {
        accounts.add(account);
    }
}
