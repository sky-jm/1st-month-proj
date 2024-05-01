package ch.jakubma.cokedispenser.Payment;

import java.util.Scanner;

public class PayByCreditCard implements PaymentStrategy {
    @Override
    public boolean pay(int paymentAmount, int currentBalance) {
        return false;
    }

}
