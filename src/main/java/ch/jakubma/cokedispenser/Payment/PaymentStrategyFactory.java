package ch.jakubma.cokedispenser.Payment;

public class PaymentStrategyFactory {
    public static PaymentStrategy createPaymentStrategy(String paymentType) {
        return switch (paymentType.toLowerCase()) {
            case "card" -> new PayByCreditCard();
            default -> new PayByCash();
        };
    }
}