package fumantes;

import java.util.ArrayList;
import java.util.List;

public class FumantesRunner {

    public static void executar() {
        System.out.println("INICIANDO SIMULAÇÃO: PROBLEMA DOS FUMANTES");

        // Instancia a memória/mesa compartilhada
        MesaFumantes mesa = new MesaFumantes();

        // Lista para gerenciar as threads
        List<Thread> threadsFumantes = new ArrayList<>();

        Agente agente = new Agente(mesa);
        
        threadsFumantes.add(new Fumante("Fumante 1", "Tabaco", mesa));
        threadsFumantes.add(new Fumante("Fumante 2", "Papel", mesa));
        threadsFumantes.add(new Fumante("Fumante 3", "Fósforo", mesa));

        // Dispara as threads
        for (Thread t : threadsFumantes) {
            t.start();
        }
        agente.start();

        // Aguarda o agente terminar as rodadas dele
        try {
            agente.join();
        } catch (InterruptedException e) {
            System.out.println("A execução do Agente foi interrompida.");
            Thread.currentThread().interrupt();
        }

        // Interrompe e aguarda as threads dos fumantes que ficaram no laço infinito
        for (Thread t : threadsFumantes) {
            t.interrupt();
        }
        for (Thread t : threadsFumantes) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("A execução do Fumante foi interrompida.");
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("SIMULAÇÃO CONCLUÍDA COM SUCESSO!");
    }
}