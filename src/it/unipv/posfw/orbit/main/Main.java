package it.unipv.posfw.orbit.main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import it.unipv.posfw.orbit.gui.Store;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(Store::new);
    }
}
