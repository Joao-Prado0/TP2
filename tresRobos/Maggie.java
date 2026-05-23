package tresRobos;

public class Maggie implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                TresRobosRunner.semMaggie.acquire(); // Pede permissão
                TresRobosRunner.move("Maggie");      // Se move
                TresRobosRunner.semLisa.release();   // Passa a vez de volta pra Lisa
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}