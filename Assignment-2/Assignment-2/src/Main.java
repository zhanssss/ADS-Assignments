import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<BankAccount> accounts = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        Stack<String> transactionHistory = new Stack<>();

        while (true) {
            System.out.println(
                    "1.Add account \n" +
                            "2.Display All\n" +
                            "3.Search\n" +
                            "4.Account Operations\n " +
                            "5.Exit"
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
                accountOperations(accounts, scanner, transactionHistory);
            } else if (choice == 5) {
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

    public static void accountOperations(LinkedList<BankAccount> accounts, Scanner scanner, Stack transactionHistory) {
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
                            "4. Undo Last Transaction\n " +
                            "5. Exit"
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
                    // Извлекаем последнюю запись из стека
                    String lastAction = (String) transactionHistory.pop();

                    // Разбираем строку, чтобы понять действие и сумму
                    if (lastAction.startsWith("Deposited:")) {
                        // Если был депозит — вычитаем деньги обратно
                        // Извлекаем число между "Deposited: " и "to"
                        double amount = Double.parseDouble(lastAction.substring(11, lastAction.indexOf("to")).trim());
                        foundAcc.setBalance(foundAcc.getBalance() - amount);
                        System.out.println("Undo successful. Deposit canceled. Current balance: " + foundAcc.getBalance());

                    } else if (lastAction.startsWith("Withdraw:")) {
                        // Если было снятие — возвращаем деньги на счет
                        double amount = Double.parseDouble(lastAction.substring(10, lastAction.indexOf("from")).trim());
                        foundAcc.setBalance(foundAcc.getBalance() + amount);
                        System.out.println("Undo successful. Withdrawal canceled. Current balance: " + foundAcc.getBalance());
                    } else if (choice == 5) {
                        break;
                    }
                }

            }

        }
    }
}