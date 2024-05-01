package ch.jakubma.cokedispenser.Payment;

import ch.jakubma.cokedispenser.Coin.CoinCurrency;
import ch.jakubma.cokedispenser.Coin.CoinReturner;
import ch.jakubma.cokedispenser.DispenserLogger.CustomLogger;
import ch.jakubma.cokedispenser.DispenserMain.DispenserMachine;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class PayByCash implements PaymentStrategy {
    private int paymentAmount = 0;

    CustomLogger logger;

    @Override
    public boolean pay(int paymentAmount, int currentBalance) {
        this.paymentAmount = paymentAmount;

        if (currentBalance >= paymentAmount) {
            return true;
        }
        else {
            System.err.println("DEBUG: You haven't put in enough money, bro.");
            return false;
        }
    }

}
