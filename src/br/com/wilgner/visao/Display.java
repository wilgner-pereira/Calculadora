package br.com.wilgner.visao;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    private final JLabel label;
    public Display() {
        setBackground(new Color(46, 49, 50));
        label = new JLabel("teste");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("courier", Font.PLAIN, 30));
        setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, Color.BLACK));

        setLayout(new BorderLayout());
        add(label, BorderLayout.EAST);
    }

}
