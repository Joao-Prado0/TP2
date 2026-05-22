package filosofos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilosofosRunner {
    public static void executar() {
        System.out.println("\n=== INICIANDO O JANTAR DOS FILÓSOFOS ===");
        System.out.println("Pressione ENTER para parar a simulação e voltar ao menu.\n");

        Mesa mesa = new Mesa();
        List<Filosofo> filosofos = new ArrayList<>();

        // Cria e inicia as 5 threads de filósofos 
        for (int i = 0; i < 5; i++) {
            Filosofo f = new Filosofo(i, mesa);
            filosofos.add(f);
            f.start();
        }

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        System.out.println("Encerrando as threads dos filósofos...");
        for (Filosofo f : filosofos) {
            f.interrupt();
        }
        
        System.out.println("Jantar finalizado com sucesso.\n");
    }
}