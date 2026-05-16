package it.unipv.posfw.orbit.payment;

public class PaypalFactory {
	
	private static PaypalFactory instance;
	
	private PaypalFactory() {
		
	}
	
	public static PaypalFactory getInstance() {
		if(instance == null) {
			instance = new PaypalFactory();
		}
		
		return instance;
	}
	
	public Paypal create(String email) {
		return new Paypal(email);
	}
}
