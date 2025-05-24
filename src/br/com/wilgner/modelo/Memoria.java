package br.com.wilgner.modelo;

import static java.lang.Float.parseFloat;

public class Memoria {
    private String valor = "0";
    private String valorAnterior;
    private String valorFinal;
    private String operacaoPendente;
    private boolean substituir;

    public void processarComando(String texto) {
        if (texto.equals("AC")) {
            valor = "0";
            valorAnterior = "";
            valorFinal = "";
            operacaoPendente = "";
        } else if (texto.equals("+/-")) {
            if (valor.startsWith("-")) {
                valor = valor.substring(1);
            } else {
                valor = "-" + valor;
            }
        } else if (texto.equals(",")) {
            if(!valor.contains(",")) {
                valor += ",";
            }
        } else if (texto.matches("[0-9]")) {
            if (substituir || valor.equals("0")) {
                valor = texto;
                substituir = false;
            } else {
                valor += texto;
            }
        } else if (texto.matches("[+\\-*/]")) {

            valorAnterior = valor;
            operacaoPendente = texto;
            substituir = true;
        } else if (texto.equals("=")) {
            try {
                double num1 = Double.parseDouble(valorAnterior.replace(",", "."));
                double num2 = Double.parseDouble(valor.replace(",", "."));
                double resultado = 0;

                switch (operacaoPendente) {
                    case "+":
                        resultado = num1 + num2;
                        break;
                    case "-":
                        resultado = num1 - num2;
                        break;
                    case "*":
                        resultado = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            valor = "Erro";
                            return;
                        }
                        resultado = num1 / num2;
                        break;
                }

                // Converter resultado para string com vírgula (formato brasileiro)
                String resultadoFormatado = String.valueOf(resultado).replace(".", ",");

                valor = resultadoFormatado;
                operacaoPendente = "";
                substituir = true;

                } catch (NumberFormatException e) {
                    valor = "Erro";
            }
        }
    }

    public String getValor() {
        return valor;
    }
}

