package it.unipv.posfw.orbit.payment;

/**
 * Concrete implementation of the IPaymentStrategy using PayPal.
 */

public class Paypal implements IPaymentStrategy {
	
	// ---------- Variables ----------
	
	@SuppressWarnings("unused")
	private String email = "default@mail.com";
	
	// ---------- Constructors ----------
	
	protected Paypal(String email) {
		this.email = email;
	}
	
	// ---------- Methods ----------
	
	@Override
	public void pay(float amount) {
		System.out.println("log: " + "paid " + amount + " € via Paypal\n");
	}

}
