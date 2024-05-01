package ch.jakubma.cokedispenser.Coin;

import java.util.HashMap;

public class CoinCurrency {
    protected int amount;

    // TODO: This shouldn't be public, but I couldn't be bothered to getters/setters at this point
    public HashMap<Integer, Integer> coinsToDispense;

    public CoinCurrency(int amount) {
        super();
        this.amount = amount;
        this.coinsToDispense = new HashMap<Integer, Integer>();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
