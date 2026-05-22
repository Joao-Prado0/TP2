package selvagens;

import java.util.ArrayList;
import java.util.List;

public class SelvagensRunner {

    public static void executar() {
        System.out.println("====================================================");
        System.out.println("INICIANDO SIMULAÇÃO: JANTAR DOS SELVAGENS");
        System.out.println("====================================================\n");

        int capacidadeCaldeirao = 5;
        int quantidadeSelvagens = 4;

        Caldeirao caldeirao = new Caldeirao(capacidadeCaldeirao);

        // Incializa o cozinheiro que vai dormir por o caldeirão estar cheio
        Cozinheiro cozinheiro = new Cozinheiro(caldeirao);
        cozinheiro.start();

        // Criação da lista de selvagens
        List<Selvagem> selvagens = new ArrayList<>();
        for (int i = 1; i <= quantidadeSelvagens; i++) {
            selvagens.add(new Selvagem("Selvagem " + i, caldeirao));
        }

        // Inicializa todas as threads
        for (Selvagem s : selvagens) {
            s.start();
        }

        // Garante que o menu do terminal aguarde o fim das operações da simulação
        for (Selvagem s : selvagens) {
            try {
                s.join();
            } catch (InterruptedException e) {
                System.out.println("A execução principal foi interrompida.");
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n====================================================");
        System.out.println("TODOS OS SELVAGENS ESTÃO SATISFEITOS. SIMULAÇÃO CONCLUÍDA!");
        System.out.println("====================================================\n");
    }
}
