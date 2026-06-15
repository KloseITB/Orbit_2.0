package it.unipv.posfw.orbit.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoginUI extends JFrame{
	
	private static final Color PANEL_BG       = new Color(42, 38, 60);
	private static final Color ACCENT_YELLOW  = new Color(230, 175, 30);
	
    public LoginUI() {
        setTitle("Orbit - Login");
        
        Image img = null;
		try {
			img = ImageIO.read(new File("icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setIconImage(img);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        
        GridBagConstraints mainGbc = new GridBagConstraints();

        JPanel formPanel = new JPanel();
        formPanel.setBackground(PANEL_BG);
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 20, 0);

        formPanel.setBorder(BorderFactory.createEmptyBorder(60, 80, 60, 80));

        // 3. Titolo: "JOIN ORBIT TODAY!"
        JLabel titleLabel = new JLabel("JOIN ORBIT TODAY!", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 60, 0);
        formPanel.add(titleLabel, gbc);

        // 4. Nickname Label
        JLabel nicknameLabel = new JLabel("Nickname");
        nicknameLabel.setForeground(Color.WHITE);
        nicknameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 10, 0);
        formPanel.add(nicknameLabel, gbc);

        // 5. Nickname Input Field
        JTextField nicknameField = new JTextField(20);
        nicknameField.setBackground(Color.WHITE);
        nicknameField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 40, 0);
        formPanel.add(nicknameField, gbc);

        // 6. Password Label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 10, 0);
        formPanel.add(passwordLabel, gbc);

        // Password Input Field
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBackground(Color.WHITE);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 60, 0);
        formPanel.add(passwordField, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
        buttonPanel.setOpaque(false);

        // Login Button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setBackground(ACCENT_YELLOW);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setOpaque(true);
        loginButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login pressed. Nickname: " + nicknameField.getText());
            	dispose();             
            	new StoreUI(); 
            }
        });
        
        // Label "OR"
        JLabel orLabel = new JLabel("OR", JLabel.CENTER);
        orLabel.setForeground(Color.WHITE);
        orLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        // Signup Button
        JButton signupButton = new JButton("SIGNUP");
        signupButton.setBackground(ACCENT_YELLOW);
        signupButton.setForeground(Color.BLACK);
        signupButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        signupButton.setFocusPainted(false);
        signupButton.setBorderPainted(false);
        signupButton.setOpaque(true);
        signupButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Signup premuto.");
            	dispose();             
            	new StoreUI(); 
            }
        });
        
        // Add components to the frame
        buttonPanel.add(loginButton);
        buttonPanel.add(orLabel);
        buttonPanel.add(signupButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        formPanel.add(buttonPanel, gbc);

        add(formPanel, mainGbc);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
