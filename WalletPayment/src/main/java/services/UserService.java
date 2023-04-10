package main.java.services;

import main.java.model.Transaction;
import main.java.model.User;
import main.java.model.Wallet;

import java.util.*;

public class UserService {

    public Map<String, User> userMap = new HashMap<>();

    public void registerUser(String username, String phone, String password) {
        if(userMap.containsKey(phone)) {
            System.out.println("Phone Number is already in use");
            return;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        Wallet wallet = new Wallet(0d);
        user.setWallet(wallet);
        user.setTransactions(new ArrayList<>());

        userMap.put(phone, user);
        System.out.println("User has been successfully registered");
        userMap.forEach((key, value) -> System.out.println(key));
    }

    public void getWalletBalance(String phone) {
        if(!userMap.containsKey(phone)) {
            System.out.println("No Such User Exists");
            return;
        }
        System.out.println("User's Wallet balance is : " + userMap.get(phone).displayBalance());
    }

    public void rechargeWallet(String phone, Double amount, Transaction.PaymentModes paymentMode) {
        if(!userMap.containsKey(phone)) {
            System.out.println("No Such User Exists");
            return;
        }
        User user = userMap.get(phone);
        user.topUpWallet(amount, paymentMode);
        System.out.println("Wallet Recharge Successful");
        System.out.println("Updated balance is : " + user.displayBalance());
    }

    public void sendMoney(String senderPhone, String receiverPhone, Double amount) {
        if(!userMap.containsKey(senderPhone)) {
            System.out.println("Sender does not exist, can't send Money");
            return;
        }
        if(!userMap.containsKey(receiverPhone)) {
            System.out.println("Receiver does not exist, can't send Money");
            return;
        }

        User sender = userMap.get(senderPhone);
        User receiver = userMap.get(receiverPhone);

        if(sender.displayBalance() < amount) {
            System.out.println("You do not have enough money to complete this transaction. Please recharge your Wallet");
            return;
        }

        sender.deductWallet(amount, Transaction.PaymentModes.WALLET);
        receiver.topUpWallet(amount, Transaction.PaymentModes.WALLET);
        System.out.println("Money has been successfully transferred");
    }
}
