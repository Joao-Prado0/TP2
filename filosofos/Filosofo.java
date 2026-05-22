package filosofos;

import java.util.Random;

public class Filosofo extends Thread {
    private final int id;
    private final Mesa mesa;
    private final Random random = new Random();

    public Filosofo(int id, Mesa mesa) {
        this.id = id;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        try {
            // Loop para simular o comportamento contínuo
            while (!Thread.currentThread().isInterrupted()) {
                pensar();
                mesa.pegarGarfos(id);
                comer();
                mesa.largarGarfos(id);
            }
        } catch (InterruptedException e) {
            System.out.println("Filósofo " + id + " foi interrompido.");
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando...");
        Thread.sleep(random.nextInt(1500) + 500); // Tempo aleatório pensando 
    }

    private void comer() throws InterruptedException {
        Thread.sleep(random.nextInt(1500) + 500); // Tempo aleatório comendo
    }
}