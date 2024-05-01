package ch.jakubma.cokedispenser.Coin;

import java.util.HashMap;

public class CoinReturner {
    protected static CoinCurrencyDispenser fiftyDispenser = new FiftyDispenser();
    protected static CoinCurrencyDispenser twentyDispenser = new TwentyDispenser();
    protected static CoinCurrencyDispenser tenDispenser = new TenDispenser();
    protected static CoinCurrencyDispenser fiveDispenser = new FiveDispenser();
    protected static CoinCurrencyDispenser twoDispenser = new TwoDispenser();
    protected static CoinCurrencyDispenser oneDispenser = new OneDispenser();

    protected static CoinCurrencyDispenser dispenserChain; // Where to start off


    static {
        fiftyDispenser.setNextDispenser(twentyDispenser);
        twentyDispenser.setNextDispenser(tenDispenser);
        tenDispenser.setNextDispenser(fiveDispenser);
        fiveDispenser.setNextDispenser(twoDispenser);
        twoDispenser.setNextDispenser(oneDispenser);
        dispenserChain = fiftyDispenser;
    }

    public static HashMap<Integer, Integer> returnCoins(CoinCurrency currency) {
        if (currency != null) {
            dispenserChain.dispense(currency);
            System.out.println("I am in CoinReturner and the coins to dsispsense is:");
            System.out.println(currency.coinsToDispense);
        }

        return currency.coinsToDispense;
    }


}
