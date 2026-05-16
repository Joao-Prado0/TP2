import java.util.concurrent.Semaphore;

public class DatabaseLE {

    // Recurso compartilhado
    private int[] M = { 3, 7, 1, 9, 2, 12, 5, 90, 34 };

    // Contador de Leitores concorrentes
    private int readerCount = 0;

    // Semáforo para exclusão mútua do contador 'readerCount'
    private final Semaphore mutex = new Semaphore(1);

    // Semáforo para controlar o acesso ao Database
    private final Semaphore db = new Semaphore(1);

    // Método para Leitores
    public void iniciarLeitura(String nomeLeitor) {
        try {
            mutex.acquire();
            readerCount++;

            if (readerCount == 1) {
                db.acquire();
                System.out.println("[" + nomeLeitor + "] Primeiro leitor chegou. Bloqueando escrita.");
            }

            mutex.release();

            System.out.println("[" + nomeLeitor + "] Lendo dados: " + java.util.Arrays.toString(M));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void encerrarLeitura(String nomeLeitor) {
        try {
            mutex.acquire();

            readerCount--;
            System.out.println("[" + nomeLeitor + "] Terminou de ler.");

            if (readerCount == 0) {
                db.release();
                System.out.println("[" + nomeLeitor + "] Último leitor saiu. Liberando base para escrita.");
            }

            mutex.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // --- MÉTODOS PARA OS ESCRITORES ---

    public void iniciarEscrita(String nomeEscritor, int posicao, int novoValor) {
        try {
            // Pede acesso exclusivo à base de dados
            // Se houver qualquer leitor ou outro escritor usando, ele trava aqui
            db.acquire();
            
            // Simula a escrita alterando o "vetor M"
            System.out.println("[" + nomeEscritor + "] Modificando M[" + posicao + "] de " + M[posicao] + " para " + novoValor);
            M[posicao] = novoValor;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void encerrarEscrita(String nomeEscritor) {
        System.out.println("[" + nomeEscritor + "] Terminou de escrever e liberou o recurso.");
        db.release();
    }

}
