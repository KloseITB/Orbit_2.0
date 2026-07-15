package it.unipv.posfw.orbit.gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import it.unipv.posfw.orbit.client.UserManager;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class AccountUI extends JFrame {

    //---------- Colors ----------
    private static final Color BG_DARK       = new Color(22, 22, 30);
    private static final Color PANEL_BG      = new Color(42, 38, 60);
    private static final Color FIELD_BG      = new Color(32, 29, 46);
    private static final Color ACCENT_YELLOW = new Color(230, 175, 30);
    private static final Color ACCENT_HOVER  = new Color(255, 200, 50);
    private static final Color TEXT_LIGHT    = new Color(220, 215, 235);
    private static final Color TEXT_DIM      = new Color(150, 145, 165);
    private static final Color TEXT_ACCENT   = new Color(180, 160, 255);
    private static final Color BORDER_COLOR  = new Color(75, 68, 105);
    private static final Color DANGER_RED    = new Color(210, 70, 70);
    private static final Color DANGER_HOVER  = new Color(240, 90, 90);
    
    //   STATO DI LOGIN — DA COLLEGARE AL SISTEMA DI AUTENTICAZIONE
    //   impostare a `true` se l'utente ha già effettuato il login,
    //   `false` per mostrare la schermata di accesso.

    private boolean isLoggedIn = UserManager.getInstance().getIsLoggedIn();

    //   DATI UTENTE — PLACEHOLDER
    //   sostituire con il caricamento reale dal sistema di autenticazione
    //   Ruoli disponibili: "user" | "publisher"

    private String  accountNickname;
    private String  accountRole;
    private int     ownedGamesCount  = 0;

    private void Setup() {
    	if(UserManager.getInstance().getLoggedUser() != null) {
    	    accountNickname  = UserManager.getInstance().getLoggedUser().getNickname();
    	    accountRole      = UserManager.getInstance().getLoggedUser().getRole().toString();
    	    ownedGamesCount  = UserManager.getInstance().getLoggedUser().getLibrary().getGames().size();
    	}
    }

    public AccountUI() {
        setTitle("Orbit — Account");
        
        Image img = null;
		try {
			img = ImageIO.read(new File("icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        setIconImage(img);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(920, 620);
        setMinimumSize(new Dimension(750, 480));
        setLocationRelativeTo(null);
        setBackground(BG_DARK);

        render();
        setVisible(true);
    }

    // Reloads the page every time the login state changes
    private void render() {
        getContentPane().removeAll();

        JPanel root = new JPanel(new BorderLayout(24, 0));
        root.setBackground(BG_DARK);
        root.setBorder(BorderFactory.createEmptyBorder(22, 22, 22, 22));

        JPanel leftSection = new JPanel(new BorderLayout(0, 16));
        leftSection.setBackground(BG_DARK);
        leftSection.add(buildLogoPanel(), BorderLayout.NORTH);
        leftSection.add(isLoggedIn ? buildAccountPanel() : buildLoginPanel(),
                        BorderLayout.CENTER);

        root.add(leftSection,     BorderLayout.CENTER);
        root.add(buildNavPanel(), BorderLayout.EAST);

        setContentPane(root);
        revalidate();
        repaint();
    }


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

    // Login interface
    private JPanel buildLoginPanel() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(BG_DARK);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(PANEL_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(36, 44, 36, 44)
        ));

        JLabel title    = centeredLabel("Login to your account", 22, Font.BOLD, TEXT_LIGHT);
        JLabel subtitle = centeredLabel("Insert your credentials to continue", 14, Font.PLAIN, TEXT_DIM);

        JLabel    nickLabel = centeredLabel("Nickname", 16, Font.BOLD, TEXT_LIGHT);
        JTextField nickField = createTextField(280, 38);

        JLabel        passLabel = centeredLabel("Password", 16, Font.BOLD, TEXT_LIGHT);
        JPasswordField passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(280, 38));
        passField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        styleTextField(passField);

        JLabel errorLabel = centeredLabel("Nickname and/or password incorrect.", 12, Font.PLAIN, DANGER_RED);
        errorLabel.setVisible(false);

        JButton loginBtn = createAccentButton("LOGIN", 280, 40);
        loginBtn.addActionListener(e -> {
            String nick = nickField.getText().trim();
            String pass = new String(passField.getPassword()).trim();

            //   sostituire la condizione qui sotto con la vera verifica delle credenziali 
            boolean credentialsValid = !nick.isEmpty() && !pass.isEmpty();

            if (credentialsValid) {
            	UserManager.getInstance().setLoggedIn(true);
                isLoggedIn      = true;
                accountNickname = nick;
                errorLabel.setVisible(false);
                render();
            } else {
                errorLabel.setVisible(true);
                passField.setText("");
            }
        });

        ActionListener submitOnEnter = e -> loginBtn.doClick();
        nickField.addActionListener(submitOnEnter);
        passField.addActionListener(submitOnEnter);

        card.add(title);
        card.add(Box.createVerticalStrut(4));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(28));
        card.add(nickLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(nickField);
        card.add(Box.createVerticalStrut(16));
        card.add(passLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(passField);
        card.add(Box.createVerticalStrut(10));
        card.add(errorLabel);
        card.add(Box.createVerticalStrut(24));
        card.add(loginBtn);

        outer.add(card);
        return outer;
    }

    // Logged in interface
    private JPanel buildAccountPanel() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(BG_DARK);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(PANEL_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(36, 44, 36, 44)
        ));

        JLabel titleLabel = centeredLabel("Your account", 20, Font.BOLD, TEXT_LIGHT);

        JSeparator sep = new JSeparator();
        sep.setForeground(BORDER_COLOR);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        
        // sistema di riconoscimento ruolo (Work in progress)
        
        String roleDisplay = accountRole.equals("publisher") ? "Publisher" : "User";
        Color  roleBg      = accountRole.equals("publisher")
                             ? new Color(100, 60, 180)
                             : new Color(50, 100, 160);
        JLabel roleBadge = new JLabel(roleDisplay.toUpperCase(), SwingConstants.CENTER);
        roleBadge.setFont(new Font("Segoe UI", Font.BOLD, 11));
        roleBadge.setForeground(Color.WHITE);
        roleBadge.setBackground(roleBg);
        roleBadge.setOpaque(true);
        roleBadge.setBorder(BorderFactory.createEmptyBorder(3, 12, 3, 12));
        roleBadge.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel badgeWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        badgeWrapper.setOpaque(false);
        badgeWrapper.add(roleBadge);

        // Info Panel
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setOpaque(false);
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addInfoRow(infoPanel, "Nickname",         accountNickname,            0);
        addInfoRow(infoPanel, "Role",             roleDisplay,                1);
        addInfoRow(infoPanel, "Games Owned", String.valueOf(ownedGamesCount), 2);

        // Logout
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(DANGER_RED);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        logoutBtn.setPreferredSize(new Dimension(220, 38));
        logoutBtn.setMaximumSize(new Dimension(220, 38));
        logoutBtn.setBorderPainted(false);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { logoutBtn.setBackground(DANGER_HOVER); }
            public void mouseExited (MouseEvent e) { logoutBtn.setBackground(DANGER_RED);   }
        });
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
            	UserManager.getInstance().setLoggedIn(false);
                isLoggedIn = false;
                render();
            }
        });

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(badgeWrapper);
        card.add(Box.createVerticalStrut(18));
        card.add(sep);
        card.add(Box.createVerticalStrut(22));
        card.add(infoPanel);
        card.add(Box.createVerticalStrut(32));
        card.add(logoutBtn);

        outer.add(card);
        return outer;
    }

    private void addInfoRow(JPanel panel, String label, String value, int row) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy  = row;
        gc.insets = new Insets(8, 0, 8, 0);
        gc.anchor = GridBagConstraints.WEST;

        gc.gridx  = 0;
        gc.ipadx  = 20;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(TEXT_DIM);
        panel.add(lbl, gc);

        gc.gridx  = 1;
        gc.ipadx  = 0;
        JLabel val = new JLabel(value);
        val.setFont(new Font("Segoe UI", Font.BOLD, 14));
        val.setForeground(TEXT_ACCENT);
        panel.add(val, gc);
    }

    // Navigation buttons
    private JPanel buildNavPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BG_DARK);
        panel.setBorder(BorderFactory.createEmptyBorder(85, 0, 0, 0));

        JButton storeBtn   = createNavButton("STORE");
        JButton libraryBtn = createNavButton("LIBRARY");
        JButton publishBtn = createNavButton("PUBLISH");

        storeBtn.addActionListener(e -> { dispose(); new StoreUI();   });
        libraryBtn.addActionListener(e -> { dispose(); new LibraryUI(); });
        publishBtn.addActionListener(e -> { dispose(); new PublishUI(); });
        
        if(!UserManager.getInstance().getIsLoggedIn()) {
        	libraryBtn.setEnabled(false);
        	libraryBtn.setText("");
        	libraryBtn.setOpaque(false);
        }
        
        if(!UserManager.getInstance().getIsLoggedIn()) {
        	publishBtn.setEnabled(false);
        	publishBtn.setText("");
        	publishBtn.setOpaque(false);
        }
        
        panel.add(storeBtn);
        panel.add(Box.createVerticalStrut(14));
        panel.add(libraryBtn);
        panel.add(Box.createVerticalStrut(14));
        panel.add(publishBtn);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    // ---------- Helpers ----------

    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(148, 40));
        btn.setMaximumSize(new Dimension(148, 58));
        btn.setBackground(ACCENT_YELLOW);
        btn.setForeground(new Color(20, 20, 20));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(ACCENT_HOVER);  }
            public void mouseExited (MouseEvent e) { btn.setBackground(ACCENT_YELLOW); }
        });
        return btn;
    }

    private JButton createAccentButton(String text, int w, int h) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(w, h));
        btn.setMaximumSize(new Dimension(w, h));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setBackground(ACCENT_YELLOW);
        btn.setForeground(new Color(20, 20, 20));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(ACCENT_HOVER);  }
            public void mouseExited (MouseEvent e) { btn.setBackground(ACCENT_YELLOW); }
        });
        return btn;
    }

    private JTextField createTextField(int w, int h) {
        JTextField f = new JTextField();
        f.setPreferredSize(new Dimension(w, h));
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE, h));
        styleTextField(f);
        return f;
    }

    private void styleTextField(JTextField f) {
        f.setBackground(FIELD_BG);
        f.setForeground(TEXT_LIGHT);
        f.setCaretColor(TEXT_LIGHT);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
        f.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                f.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(ACCENT_YELLOW, 1),
                    BorderFactory.createEmptyBorder(4, 10, 4, 10)
                ));
            }
            public void focusLost(FocusEvent e) {
                f.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDER_COLOR, 1),
                    BorderFactory.createEmptyBorder(4, 10, 4, 10)
                ));
            }
        });
    }

    private JLabel centeredLabel(String text, int size, int style, Color color) {
        JLabel l = new JLabel(text, SwingConstants.CENTER);
        l.setFont(new Font("Segoe UI", style, size));
        l.setForeground(color);
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        return l;
    }
}
