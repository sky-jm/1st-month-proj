package ch.jakubma.cokedispenser.Payment;

/* Strategy pattern
*
* */

public interface PaymentStrategy {
    public abstract boolean pay(int paymentAmount, int accountBalance);


    // TODO: say that this is wrong, because you are giving accountBalance as an argument, so
    // TODO: the payment methods are not interchangable and not decoupled from the dispensermachine
    // TODO: since the card payment does not have accountBalance

    // TODO: better example was CC vs PayPal strategies on refactoring.guru
}
