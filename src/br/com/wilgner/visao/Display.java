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

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));

        add(label);
    }

}
