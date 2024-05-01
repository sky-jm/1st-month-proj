package ch.jakubma.cokedispenser.Coin;

import ch.jakubma.cokedispenser.DispenserLogger.CustomLogger;

public class TwoDispenser extends CoinCurrencyDispenser {
    CustomLogger logger;
    public TwoDispenser() {
        logger = CustomLogger.getInstance();
    }

    @Override
    public void dispense(CoinCurrency currency) {
        if(currency != null) {
            int amount = currency.getAmount();
            int remainder = amount;

            if (amount >= 2) {
                int count = amount / 2;
                remainder = amount % 2;
                logger.log("Dispensing '%d' of 2 Kč coin.".formatted(count));
                currency.coinsToDispense.put(2, count);
                currency.setAmount(remainder);
            }

            if (remainder > 0 && this.nextDispenser != null) {
                this.nextDispenser.dispense(currency);
            }
        }
    }
}
