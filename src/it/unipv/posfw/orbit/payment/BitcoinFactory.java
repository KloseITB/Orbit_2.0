package it.unipv.posfw.orbit.payment;

public class BitcoinFactory {
	
	private static BitcoinFactory instance;
	
	private BitcoinFactory() {
		
	}
	
	public static BitcoinFactory getInstance() {
		if(instance == null) {
			instance = new BitcoinFactory();
		}
		
		return instance;
	}
	
	public Bitcoin create(String walletAddress) {
		return new Bitcoin(walletAddress);
	}
}
