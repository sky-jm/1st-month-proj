package ch.jakubma.cokedispenser.Coin;

import java.util.HashMap;

/*
# Chain of responsibility pattern
* Client sends request to first object in chain without knowing which part will process it.
* Each object in the chain has its own implementation to process the request partially or fully,
    * or send it to the next object in the chain.
* Objects are composed in the chain through references to the next object,
    * allowing the request to be forwarded accordingly.
* Creating the chain carefully is important to ensure that the
    * request is processed correctly and reaches the intended processor.
*
* * Advantges:
    * Loose coupling between objects
* Disadvantages:
*   it can result in a lot of implementation classes
*   and maintenance difficulties if most of the code is shared among them.
 */

public abstract class CoinCurrencyDispenser {
    protected CoinCurrencyDispenser nextDispenser;

    public CoinCurrencyDispenser() {

    }

    public void setNextDispenser(CoinCurrencyDispenser nextDispenserToUse) {
        this.nextDispenser = nextDispenserToUse;
    }

    public abstract void dispense(CoinCurrency currency);
}
