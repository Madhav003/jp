/*
Create a class called BankAccount, it has following data members:
integer accountNumber, String CustomerName, String
AccountType ( ‘Savings’ or ‘Current’), float balance Member
Functions of the class are : void deposit (float amt); void withdraw
(float amt); float getBalance(); deposit(float amt) method allows
you to credit an amount into the current balance. If the amount is
negative, throw an exception NegativeAmount to block the
operation from being performed.
withdraw (float amt) method allows you to debit an amount
from the current balance. Please ensure a minimum balance of
Rs. 1000/- in the account for the savings account and Rs.
5000/- for the current account, else throw an exception
Insufficient Funds and block the withdrawal operation. Also
throw an exception Negative Amount to block the operation
from being performed if the amt parameter passed to this
function is negative.
getBalance () method returns the current balance.
A constructor to this class will allow you to pass the account
number, customer name, account type and opening balance.
The minimum opening balance for a savings account is 1000
Rs and for current account, it is 5000 Rs. If the amount entered
is less than that, raise the LowBalance exception and prompt
the user to enter the opening balance again.
Write appropriate custom exception classes.
*/
import java.util.Scanner;

class NegativeAmount extends Exception {
    NegativeAmount(String message) {
        super(message);
    }
}

class InsufficientFunds extends Exception {
    InsufficientFunds(String message) {
        super(message);
    }
}

class balanceException extends Exception {
    balanceException(String message) {
        super(message);
    }
}

class BankAccount {
    int accno;
    String custname;
    String acctype;
    float bal;

    BankAccount(int accno, String custname, String acctype, float bal) throws balanceException {
        this.accno = accno;
        this.custname = custname;
        this.acctype = acctype;

        if (!acctype.equals("Savings") && !acctype.equals("Current")) {
            throw new balanceException("Invalid account type. Must be 'Savings' or 'Current'");
        }

        if (acctype.equals("Savings") && bal < 1000) {
            throw new balanceException("Minimum opening balance for Savings account is Rs. 1000");
        } 
        else if (acctype.equals("Current") && bal < 5000) {
            throw new balanceException("Minimum opening balance for Current account is Rs. 5000");
        }

        this.bal = bal;
    }

    void deposit(float amt) throws NegativeAmount {
        if (amt < 0) {
            throw new NegativeAmount("Amount cannot be negative");
        }
        bal = bal + amt;
        System.out.println("Deposit successful");
    }

    void withdraw(float amt) throws NegativeAmount, InsufficientFunds {
        if (amt < 0) {
            throw new NegativeAmount("Amount cannot be negative");
        }

        if (acctype.equals("Savings")) {
            if (bal - amt < 1000) {
                throw new InsufficientFunds("Minimum balance of Rs. 1000 must be maintained");
            }
        } 
        else if (acctype.equals("Current")) {
            if (bal - amt < 5000) {
                throw new InsufficientFunds("Minimum balance of Rs. 5000 must be maintained");
            }
        }

        bal = bal - amt;
        System.out.println("Withdrawal successful");
    }

    float getBalance() {
        return bal;
    }
}

public class Exp9b {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount b = null;

        System.out.print("Enter account number: ");
        int accno = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter customer name: ");
        String custname = sc.nextLine();

        System.out.print("Enter account type (Savings/Current): ");
        String acctype = sc.nextLine();

        while (true) {
            try {
                System.out.print("Enter opening balance: ");
                float bal = sc.nextFloat();
                b = new BankAccount(accno, custname, acctype, bal);
                break;
            } catch (balanceException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter opening balance again.");
            }
        }

        try {
            System.out.print("Enter amount to deposit: ");
            float d = sc.nextFloat();
            b.deposit(d);
        } catch (NegativeAmount e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.print("Enter amount to withdraw: ");
            float w = sc.nextFloat();
            b.withdraw(w);
        } catch (NegativeAmount | InsufficientFunds e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Current Balance: Rs. " + b.getBalance());

        sc.close();
    }
}