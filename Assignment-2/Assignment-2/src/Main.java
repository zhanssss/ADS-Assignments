import java.util.Scanner;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        LinkedList<BankAccount> accounts = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        Stack<String> transactionHistory = new Stack<>();
        Queue<Bill> billQueue = new LinkedList<>();
        Queue<String> registrationQueue = new LinkedList<>();

        while (true) {
            System.out.println(
                    "1.Add account \n" +
                            "2.Display All\n" +
                            "3.Search\n" +
                            "4.Account Operations\n " +
                            "5.Admin Panel\n" +
                            "6.Exit"
            );
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                addAccount(accounts, scanner);
            } else if (choice == 2) {
                dispayAll(accounts);
            } else if (choice == 3) {
                searchAccount(accounts, scanner);
            } else if (choice == 4) {
                accountOperations(accounts, scanner, transactionHistory, billQueue);
            } else if (choice == 5) {
                adminPanel();
            } else if (choice == 6) {
                break;
            }
        }
    }

    public static void addAccount(LinkedList<BankAccount> accounts, Scanner scanner) {
        System.out.println("Enter account number: ");
        String accountNumber = scanner.nextLine();

        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        BankAccount newAcc = new BankAccount(accountNumber, username, balance);
        accounts.add(newAcc);

        System.out.println("Account added successfully!");
    }

    public static void dispayAll(LinkedList<BankAccount> accounts) {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found");
        } else {
            for (BankAccount acc : accounts) {
                System.out.println(acc);
            }
        }
    }

    public static void searchAccount(LinkedList<BankAccount> accounts, Scanner scanner) {
        System.out.println("Enter username to search: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (BankAccount acc : accounts) {
            if (acc.getUsername().equalsIgnoreCase(name)) {
                System.out.println("Account found: " + acc);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Account with username'" + name + "'not found.");
        }
    }

    public static void accountOperations(LinkedList<BankAccount> accounts, Scanner scanner, Stack transactionHistory, Queue<Bill> billQueue) {
        System.out.println("Enter bank account username");
        String name = scanner.nextLine();
        BankAccount foundAcc = null;
        for (BankAccount acc : accounts) {
            if (acc.getUsername().equalsIgnoreCase(name)) {
                foundAcc = acc;
                break;
            }
        }
        if (foundAcc == null) {
            System.out.println("Account not found");
            return;
        }
        while (true) {
            System.out.println("Managing account: " + foundAcc.getUsername());
            System.out.println(
                    "1. Deposite\n " +
                            "2. Withdraw\n" +
                            "3. Transaction History\n " +
                            "4. Undo Last Transaction\n" +
                            "5. Bills" +
                            "6. Exit"
            );
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter amount to deposit: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                foundAcc.setBalance(foundAcc.getBalance() + amount);
                System.out.println("Successfully deposited, new balance is: " + foundAcc.getBalance());
                transactionHistory.push("Deposited: " + amount + "to" + foundAcc.getUsername());
            } else if (choice == 2) {
                System.out.print("Enter amount to withdraw: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                if (foundAcc.getBalance() >= amount) {
                    foundAcc.setBalance(foundAcc.getBalance() - amount);
                    System.out.println("Successfully withdraw, new balance is: " + foundAcc.getBalance());
                    transactionHistory.push("Withdraw: " + amount + "from" + foundAcc.getUsername());
                } else {
                    System.out.println("Not enough funds");
                }
            } else if (choice == 3) {
                if (transactionHistory.isEmpty()) {
                    System.out.println("No transaction yet.");
                } else {
                    System.out.println("Recent Transactions (Last to First): ");
                    for (int i = transactionHistory.size() - 1; i >= 0; i--) {
                        System.out.println(transactionHistory.get(i));
                    }
                }
            } else if (choice == 4) {
                if (transactionHistory.isEmpty()) {
                    System.out.println("Nothing to undo.");
                } else {
                    String lastAction = (String) transactionHistory.pop();
                    if (lastAction.startsWith("Deposited:")) {
                        double amount = Double.parseDouble(lastAction.substring(11, lastAction.indexOf(" to ")).trim());
                        foundAcc.setBalance(foundAcc.getBalance() - amount);
                        System.out.println("Deposit removed");

                    } else if (lastAction.startsWith("Withdraw:")) {
                        double amount = Double.parseDouble(lastAction.substring(10, lastAction.indexOf("from")).trim());
                        foundAcc.setBalance(foundAcc.getBalance() + amount);
                        System.out.println("Withdraw removed");
                    }
                }
            } else if (choice == 5) {
                accountBillManagement(scanner, billQueue, name);
            } else if (choice == 6) {
                break;
            }
        }
    }

    public static void accountBillManagement(Scanner scanner, Queue<Bill> billQueue, String name) {
        System.out.println("Choose bill action");
        int choice = scanner.nextInt();

        while (true) {
            System.out.println(
                    "1.Add bill payment request\n" +
                            "2.My bills\n" +
                            "3.Exit"
            );

            if(choice == 1){
                System.out.println("Enter bill type:");
                String type = scanner.nextLine();
                System.out.println("Enter bill amount");
                double amount = scanner.nextDouble();
                Bill newBill = new Bill(type, amount, name);

                billQueue.add(newBill);

                System.out.println("Bill added");

            } else if (choice == 2){
                System.out.println("Your bills");
                boolean hasBills = false;

                for(Bill bill : billQueue){
                    if(bill.getOwner().equalsIgnoreCase(name)){
                        System.out.println(bill);
                        hasBills = true;
                    }
                }

                if(!hasBills) System.out.println("No bills");

            } else if (choice == 3){
                break;
            }
        }


    }

    public static void adminPanel() {

    }
}