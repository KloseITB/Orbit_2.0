package it.unipv.posfw.orbit.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import it.unipv.posfw.orbit.dao.impl.GameDAO;
import it.unipv.posfw.orbit.game.Game;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class PublishUI extends JFrame{
	// Color Palette
    private static final Color BG_DARK       = new Color(22, 22, 30);
    private static final Color PANEL_BG      = new Color(42, 38, 60);
    private static final Color ACCENT_YELLOW = new Color(230, 175, 30);
    private static final Color ACCENT_HOVER  = new Color(255, 200, 50);
    private static final Color TEXT_COLOR    = new Color(220, 215, 235);

    public PublishUI() {
        setTitle("Orbit - Publish Game");
        
        Image img = null;
		try {
			img = ImageIO.read(new File("icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        setIconImage(img);
        
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_DARK);

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        JPanel logopanel = buildLogoPanel();

        headerPanel.add(logopanel, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel centerContainer = new JPanel(new GridBagLayout());
        centerContainer.setOpaque(false);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(PANEL_BG);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 58, 80), 1),
                new EmptyBorder(40, 60, 40, 60)
        ));

        JLabel titleLabel = new JLabel("PUBLISH A NEW GAME");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("INSERT THE GAME DETAILS");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(TEXT_COLOR);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(titleLabel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(subtitleLabel);
        formPanel.add(Box.createVerticalStrut(35));

        // Fields
        formPanel.add(createCenteredLabel("Title"));
        formPanel.add(Box.createVerticalStrut(6));
        JTextField titleField = createTextField();
        formPanel.add(titleField);
        formPanel.add(Box.createVerticalStrut(16));

        formPanel.add(createCenteredLabel("Genre"));
        formPanel.add(Box.createVerticalStrut(6));
        JTextField genreField = createTextField();
        formPanel.add(genreField);
        formPanel.add(Box.createVerticalStrut(16));

        formPanel.add(createCenteredLabel("Price"));
        formPanel.add(Box.createVerticalStrut(6));
        JFormattedTextField priceField = createPriceField();
        formPanel.add(priceField);
        formPanel.add(Box.createVerticalStrut(40));

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(300, 40));

        JButton btnCancel = createStyledButton("CANCEL", 110, 40);
        btnCancel.addActionListener(e -> {
        dispose();
    	new AccountUI();
        
        });
        
        JButton btnPublish = createStyledButton("PUBLISH", 110, 40);
        btnPublish.addActionListener(e -> {
        	GameDAO gd = new GameDAO();
        	Game publishedGame = new Game(titleField.getText(), genreField.getText(), Float.valueOf(priceField.getText()));
        	gd.addGame(publishedGame);
        JOptionPane.showMessageDialog(this,
            "The game " + titleField.getText() + " has been published!",
             "Publish Successful", JOptionPane.INFORMATION_MESSAGE);
        
        });

        buttonPanel.add(btnCancel);
        buttonPanel.add(btnPublish);
        formPanel.add(buttonPanel);

        centerContainer.add(formPanel);
        add(centerContainer, BorderLayout.CENTER);
        
        setVisible(true);
    }

    // --- METODI DI SUPPORTO PER LA UI ---
    
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
        field.setBackground(BG_DARK);
        field.setForeground(TEXT_COLOR);
        field.setCaretColor(TEXT_COLOR);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        Border defaultBorder = BorderFactory.createLineBorder(new Color(70, 70, 90));
        Border focusBorder = BorderFactory.createLineBorder(TEXT_COLOR);
        
        field.setBorder(BorderFactory.createCompoundBorder(defaultBorder, new EmptyBorder(5, 10, 5, 10)));
        
        // Borders when focused
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
    
    public static JFormattedTextField createPriceField() {
        JFormattedTextField field = new JFormattedTextField();
        field.setMaximumSize(new Dimension(300, 35));
        field.setPreferredSize(new Dimension(300, 35));
        field.setBackground(BG_DARK);
        field.setForeground(TEXT_COLOR);
        field.setCaretColor(TEXT_COLOR);
        
        try {
            MaskFormatter priceMask = new MaskFormatter("##.##");
            
            priceMask.setPlaceholderCharacter('_'); 
            
            priceMask.install(field);
            
        } catch (Exception e) {
            System.err.println("Masking Error: " + e.getMessage());
        }

        field.setPreferredSize(new Dimension(300, 35));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setHorizontalAlignment(JTextField.CENTER);
        
        return field;
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
