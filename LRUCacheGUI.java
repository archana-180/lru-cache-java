import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LRUCacheGUI extends JFrame implements ActionListener {

    JLabel titleLabel, keyLabel, valueLabel, outputLabel;
    JTextField keyField, valueField;

    JButton putButton, getButton, showButton, clearButton;

    JTextArea outputArea;

    LRUCache cache = new LRUCache(3);

    public LRUCacheGUI() {

        // ===== FRAME =====
        setTitle("LRU Cache System");
        setSize(700, 520);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(18, 18, 18)); // dark theme

        // ===== TITLE =====
        titleLabel = new JLabel("LRU CACHE SYSTEM");
        titleLabel.setBounds(180, 20, 400, 40);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(new Color(255,255,255));

        // ===== INPUT LABELS =====
        keyLabel = new JLabel("Key:");
        keyLabel.setBounds(80, 90, 100, 30);
        keyLabel.setForeground(Color.WHITE);
        keyLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        valueLabel = new JLabel("Value:");
        valueLabel.setBounds(80, 140, 100, 30);
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        // ===== TEXT FIELDS =====
        keyField = new JTextField();
        keyField.setBounds(180, 90, 200, 30);

        valueField = new JTextField();
        valueField.setBounds(180, 140, 200, 30);

        // ===== BUTTON STYLE =====
       putButton = createButton("PUT", 80, 210, new Color(180, 230, 215), Color.BLACK);
       getButton = createButton("GET", 200, 210, new Color(255, 200, 210), Color.BLACK);
       showButton = createButton("SHOW CACHE", 320, 210, new Color(255, 218, 185), Color.BLACK);
       clearButton = createButton("CLEAR", 440, 210, new Color(255, 200, 210), Color.BLACK);

        // ===== OUTPUT LABEL =====
        outputLabel = new JLabel("CACHE OUTPUT:");
        outputLabel.setBounds(80, 270, 200, 30);
        outputLabel.setForeground(Color.WHITE);
        outputLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        // ===== OUTPUT AREA =====
        outputArea = new JTextArea();
        outputArea.setBounds(80, 310, 560, 140);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        outputArea.setBackground(new Color(30, 30, 30));
        outputArea.setForeground(Color.GREEN);
        outputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        outputArea.setEditable(false);

        // ===== ADD COMPONENTS =====
        add(titleLabel);
        add(keyLabel);
        add(valueLabel);
        add(keyField);
        add(valueField);

        add(putButton);
        add(getButton);
        add(showButton);
        add(clearButton);

        add(outputLabel);
        add(outputArea);

        setVisible(true);
    }

    // ===== BUTTON FACTORY =====
    private JButton createButton(String text, int x, int y, Color bg, Color fg) {
        JButton btn = new JButton(text);
    btn.setBounds(x, y, 130, 35);

    btn.setBackground(bg);
    btn.setForeground(fg);  // 👈 THIS SETS TEXT COLOR (BLACK HERE)

    btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btn.setFocusPainted(false);
    btn.setBorderPainted(false);

    btn.addActionListener(this);

    return btn;

    
   }
    
    // ===== ACTIONS =====
    public void actionPerformed(ActionEvent e) {

        try {

            if (e.getSource() == putButton) {

                int key = Integer.parseInt(keyField.getText());
                int value = Integer.parseInt(valueField.getText());

                cache.put(key, value);
                outputArea.setText("✔ Inserted Successfully\nKey = " + key + " Value = " + value);

            } 
            else if (e.getSource() == getButton) {

                int key = Integer.parseInt(keyField.getText());
                int result = cache.get(key);

                outputArea.setText("🔍 Value for Key " + key + " = " + result);

            } 
            else if (e.getSource() == showButton) {

                outputArea.setText("📦 CACHE STATE:\n" + cache.displayCache());

            } 
            else if (e.getSource() == clearButton) {

                keyField.setText("");
                valueField.setText("");
                outputArea.setText("");

            }

        } catch (Exception ex) {
            outputArea.setText("❌ Error: Enter valid numeric values only");
        }
    }

    public static void main(String[] args) {
        new LRUCacheGUI();
    }
}