package it.unipv.posfw.orbit.payment;

public class Paypal implements IPaymentStrategy {
	
	// ---------- Variables ----------
	
	private String email = "default@mail.com";
	
	// ---------- Constructors ----------
	
	public Paypal(String email) {
		this.email = email;
	}
	
	// ---------- Methods ----------
	
	@Override
	public void pay(float amount) {
		System.out.println("log: " + "paid " + amount + "euros with Paypal\n");
	}

}
