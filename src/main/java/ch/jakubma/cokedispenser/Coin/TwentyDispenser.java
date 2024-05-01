package ch.jakubma.cokedispenser.Coin;

import ch.jakubma.cokedispenser.DispenserLogger.CustomLogger;

public class TwentyDispenser extends CoinCurrencyDispenser {
    CustomLogger logger;
    public TwentyDispenser() {
        logger = CustomLogger.getInstance();
    }

    @Override
    public void dispense(CoinCurrency currency) {
        if(currency != null) {
            int amount = currency.getAmount();
            int remainder = amount;

            if (amount >= 20) {
                int count = amount / 20;
                remainder = amount % 20;
                logger.log("Dispensing '%d' of 20 Kč coin.".formatted(remainder));
                currency.coinsToDispense.put(20, count);
                currency.setAmount(remainder);
            }

            if (remainder > 0 && this.nextDispenser != null) {
                this.nextDispenser.dispense(currency);
            }
        }
    }
}
