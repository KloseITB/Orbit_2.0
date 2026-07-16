package it.unipv.posfw.orbit.payment;

/**
 * Concrete implementation of the IPaymentStrategy using Credit Card.
 */

public class CreditCard implements IPaymentStrategy{
	
	// ---------- Variables ----------
	
	@SuppressWarnings("unused")
	private String code = "0000-0000-0000";
	
	// ---------- Constructors ----------
	
	protected CreditCard(String code) {
		this.code = code;
	}
	
	// ---------- Methods ----------
	
	@Override
	public void pay(float amount) {
		System.out.println("log: " + "paid " + amount + " € using a Credit Card\n");
	}

}
 