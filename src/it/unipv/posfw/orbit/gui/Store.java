package it.unipv.posfw.orbit.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Store extends JFrame {

    // ── Palette colori ──────────────────────────────────────────────────────
    private static final Color BG_DARK       = new Color(22, 22, 30);
    private static final Color PANEL_BG      = new Color(42, 38, 60);
    private static final Color ROW_ODD       = new Color(55, 50, 78);
    private static final Color ROW_EVEN      = new Color(47, 43, 68);
    private static final Color ROW_HOVER     = new Color(72, 66, 100);
    private static final Color ACCENT_YELLOW = new Color(230, 175, 30);
    private static final Color ACCENT_HOVER  = new Color(255, 200, 50);
    private static final Color TEXT_LIGHT    = new Color(220, 215, 235);
    private static final Color TEXT_DIM      = new Color(150, 145, 165);
    private static final Color SCROLLBAR_BG  = new Color(35, 32, 50);

    // ── Dati di esempio del catalogo ─────────────────────────────────────────
    private static final String[][] GAMES = {
        {"Cyberpunk 2077",              "59,99 €"},
        {"The Witcher 3: Wild Hunt",     "29,99 €"},
        {"Red Dead Redemption 2",        "49,99 €"},
        {"Elden Ring",                   "59,99 €"},
        {"Dark Souls III",               "39,99 €"},
        {"Hollow Knight",                "14,99 €"},
        {"Stardew Valley",                "13,99 €"},
        {"Hades",                        "24,99 €"},
        {"Celeste",                      "19,99 €"},
        {"Portal 2",                      "9,99 €"},
        {"DOOM Eternal",                 "39,99 €"},
        {"Ori and the Blind Forest",     "19,99 €"},
        {"Terraria",                      "9,99 €"},
        {"Minecraft Java Edition",       "26,95 €"},
        {"GTA V",                        "29,99 €"},
        {"Sekiro: Shadows Die Twice",    "59,99 €"},
        {"Cuphead",                      "19,99 €"},
        {"Dead Cells",                   "24,99 €"},
    };

    // ──────────────────────────────────────────────────────────────────────────

    public Store() {
        setTitle("Orbit - Store");
        
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

        // Pannello radice con sfondo scuro e padding
        JPanel root = new JPanel(new BorderLayout(24, 0));
        root.setBackground(BG_DARK);
        root.setBorder(BorderFactory.createEmptyBorder(22, 22, 22, 22));

        // ── Sezione sinistra: logo + lista giochi ─────────────────────────
        JPanel leftSection = new JPanel(new BorderLayout(0, 16));
        leftSection.setBackground(BG_DARK);

        leftSection.add(buildLogoPanel(),     BorderLayout.NORTH);
        leftSection.add(buildGameList(),      BorderLayout.CENTER);

        // ── Sezione destra: pulsanti navigazione ──────────────────────────
        JPanel rightSection = buildNavPanel();

        root.add(leftSection,  BorderLayout.CENTER);
        root.add(rightSection, BorderLayout.EAST);

        setContentPane(root);
        setVisible(true);
    }

    // ── Logo ─────────────────────────────────────────────────────────────────

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

    // ── Lista giochi scorrevole ───────────────────────────────────────────────

    private JScrollPane buildGameList() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(PANEL_BG);
        listPanel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        for (int i = 0; i < GAMES.length; i++) {
            listPanel.add(createGameRow(GAMES[i][0], GAMES[i][1], i));
            if (i < GAMES.length - 1) {
                listPanel.add(Box.createVerticalStrut(2));
            }
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(65, 60, 90), 1));
        scrollPane.setBackground(PANEL_BG);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.getVerticalScrollBar().setBackground(SCROLLBAR_BG);
        scrollPane.getViewport().setBackground(PANEL_BG);

        // Stile scrollbar
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override protected void configureScrollBarColors() {
                thumbColor     = new Color(110, 100, 150);
                trackColor     = SCROLLBAR_BG;
            }
            @Override protected JButton createDecreaseButton(int o) { return invisibleButton(); }
            @Override protected JButton createIncreaseButton(int o) { return invisibleButton(); }
            private JButton invisibleButton() {
                JButton b = new JButton(); b.setPreferredSize(new Dimension(0, 0)); return b;
            }
        });

        return scrollPane;
    }

    private JPanel createGameRow(String title, String price, int index) {
        Color base = (index % 2 == 0) ? ROW_ODD : ROW_EVEN;

        JPanel row = new JPanel(new BorderLayout(12, 0));
        row.setBackground(base);
        row.setBorder(BorderFactory.createEmptyBorder(9, 14, 9, 10));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));

        // Titolo
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(TEXT_LIGHT);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Prezzo
        JLabel priceLabel = new JLabel(price);
        priceLabel.setForeground(TEXT_DIM);
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        priceLabel.setPreferredSize(new Dimension(68, 20));
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Pulsante BUY
        JButton buyBtn = createStyledButton("BUY", 65, 30);
        buyBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "Hai aggiunto \"" + title + "\" al carrello!",
                "Carrello", JOptionPane.INFORMATION_MESSAGE)
        );

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(priceLabel);
        rightPanel.add(buyBtn);

        row.add(titleLabel, BorderLayout.CENTER);
        row.add(rightPanel,  BorderLayout.EAST);

        // Effetto hover sulla riga
        MouseAdapter hoverEffect = new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { row.setBackground(ROW_HOVER); }
            public void mouseExited (MouseEvent e) { row.setBackground(base);      }
        };
        row.addMouseListener(hoverEffect);
        titleLabel.addMouseListener(hoverEffect);

        return row;
    }

    // ── Pannello navigazione (destra) ─────────────────────────────────────────

    private JPanel buildNavPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BG_DARK);
        panel.setBorder(BorderFactory.createEmptyBorder(85, 0, 0, 0));

        JButton libraryBtn = createNavButton("LIBRARY");
        JButton accountBtn = createNavButton("ACCOUNT");

        libraryBtn.addActionListener(e -> {
        	dispose();             // chiude la libreria
        	new Library();       // apre lo store
        });
        accountBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "Qui verranno mostrati i dettagli del tuo account.",
                "Il tuo Account", JOptionPane.INFORMATION_MESSAGE)
        );

        panel.add(libraryBtn);
        panel.add(Box.createVerticalStrut(14));
        panel.add(accountBtn);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    // ── Factory: pulsante di navigazione grande ────────────────────────────────

    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(148, 58));
        btn.setMaximumSize (new Dimension(148, 58));
        btn.setBackground(ACCENT_YELLOW);
        btn.setForeground(new Color(20, 20, 20));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(ACCENT_HOVER); }
            public void mouseExited (MouseEvent e) { btn.setBackground(ACCENT_YELLOW); }
        });
        return btn;
    }

    // ── Factory: pulsante piccolo (BUY) ───────────────────────────────────────

    private JButton createStyledButton(String text, int w, int h) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(w, h));
        btn.setBackground(ACCENT_YELLOW);
        btn.setForeground(new Color(20, 20, 20));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
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
