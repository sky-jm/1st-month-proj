package ch.jakubma.cokedispenser.Coin;

import ch.jakubma.cokedispenser.DispenserLogger.CustomLogger;

public class TenDispenser extends CoinCurrencyDispenser {
    CustomLogger logger;
    public TenDispenser() {
        logger = CustomLogger.getInstance();
    }

    @Override
    public void dispense(CoinCurrency currency) {
        if(currency != null) {
            int amount = currency.getAmount();
            int remainder = amount;

            if (amount >= 10) {
                int count = amount / 10;
                remainder = amount % 10;
                logger.log("Dispensing '%d' of 10 Kč coin.".formatted(remainder));
                currency.coinsToDispense.put(10, count);
                currency.setAmount(remainder);
            }

            if (remainder > 0 && this.nextDispenser != null) {
                this.nextDispenser.dispense(currency);
            }
        }
    }
}
