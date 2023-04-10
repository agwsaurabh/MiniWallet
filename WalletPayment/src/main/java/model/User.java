package main.java.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    private String username;
    private String phone;
    private List<Transaction> transactions;
    private Wallet wallet;
    private String password;

    public void topUpWallet(Double transactionAmount, Transaction.PaymentModes paymentMode) {
        Double amount = wallet.getAmount() + transactionAmount;
        wallet.setAmount(amount);

        transactions.add(getTransaction(transactionAmount, paymentMode));
    }

    public void deductWallet(Double transactionAmount, Transaction.PaymentModes paymentMode) {
        Double amount = wallet.getAmount() - transactionAmount;
        wallet.setAmount(amount);

        transactions.add(getTransaction(transactionAmount, paymentMode));
    }

    public Double displayBalance() {
        return wallet.getAmount();
    }

    public List<Transaction> getFilteredTransactions(Predicate<Transaction> filter) {
        return transactions.stream().filter(filter).collect(Collectors.toList());
    }

    private Transaction getTransaction(Double transactionAmount, Transaction.PaymentModes paymentMode) {
        Transaction transaction = new Transaction();
        transaction.setSender(username);
        transaction.setReceiver(username);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setTransactionAmount(transactionAmount);
        transaction.setPaymentMode(paymentMode);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setStatus(Transaction.TransactionStatus.SUCCESS);

        return transaction;
    }
}