package ch.jakubma.cokedispenser.Coin;

import ch.jakubma.cokedispenser.DispenserLogger.CustomLogger;

public class FiftyDispenser extends CoinCurrencyDispenser {
    CustomLogger logger;
    public FiftyDispenser() {
        logger = CustomLogger.getInstance();
    }

    @Override
    public void dispense(CoinCurrency currency) {
        if(currency != null) {
            int amount = currency.getAmount();
            int remainder = amount;

            if (amount >= 50) {
                int count = amount / 50;
                remainder = amount % 50;
                logger.log("Dispensing '%d' of 50 Kč coin.".formatted(count));
                currency.coinsToDispense.put(50, count);
                currency.setAmount(remainder);
            }

            if (remainder > 0 && this.nextDispenser != null) {
                this.nextDispenser.dispense(currency);
            }
        }
    }
}
