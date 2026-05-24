package fumantes;

import java.util.Random;

public class Agente extends Thread {
    private final MesaFumantes mesa;
    private final Random random = new Random();

    public Agente(MesaFumantes mesa) {
        this.mesa = mesa;
    }

    @Override
    public void run() {
        // Simula o agente distribuindo ingredientes 6 vezes
        for (int i = 0; i < 6; i++) {
            try {
                int escolha = random.nextInt(3);
                mesa.agenteColocarIngredientes(escolha);
                
                // Pequena pausa entre rodadas
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                System.out.println("[Agente] Foi interrompido.");
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println(">>> [Agente] ENCERROU SEU TURNO DO DIA. <<<");
    }
}