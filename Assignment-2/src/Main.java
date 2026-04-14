import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        mockAccounts();
        LinkedList<BankAccount> accounts = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        Stack transactionHistory = new Stack();
        Queue<Bill> billQueue = new Queue<>();
        Queue<String> registrationQueue = new Queue<>();

        userInterface(scanner, accounts, transactionHistory, billQueue, registrationQueue);
    }

    public static void mockAccounts() {

        BankAccount[] staticAccounts = new BankAccount[3];

        staticAccounts[0] = new BankAccount("1001", "Alice", 500.0);
        staticAccounts[1] = new BankAccount("1002", "Bob", 1200.0);
        staticAccounts[2] = new BankAccount("1003", "Charlie", 750.0);

        for (BankAccount acc : staticAccounts) {
            System.out.println(acc);
        }
    }

    public static void userInterface(Scanner scanner, LinkedList<BankAccount> accounts, Stack transactionHistory, Queue<Bill> billQueue, Queue<String> registrationQueue) {
        while (true) {
            System.out.println("\n1. Enter Bank");
            System.out.println("2. Enter ATM");
            System.out.println("3. Admin Area");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Enter your name to apply for an account:");
                String name = scanner.nextLine();
                registrationQueue.enqueue(name);
                System.out.println("Application sent! Wait for admin approval.");
            } else if (choice == 2) {
                accountOperations(accounts, scanner, transactionHistory, billQueue);
            } else if (choice == 3) {
                adminPanel(registrationQueue, billQueue, accounts, scanner);
            } else if (choice == 4) {
                break;
            }
        }
    }

    public static void accountOperations(LinkedList<BankAccount> accounts, Scanner scanner, Stack transactionHistory, Queue<Bill> billQueue) {
        System.out.println("Enter bank account username:");
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
            System.out.println("\nManaging account: " + foundAcc.getUsername());
            System.out.println("1. Deposit\n" +
                    "2. Withdraw\n" +
                    "3. Transaction History\n" +
                    "4. Undo Last Transaction\n" +
                    "5. Bills\n" +
                    "6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter amount to deposit: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                foundAcc.setBalance(foundAcc.getBalance() + amount);
                transactionHistory.push(amount + " to " + foundAcc.getUsername());
                System.out.println("Success. Balance: " + foundAcc.getBalance());
            } else if (choice == 2) {
                System.out.print("Enter amount to withdraw: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                if (foundAcc.getBalance() >= amount) {
                    foundAcc.setBalance(foundAcc.getBalance() - amount);
                    transactionHistory.push("Withdraw: " + amount + " from " + foundAcc.getUsername());
                    System.out.println("Success. Balance: " + foundAcc.getBalance());
                } else {
                    System.out.println("Not enough funds");
                }
            } else if (choice == 3) {
                if (transactionHistory.isEmpty()) {
                    System.out.println("No transactions.");
                } else {
                    for (int i = transactionHistory.size() - 1; i >= 0; i--) {
                        System.out.println(transactionHistory.get(i));
                    }
                }
            } else if (choice == 4) {
                if (!transactionHistory.isEmpty()) {
                    String lastAction = transactionHistory.pop();
                    if (lastAction.startsWith("Deposited:")) {
                        double amount = Double.parseDouble(lastAction.substring(11, lastAction.indexOf(" to ")).trim());
                        foundAcc.setBalance(foundAcc.getBalance() - amount);
                    } else if (lastAction.startsWith("Withdraw:")) {
                        double amount = Double.parseDouble(lastAction.substring(10, lastAction.indexOf(" from")).trim());
                        foundAcc.setBalance(foundAcc.getBalance() + amount);
                    }
                    System.out.println("Undo successful");
                }
            } else if (choice == 5) {
                accountBillManagement(scanner, billQueue, name, accounts);
            } else if (choice == 6) {
                break;
            }
        }
    }

    public static void accountBillManagement(Scanner scanner, Queue<Bill> billQueue, String name, LinkedList<BankAccount> accounts) {
        while (true) {
            System.out.println(
                    "1. Add bill payment request\n" +
                            "2. My bills\n" +
                            "3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Enter bill type:");
                String type = scanner.nextLine();
                System.out.println("Enter bill amount:");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                Bill newBill = new Bill(type, amount, name);
                billQueue.enqueue(newBill);
                System.out.println("Bill added to processing queue");
            } else if (choice == 2) {
                billQueue.display();
            } else if (choice == 3) {
                break;
            }
        }
    }

    public static void adminPanel(Queue<String> regQueue, Queue<Bill> billQueue, LinkedList<BankAccount> accounts, Scanner scanner) {
        while (true) {
            System.out.println("1. Approve Registration (" + regQueue.size() + ")");
            System.out.println("2. Approve Bills (" + billQueue.size() + ")");
            System.out.println("3. Back");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                if (regQueue.isEmpty()) {
                    System.out.println("No requests.");
                } else {
                    String name = regQueue.peek();
                    System.out.println("User: " + name);
                    System.out.println("Enter account number:");
                    String accNum = scanner.nextLine();
                    System.out.println("Enter initial balance:");
                    double balance = scanner.nextDouble();
                    scanner.nextLine();

                    accounts.add(new BankAccount(accNum, name, balance));
                    regQueue.dequeue();
                    System.out.println("Account approved");
                }
            } else if (choice == 2) {
                if (billQueue.isEmpty()) {
                    System.out.println("No bills.");
                } else {
                    Bill bill = billQueue.peek();
                    System.out.println(bill);
                    System.out.println("1. Approve\n2. Reject");
                    int act = scanner.nextInt();
                    scanner.nextLine();

                    if (act == 1) {
                        BankAccount owner = null;
                        for (BankAccount acc : accounts) {
                            if (acc.getUsername().equalsIgnoreCase(bill.getOwner())) {
                                owner = acc;
                                break;
                            }
                        }
                        if (owner != null && owner.getBalance() >= bill.getAmount()) {
                            owner.setBalance(owner.getBalance() - bill.getAmount());
                            bill.status = BillStatus.APPROVED;
                            billQueue.dequeue();
                            System.out.println("Paid");
                        } else {
                            bill.status = BillStatus.REJECTED;
                            billQueue.dequeue();
                            System.out.println("Rejected (Funds)");
                        }
                    } else {
                        bill.status = BillStatus.REJECTED;
                        billQueue.dequeue();
                        System.out.println("Rejected");
                    }
                }
            } else if (choice == 3) {
                break;
            }
        }
    }
}