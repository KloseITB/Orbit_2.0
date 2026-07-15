package it.unipv.posfw.orbit.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import it.unipv.posfw.orbit.client.UserManager;
import it.unipv.posfw.orbit.game.Game;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class CheckoutUI extends JFrame {

    // Palette colors matching the Orbit UI style
    private static final Color BG_DARK       = new Color(22, 22, 30);
    private static final Color PANEL_BG      = new Color(42, 38, 60);
    private static final Color ACCENT_YELLOW = new Color(230, 175, 30);
    private static final Color ACCENT_HOVER  = new Color(255, 200, 50);
    private static final Color TEXT_COLOR    = new Color(220, 215, 235);

    public CheckoutUI(Game game) {
        setTitle("Orbit - Purchase Game");
        
        Image img = null;
		try {
			img = ImageIO.read(new File("icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        setIconImage(img);
        
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_DARK);

        // --- HEADER ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JPanel logopanel = buildLogoPanel();

        headerPanel.add(logopanel, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);

        // --- CENTER: Form Panel ---
        JPanel centerContainer = new JPanel(new GridBagLayout());
        centerContainer.setOpaque(false);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(PANEL_BG);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 58, 80), 1),
                new EmptyBorder(40, 60, 40, 60)
                
                
        ));

        // Titles
        JLabel titleLabel = new JLabel("COMPLETE YOUR PURCHASE");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("SELECT A PAYMENT METHOD");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(TEXT_COLOR);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(titleLabel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(subtitleLabel);
        formPanel.add(Box.createVerticalStrut(35));

        // Dropdown Menu
        formPanel.add(createCenteredLabel("Payment Method"));
        formPanel.add(Box.createVerticalStrut(5));
        
        String[] paymentMethods = {"Bitcoin", "CreditCard", "Paypal"};
        JComboBox<String> paymentCombo = new JComboBox<>(paymentMethods);
        paymentCombo.setForeground(BG_DARK);
        styleComboBox(paymentCombo);
        formPanel.add(paymentCombo);
        formPanel.add(Box.createVerticalStrut(20));

        // Dynamic Input Panel using CardLayout
        CardLayout cardLayout = new CardLayout();
        JPanel dynamicInputsPanel = new JPanel(cardLayout);
        dynamicInputsPanel.setOpaque(false);
        dynamicInputsPanel.setMaximumSize(new Dimension(300, 70));

        // 1. Bitcoin Card
        JPanel bitcoinPanel = createInputCard("Wallet address");
        // 2. CreditCard Card
        JPanel creditCardPanel = createInputCard("Code");
        // 3. Paypal Card
        JPanel paypalPanel = createInputCard("Email");

        dynamicInputsPanel.add(bitcoinPanel, "Bitcoin");
        dynamicInputsPanel.add(creditCardPanel, "CreditCard");
        dynamicInputsPanel.add(paypalPanel, "Paypal");

        formPanel.add(dynamicInputsPanel);
        formPanel.add(Box.createVerticalStrut(40));

        // Action Listener to switch cards when dropdown selection changes
        paymentCombo.addActionListener(e -> {
            String selectedMethod = (String) paymentCombo.getSelectedItem();
            cardLayout.show(dynamicInputsPanel, selectedMethod);
        });

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(300, 40));

        JButton btnCancel = createStyledButton("CANCEL", 120, 40);
        btnCancel.addActionListener(e -> {
            dispose();
        	new StoreUI();
            
            });
        
        JButton btnPurchase = createStyledButton("PURCHASE", 120, 40);
        btnPurchase.addActionListener(e -> {
            
            JOptionPane.showMessageDialog(this
            		, game.getTitle() + " was added to your library"
            		, "Purchase successful"
            		, JOptionPane.INFORMATION_MESSAGE);
            UserManager.getInstance().getLoggedUser().getLibrary().addGame(game);
            });

        buttonPanel.add(btnCancel);
        buttonPanel.add(btnPurchase);
        formPanel.add(buttonPanel);

        centerContainer.add(formPanel);
        add(centerContainer, BorderLayout.CENTER);
        
        setVisible(true);
    }

    // --- UI HELPER METHODS ---
    
    private JPanel buildLogoPanel() {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        wrapper.setBackground(BG_DARK);
        
        ImageIcon icon = new ImageIcon("logo.png");
        Image scaled = icon.getImage().getScaledInstance(200, 30, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaled));
        logo.setPreferredSize(new Dimension(200, 30));

        wrapper.add(logo);
        return wrapper;
    }
    
    private JPanel createInputCard(String labelText) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel label = createCenteredLabel(labelText);
        JTextField textField = createTextField();

        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(textField);

        return panel;
    }

    private JLabel createCenteredLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(TEXT_COLOR);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(300, 35));
        field.setPreferredSize(new Dimension(300, 35));
        field.setBackground(Color.WHITE);
        field.setForeground(BG_DARK);
        field.setCaretColor(BG_DARK);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        Border defaultBorder = BorderFactory.createLineBorder(new Color(70, 70, 90));
        Border focusBorder = BorderFactory.createLineBorder(BG_DARK);

        field.setBorder(BorderFactory.createCompoundBorder(defaultBorder, new EmptyBorder(5, 10, 5, 10)));

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(focusBorder, new EmptyBorder(5, 10, 5, 10)));
            }

            @Override
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(defaultBorder, new EmptyBorder(5, 10, 5, 10)));
            }
        });

        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        return field;
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setMaximumSize(new Dimension(300, 35));
        comboBox.setPreferredSize(new Dimension(300, 35));
        comboBox.setBackground(ACCENT_HOVER);
        comboBox.setForeground(BG_DARK);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboBox.setFocusable(false);
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 90)));
        
        // Customizing the dropdown list appearance
        UIManager.put("ComboBox.selectionBackground", TEXT_COLOR);
        UIManager.put("ComboBox.selectionForeground", Color.BLACK);
    }

    private JButton createStyledButton(String text, int w, int h) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(w, h));
        btn.setBackground(ACCENT_YELLOW);
        btn.setForeground(new Color(20, 20, 20));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(ACCENT_HOVER); }
            public void mouseExited (MouseEvent e) { btn.setBackground(ACCENT_YELLOW); }
        });
        return btn;
        }
}