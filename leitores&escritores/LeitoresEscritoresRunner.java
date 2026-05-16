import java.util.ArrayList;
import java.util.List;

public class LeitoresEscritoresRunner {

    public static void executar() {
        System.out.println("====================================================");
        System.out.println("INICIANDO SIMULAÇÃO: LEITORES E ESCRITORES");
        System.out.println("====================================================\n");

        // Criar instância na mmemória a ser dividida
        DatabaseLE db = new DatabaseLE();

        // Lista para guardarmos todas as threads
        List<Thread> threads = new ArrayList<>();

        // Instancia os leitores
        for (int i = 1; i <= 5; i++) {
            threads.add(new Leitor("Leitor " + i, db));
        }

        // Instancia os escritores
        for (int i = 1; i <= 2; i++) {
            threads.add(new Escritor("Escritor " + i, db));
        }

        // Dispara todas as threads
        for (Thread t : threads) {
            t.start();
        }

        // Bloqueia o Menu até que a última thread termine sua execução
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("A execução principal foi interrompida.");
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n====================================================");
        System.out.println("SIMULAÇÃO CONCLUÍDA COM SUCESSO!");
        System.out.println("====================================================\n");

    }
}
