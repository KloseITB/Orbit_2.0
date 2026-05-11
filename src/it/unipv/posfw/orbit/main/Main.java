package it.unipv.posfw.orbit.main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import it.unipv.posfw.orbit.gui.StoreUI;
import it.unipv.posfw.orbit.payment.Bitcoin;
import it.unipv.posfw.orbit.payment.CreditCard;
import it.unipv.posfw.orbit.payment.PaymentFactory;
import it.unipv.posfw.orbit.payment.Paypal;

public class Main {
	
    public static void main(String[] args) {
    	
    	// Software setup (usually done using an external .json or .xml file)
    	PaymentFactory.getInstance().registerPaymentOption("credit",  attr -> new CreditCard(attr));
    	PaymentFactory.getInstance().registerPaymentOption("bitcoin", attr -> new Bitcoin(attr));
    	PaymentFactory.getInstance().registerPaymentOption("paypal",  attr -> new Paypal(attr));
    	
    	
    	// Software starting point
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(StoreUI::new);
    }
}
