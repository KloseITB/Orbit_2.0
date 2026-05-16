package it.unipv.posfw.orbit.payment;

public class CreditCardFactory {
	
	private static CreditCardFactory instance;
	
	private CreditCardFactory() {
		
	}
	
	public static CreditCardFactory getInstance() {
		if(instance == null) {
			instance = new CreditCardFactory();
		}
		
		return instance;
	}
	
	public CreditCard create(String code) {
		return new CreditCard(code);
	}
}
