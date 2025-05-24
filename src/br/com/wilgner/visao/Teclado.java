package br.com.wilgner.visao;

import br.com.wilgner.modelo.Memoria;

import javax.swing.*;
import java.awt.*;

public class Teclado extends JPanel {
    private final Color COR_CINZA_ESCURO = new Color(68,68,68);
    private final Color COR_CINZA_CLARO = new Color(99,99,99);
    private final Color COR_LARANJA = new Color(242,163,60);

    public Teclado() {
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setBackground(Color.BLACK);

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        adicionarBotao("AC", COR_CINZA_ESCURO, (GridBagConstraints) c.clone(), 0, 0, 2);
        adicionarBotao("+/-", COR_CINZA_ESCURO, (GridBagConstraints) c.clone(), 2, 0);
        adicionarBotao("/", COR_LARANJA, (GridBagConstraints) c.clone(), 3, 0);

        adicionarBotao("7", COR_CINZA_CLARO, (GridBagConstraints) c.clone(), 0, 1);
        adicionarBotao("8", COR_CINZA_CLARO, (GridBagConstraints) c.clone(), 1, 1);
        adicionarBotao("9", COR_CINZA_CLARO, (GridBagConstraints) c.clone(), 2, 1);
        adicionarBotao("*", COR_LARANJA, (GridBagConstraints) c.clone(), 3, 1);

        adicionarBotao("4", COR_CINZA_CLARO, (GridBagConstraints) c.clone(), 0, 2);
        adicionarBotao("5", COR_CINZA_CLARO, (GridBagConstraints) c.clone(), 1, 2);
        adicionarBotao("6", COR_CINZA_CLARO, (GridBagConstraints) c.clone(), 2, 2);
        adicionarBotao("-", COR_LARANJA, (GridBagConstraints) c.clone(), 3, 2);

        adicionarBotao("1", COR_CINZA_CLARO, (GridBagConstraints) c.clone(), 0, 3);
        adicionarBotao("2", COR_CINZA_CLARO, (GridBagConstraints) c.clone(), 1, 3);
        adicionarBotao("3", COR_CINZA_CLARO, (GridBagConstraints) c.clone(), 2, 3);
        adicionarBotao("+", COR_LARANJA, (GridBagConstraints) c.clone(), 3, 3);

        adicionarBotao("0", COR_CINZA_CLARO, (GridBagConstraints) c.clone(), 0, 4);
        adicionarBotao(",", COR_CINZA_CLARO, (GridBagConstraints) c.clone(), 1, 4);
        adicionarBotao("=", COR_LARANJA, (GridBagConstraints) c.clone(), 2, 4, 2);
    }


    private void adicionarBotao(String texto, Color cor, GridBagConstraints c, int x, int y, int span) {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = span;
        Botao botao = new Botao(texto, cor);
        botao.addActionListener(e -> Memoria.getInstancia().processarComando(texto));

        add(botao, c);
    }
    private void adicionarBotao(String texto, Color cor, GridBagConstraints c, int x, int y) {
        c.gridx = x;
        c.gridy = y;

        Botao botao = new Botao(texto, cor);
        botao.addActionListener(e -> Memoria.getInstancia().processarComando(texto));
        add(botao, c);
    }
}


