package it.unipv.posfw.orbit.payment;

/**
 * Concrete implementation of the IPaymentStrategy using Bitcoin.
 */

public class Bitcoin implements IPaymentStrategy {
	
	// ---------- Variables ----------
	
	@SuppressWarnings("unused")
	private String walletAddress;
	
	// ---------- Constructors ----------
	
	protected Bitcoin(String walletAddress) {
		this.walletAddress = walletAddress;
	}
	
	// ---------- Methods ----------
	
	@Override
	public void pay(float amount) {
		System.out.println("log: " + "paid " + amount + " € using Bitcoins\n");
	}

}
