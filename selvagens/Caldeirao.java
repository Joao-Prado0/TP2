package selvagens;

import java.util.concurrent.Semaphore;

public class Caldeirao {

    // Capacidade maxima caldeirao
    private final int maxPorcoes;

    // Quantidade de porções no momento
    private int porcoes;

    // Semáforo para garantir apenas um selvagem se servindo
    private Semaphore mutex = new Semaphore(1);

    // Semáforo para controlar o sono do cozinheiro.
    private final Semaphore semVazio = new Semaphore(0);

    // Semáforo para controlar os selvagens quando faltar comida.
    private final Semaphore semSuficiente = new Semaphore(0);

    public Caldeirao(int maxPorcoes) {

        if (maxPorcoes <= 0) {
            throw new IllegalArgumentException("Número para máximo de porções inválido");
        }

        this.maxPorcoes = maxPorcoes;
        this.porcoes = maxPorcoes;
    }

    // Método de controle dos selvagens
    public void seServir(String nomeSelvagem) {
        try {
            // Garante acesso exclusivo ao caldeirao
            mutex.acquire();

            // Se o caldeirão estiver vazio o cozinheiro é acordado e preenche o caldeirão
            while (porcoes == 0) {
                System.out.println("[" + nomeSelvagem + "] Viu que o caldeirão está VAZIO! Acordando o cozinheiro...");

                semVazio.release();

                mutex.release();

                semSuficiente.acquire();

                mutex.acquire();
            }

            // "Retira sua porção"
            porcoes--;
            System.out.println("[" + nomeSelvagem + "] Se serviu. Porções restantes no caldeirão: " + porcoes);

            // Libera a operação para o próximo selvagem
            mutex.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void encherCaldeirao() {
        try {

            // O cozinheiro dorme até que algum selvagem descubra o caldeirão vazio
            semVazio.acquire();

            System.out.println("[COZINHEIRO] Acordou! Colocando " + maxPorcoes + " porções de missionário no caldeirão...");

            // Simulando o tempo de cozimento
            Thread.sleep(3000);

            this.porcoes = maxPorcoes;
            System.out.println("[COZINHEIRO] Caldeirão cheio! Voltando a dormir... 💤");

            // Acorda o selvagem que estava aguardando pela comida
            semSuficiente.release();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
