package selvagens;

import java.util.Random;

public class Selvagem extends Thread{
    
    private final Caldeirao caldeirao;
    private final String nome;
    private final Random random = new Random();

    public Selvagem (String nome, Caldeirao caldeirao) {
        this.caldeirao = caldeirao;
        this.nome = nome;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {

                // Tenta o acesso a Seção crítica
                caldeirao.seServir(nome);

                System.out.println("[" + nome + "] Começou a comer a sua " + (i+1) + "ª porção...");

                // Simula o tempo do selvagem comendo
                Thread.sleep(random.nextInt(2000) + 3000);

                System.out.println("[" + nome + "] Terminou de comer a " + (i+1) + "ª porção e foi dar uma volta.");

                // Simula o tempo que ele gasta antes de sentir fome de novo
                Thread.sleep(random.nextInt(200) + 4000);
            } catch (InterruptedException e) {
                System.out.println("[" + nome + "] Foi interrompido.");
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println(">>> [" + nome + "] FICOU COMPLETAMENTE SATISFEITO E FOI DORMIR. <<<");
    }
}
