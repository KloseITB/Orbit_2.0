package it.unipv.posfw.orbit.payment;

/**
 * Interface defining the Strategy pattern for processing payments.
 * Allows the application to switch payment methods dynamically at runtime.
 */

public interface IPaymentStrategy {
	
	/**
     * Executes the payment transaction.
     * * @param amount The total amount to be charged.
     */
	void pay(float amount);
}
