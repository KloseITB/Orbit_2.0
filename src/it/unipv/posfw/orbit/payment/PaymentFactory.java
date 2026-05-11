package it.unipv.posfw.orbit.payment;

import java.util.HashMap;
import java.util.function.Function;

public class PaymentFactory {
	
	private static PaymentFactory instance;
	private HashMap<String, Function<String, IPaymentStrategy>> paymentOptionRegistry;
	
	private PaymentFactory() {}
	
	public static PaymentFactory getInstance() {
		if(instance == null) {
			instance = new PaymentFactory();
		}
		
		return instance;
	}
	
	public void registerPaymentOption(String key, Function<String, IPaymentStrategy> function) {
		paymentOptionRegistry.put(key.toLowerCase(), function);
		
	}
	
	public IPaymentStrategy create(String key, String attribute) {
	    return paymentOptionRegistry.get(key).apply(attribute);
	}
}
