package br.com.wilgner.controle;
import br.com.wilgner.modelo.Memoria;
public class MemoriaControle {
    private final Memoria memoria = Memoria.getInstancia();

    public void executarComando(String comando) {
        memoria.processarComando(comando);
    }
}
