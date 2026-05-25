package tresRobos;

import java.util.concurrent.Semaphore;

public class TresRobosRunner {

    // Semáforos estáticos para os robôs acessarem
    public static Semaphore semBart = new Semaphore(1); // Bart começa com 1
    public static Semaphore semLisa = new Semaphore(0);
    public static Semaphore semMaggie = new Semaphore(0);

    public static void executar() {
        System.out.println("====================================================");
        System.out.println("INICIANDO SIMULAÇÃO: PROBLEMA DOS TRÊS ROBÔS");
        System.out.println(" Para encerrar, aperte Ctrl+C ou clique em 'Stop'");
        System.out.println("====================================================\n");

        // Caso o usuário rode mais de uma vez pelo menu, garantimos os valores iniciais
        semBart.drainPermits(); semBart.release(1);
        semLisa.drainPermits();
        semMaggie.drainPermits();

        // Cria as threads
        Thread threadBart = new Thread(new Bart());
        Thread threadLisa = new Thread(new Lisa());
        Thread threadMaggie = new Thread(new Maggie());

        // Inicia as threads
        threadBart.start();
        threadLisa.start();
        threadMaggie.start();
    }

    // Método exigido no enunciado
    public static void move(String nome) {
        System.out.println("Robô " + nome + " se moveu.");
        try {
            Thread.sleep(700); // Pausa para conseguirmos ler no console
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}