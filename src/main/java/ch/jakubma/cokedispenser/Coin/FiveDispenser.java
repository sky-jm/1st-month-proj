package ch.jakubma.cokedispenser.Coin;

import ch.jakubma.cokedispenser.DispenserLogger.CustomLogger;

public class FiveDispenser extends CoinCurrencyDispenser {
    CustomLogger logger;
    public FiveDispenser() {
        logger = CustomLogger.getInstance();
    }

    @Override
    public void dispense(CoinCurrency currency) {
        if(currency != null) {
            int amount = currency.getAmount();
            int remainder = amount;

            if (amount >= 5) {
                int count = amount / 5;
                remainder = amount % 5;
                logger.log("Dispensing '%d' of 5 Kč coin.".formatted(count));
                currency.coinsToDispense.put(5, count);
                currency.setAmount(remainder);
            }

            if (remainder > 0 && this.nextDispenser != null) {
                this.nextDispenser.dispense(currency);
            }
        }
    }
}
