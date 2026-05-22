import java.util.Random;

public class Leitor extends Thread{
    
    private final String nome;
    private final DatabaseLE db;
    private final Random random = new Random();

    public Leitor(String nome, DatabaseLE db){
        this.nome = nome;
        this.db=db;
    }

    @Override
    public void run(){
        // Simulando o leitor tentando acessar a memória 5 vezes
        for (int i = 0; i < 5; i++) {
            try{
                // Pede permissão para ler
                db.iniciarLeitura(nome);
                
                // Simula o tempo que ele gasta lendo o livro/dado
                Thread.sleep(random.nextInt(1000) + 500);

                // Avisa que terminou de ler e sai
                db.encerrarLeitura(nome);

                // Espera um pouco antes de tentar ler novamente 
                Thread.sleep(random.nextInt(1500) + 500);
            } catch (InterruptedException e) {
                System.out.println("[" + nome + "] Foi interrompido.");
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println(">>> [" + nome + "] ENCERROU SUAS LEITURAS DO DIA. <<<");
    }

}
