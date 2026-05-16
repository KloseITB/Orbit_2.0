package it.unipv.posfw.orbit.payment;

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
		System.out.println("log: " + "paid " + amount + "euros with a Credit Card\n");
	}

}
 