package tresRobos;

public class Bart implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                TresRobosRunner.semBart.acquire(); // Pede permissão
                TresRobosRunner.move("Bart");      // Se move
                TresRobosRunner.semLisa.release(); // Passa a vez pra Lisa
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}