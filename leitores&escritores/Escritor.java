import java.util.Random;

public class Escritor extends Thread {
    private final String nome;
    private final DatabaseLE db; 
    private final Random random = new Random();

    public Escritor(String nome, DatabaseLE db) {
        this.nome = nome;
        this.db = db;
    }

    @Override
    public void run() {
        // Simula o escritor tentando modificar o banco 5 vezes
        for (int i = 0; i < 5; i++) {
            try {
                // O escritor escolhe uma posição aleatória de M (0 a 8) e um novo valor (1 a 99)
                int posicaoAleatoria = random.nextInt(9);
                int novoValor = random.nextInt(99) + 1;

                // Pede acesso exclusivo. Se tiver leitor na sala, ele dorme aqui.
                db.iniciarEscrita(nome, posicaoAleatoria, novoValor);
                
                // Simula o tempo gasto escrevendo/alterando o arquivo (entre 1 e 2 segundos)
                Thread.sleep(random.nextInt(1000) + 1000);
                
                // Termina a modificação e libera o banco de dados
                db.encerrarEscrita(nome);
                
                // Espera um tempo antes de querer escrever de novo
                Thread.sleep(random.nextInt(2000) + 1000);
                
            } catch (InterruptedException e) {
                System.out.println("[" + nome + "] Foi interrompido.");
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println(">>> [" + nome + "] ENCERROU SUAS ESCRITAS DO DIA. <<<");
    }
}