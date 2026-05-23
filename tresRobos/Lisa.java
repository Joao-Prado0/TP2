package tresRobos;

public class Lisa implements Runnable {
    // Variável para controlar a direção do movimento
    private boolean indoParaMaggie = true;

    @Override
    public void run() {
        try {
            while (true) {
                TresRobosRunner.semLisa.acquire(); // Pede permissão
                TresRobosRunner.move("Lisa");      // Se move
                
                if (indoParaMaggie) {
                    indoParaMaggie = false; 
                    TresRobosRunner.semMaggie.release(); // Passa a vez pra Maggie
                } else {
                    indoParaMaggie = true;  
                    TresRobosRunner.semBart.release();   // Passa a vez pro Bart
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}