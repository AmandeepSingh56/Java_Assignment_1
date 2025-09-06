import java.util.Scanner;

class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    // Constructor
    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully! New Balance: " + balance);
        } else {
            System.out.println("Invalid amount. Deposit must be positive.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawal successful! Remaining Balance: " + balance);
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Invalid amount. Withdrawal must be positive.");
        }
    }

    // Display account details
    public void displayAccountDetails() {
        System.out.println("\nAccount Details:");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    // Update contact details
    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated successfully!");
    }

    // Getter
    public int getAccountNumber() {
        return accountNumber;
    }
}

class UserInterface {
    private Account[] accounts;
    private int accountCount;
    private Scanner sc;
    private int nextAccountNumber = 1001; // Auto-increment account numbers

    public UserInterface(int size) {
        accounts = new Account[size];
        accountCount = 0;
        sc = new Scanner(System.in);
    }

    // Create new account
    public void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double balance = sc.nextDouble();
        sc.nextLine(); // consume newline
        System.out.print("Enter email address: ");
        String email = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        accounts[accountCount] = new Account(nextAccountNumber, name, balance, email, phone);
        System.out.println("Account created successfully with Account Number: " + nextAccountNumber);
        nextAccountNumber++;
        accountCount++;
    }

    // Deposit money
    public void performDeposit() {
        Account acc = findAccount();
        if (acc != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            acc.deposit(amount);
        }
    }

    // Withdraw money
    public void performWithdrawal() {
        Account acc = findAccount();
        if (acc != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            acc.withdraw(amount);
        }
    }

    // Show account details
    public void showAccountDetails() {
        Account acc = findAccount();
        if (acc != null) {
            acc.displayAccountDetails();
        }
    }

    // Update contact
    public void updateContact() {
        Account acc = findAccount();
        if (acc != null) {
            System.out.print("Enter new email: ");
            String email = sc.nextLine();
            System.out.print("Enter new phone number: ");
            String phone = sc.nextLine();
            acc.updateContactDetails(email, phone);
        }
    }

    // Find account by number
    private Account findAccount() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accNo) {
                return accounts[i];
            }
        }
        System.out.println("Account not found!");
        return null;
    }

    // Menu
    public void mainMenu() {
        int choice;
        do {
            System.out.println("\nWelcome to the Banking Application!");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: createAccount(); break;
                case 2: performDeposit(); break;
                case 3: performWithdrawal(); break;
                case 4: showAccountDetails(); break;
                case 5: updateContact(); break;
                case 6: System.out.println("Exiting... Thank you!"); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }
}

// Main class
public class BankingApplication {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface(100); // Can handle 100 accounts
        ui.mainMenu();
    }
}