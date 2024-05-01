package ch.jakubma.cokedispenser.Coin;

import ch.jakubma.cokedispenser.DispenserLogger.CustomLogger;

public class OneDispenser extends CoinCurrencyDispenser {
    CustomLogger logger;
    public OneDispenser() {
        logger = CustomLogger.getInstance();
    }

    @Override
    public void dispense(CoinCurrency currency) {
        if(currency != null) {
            int amount = currency.getAmount();
            int remainder = amount;

            if (amount >= 1) {
                int count = amount / 1;
                remainder = amount % 1;
                logger.log("Dispensing '%d' of 1 Kč coin.".formatted(count));
                currency.coinsToDispense.put(1, count);
                currency.setAmount(remainder);
            }

            if (remainder > 0 && this.nextDispenser != null) {
                this.nextDispenser.dispense(currency);
            }
        }
    }
}
