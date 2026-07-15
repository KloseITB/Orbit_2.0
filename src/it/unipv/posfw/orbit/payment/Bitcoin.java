package it.unipv.posfw.orbit.payment;

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
