package selvagens;

public class Cozinheiro extends Thread {
    
    private final Caldeirao caldeirao;
    
    public Cozinheiro(Caldeirao caldeirao) {
        this.caldeirao = caldeirao;
        this.setDaemon(true); 
    }

    @Override
    public void run() {
        try {
            // Fica bloqueado esperando o sinal de caldeirão vazio para trabalhar
            while (true) {
                caldeirao.encherCaldeirao();
            }
        } catch (Exception e) {
            System.out.println("[COZINHEIRO] Foi interrompido enquanto descansava.");
        }
    }
}
