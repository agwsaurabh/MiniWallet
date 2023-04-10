import main.java.model.Transaction;
import main.java.services.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean loop = true;
        UserService userService = new UserService();

        while(loop) {

            System.out.println("Hello and welcome to PhonePe Wallet!");
            System.out.println("1. To Register User, Please press 1");
            System.out.println("2. To Know your Wallet Balance, Please press 2");
            System.out.println("3. To Recharge Wallet, Please press 3");
            System.out.println("4. To send Money to someone, Please press 4");
            System.out.println("5. Press 5 to close the program");

            Scanner sc = new Scanner(System.in);
            int operation = sc.nextInt();
            sc.nextLine();

            //Switch expression
            switch (operation) {
                //Case statements
                case 1 -> {
                    System.out.println("Please enter the following details");
                    System.out.print("Please enter user's phone number : ");
                    String phone = sc.nextLine();
                    System.out.print("Please enter username : ");
                    String username = sc.nextLine();
                    System.out.print("Please enter password : ");
                    String password = sc.nextLine();
                    userService.registerUser(username, phone, password);
                }
                case 2 -> {
                    System.out.print("Please enter user's phone number : ");
                    String phone = sc.nextLine();
                    userService.getWalletBalance(phone);
                }
                case 3 -> {
                    System.out.print("Please enter user's phone number : ");
                    String phone = sc.nextLine();
                    System.out.print("Please enter the amount : ");
                    Double amount = sc.nextDouble();
                    System.out.println("Please select Payment Mode : ");
                    System.out.println("1. Please press 1 for UPI");
                    System.out.println("2. Please press 2 for Debit Card");
                    System.out.println("3. Please press 3 for Credit Card");
                    System.out.println("4. Please press 4 for Net Banking");
                    int mode = sc.nextInt();
                    Transaction.PaymentModes paymentMode = Transaction.PaymentModes.WALLET;
                    switch (mode) {
                        case 1 -> {
                            paymentMode = Transaction.PaymentModes.UPI;
                        }
                        case 2 -> {
                            paymentMode = Transaction.PaymentModes.DEBIT_CARD;
                        }
                        case 3 -> {
                            paymentMode = Transaction.PaymentModes.CREDIT_CARD;
                        }
                        case 4 -> {
                            paymentMode = Transaction.PaymentModes.NET_BANKING;
                        }
                    }
                    userService.rechargeWallet(phone, amount, paymentMode);
                    }
                case 4 -> {
                    System.out.print("Please enter sender's phone number : ");
                    String sender = sc.nextLine();
                    System.out.print("Please enter receiver's phone number : ");
                    String receiver = sc.nextLine();
                    System.out.print("Please enter the amount : ");
                    Double amount = sc.nextDouble();

                    userService.sendMoney(sender, receiver, amount);
                }
                case 5 -> {
                    loop = false;
                }
            }

        }
    }
}
