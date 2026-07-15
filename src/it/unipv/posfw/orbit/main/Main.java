package it.unipv.posfw.orbit.main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import it.unipv.posfw.orbit.gui.PublishUI;
import it.unipv.posfw.orbit.gui.StoreUI;

public class Main {
	
    public static void main(String[] args) {
    	
    	// Software starting point
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        	System.err.println("ERROR: Failed to initialize the GUI");
        }

        SwingUtilities.invokeLater(PublishUI/*StoreUI*/::new);
    }
}
