package ch.jakubma.cokedispenser.DispenserMain;

import ch.jakubma.cokedispenser.Coin.CoinCurrency;
import ch.jakubma.cokedispenser.Coin.CoinReturner;
import ch.jakubma.cokedispenser.DispenserDb.model.Drink;
import ch.jakubma.cokedispenser.DispenserDb.repository.DrinkRepository;
import ch.jakubma.cokedispenser.DispenserDb.service.DrinkService;
import ch.jakubma.cokedispenser.DispenserLogger.CustomLogger;
import ch.jakubma.cokedispenser.Payment.PaymentStrategy;
import ch.jakubma.cokedispenser.Payment.PaymentStrategyFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.command.annotation.Command;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

/* TODO: This should've been using Spring's Scope instead */

@Component
public class DispenserMachine {
    CustomLogger logger;

    private static DispenserMachine dispenserInstance;

    @Getter // Encapsulation
    @Setter
    private int currentCashBalance; // TODO: This should be `CoinCurrency`

    @Getter
    @Setter
    private Drink selectedDrink = null;

    public static synchronized DispenserMachine getInstance() {
        if (dispenserInstance == null) {
            dispenserInstance = new DispenserMachine();
        }
        return dispenserInstance;
    }

    private DispenserMachine() {
        logger = CustomLogger.getInstance();
    }

    public HashMap<Integer, Integer> returnChange() {
        var cashBalanceAsCoinCurrency = new CoinCurrency(this.getCurrentCashBalance());
        var returnedCoins = CoinReturner.returnCoins(cashBalanceAsCoinCurrency);
        this.setCurrentCashBalance(0);
        return returnedCoins;
    }

    public boolean addCurrentCashBalance(int coin) {
        switch(coin) {
            case 1, 2, 5, 10, 20, 50:
                this.currentCashBalance += coin;
                logger.log("Adding %d to cash balance, cash balance is now %d".formatted(coin, currentCashBalance));
                return true;
            default:
                return false;
        }
    }

}
