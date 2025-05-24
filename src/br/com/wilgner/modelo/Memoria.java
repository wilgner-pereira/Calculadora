package br.com.wilgner.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
    private String valor = "0";
    private String valorAnterior = "";
    private String operacaoPendente = "";
    private boolean substituir;

    private final List<MemoriaObservador> observadoresList = new ArrayList<>();

    public void processarComando(String texto) {
        if (valor.equals("Erro")) {
            // Se estiver em "Erro" e o usuário digitar um número ou vírgula, resetar valor para começar novo número
            if (texto.matches("[0-9]") || texto.equals(",")) {
                valor = "0"; // ou "" se preferir iniciar vazio
                substituir = false; // para permitir adicionar os dígitos
            } else if (!texto.equals("AC")) {
                // Ignora qualquer comando que não seja número, vírgula ou AC enquanto estiver no erro
                return;
            }
        }
        if (texto.equals("AC")) {
            valor = "0";
            notificarObservadores();
            valorAnterior = "";
            operacaoPendente = "";
        } else if (texto.equals("+/-")) {
            if (valor.startsWith("-")) {
                valor = valor.substring(1);
            } else {
                valor = "-" + valor;
            }
            notificarObservadores();
        } else if (texto.equals(",")) {
            if (!valor.contains(",")) {
                valor += ",";
                notificarObservadores();
            }
        } else if (texto.matches("[0-9]")) {
            if (substituir || valor.equals("0")) {
                valor = texto;
                substituir = false;
            } else {
                valor += texto;
            }
            notificarObservadores();
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
                            notificarObservadores();
                            return;
                        }
                        resultado = num1 / num2;
                        break;
                }

                if (resultado == (int) resultado) {
                    // É um número inteiro, exibe sem casas decimais
                    valor = String.valueOf((int) resultado);
                } else {
                    // Tem casas decimais, exibe com vírgula
                    valor = String.valueOf(resultado).replace(".", ",");
                }
                substituir = true;
            } catch (NumberFormatException e) {
                valor = "Erro";
            }
            notificarObservadores();
        }
    }


    private static Memoria instancia;

    private Memoria() {
        // código de inicialização
    }

    public void adicionarObservadores(MemoriaObservador observadorRecebido) {
        observadoresList.add(observadorRecebido);
    }

    private void notificarObservadores(){
        for(MemoriaObservador x : observadoresList){
            x.valorAlterado(getValor());
        }
    }

    public String getValor() {
        return valor;
    }

    public static Memoria getInstancia() {
        if (instancia == null) {
            instancia = new Memoria();
        }
        return instancia;
    }
}

