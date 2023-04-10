package main.java.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class Transaction {

    public enum PaymentModes {
        UPI,
        DEBIT_CARD,
        NET_BANKING,
        WALLET,
        CREDIT_CARD;
    }

    public enum TransactionStatus {
        SUCCESS,
        FAILED,
        PENDING;
    }

    private String sender;
    private String receiver;
    private LocalDateTime transactionDate;
    private Double transactionAmount;
    private TransactionStatus status;
    private String transactionId;
    private PaymentModes paymentMode;
}
