package fumantes;

import java.util.concurrent.Semaphore;

public class MesaFumantes {

    // Semáforo do agente inicia em 1 para ele dar o primeiro passo
    private final Semaphore agenteSem = new Semaphore(1);
    
    // Semáforos dos fumantes iniciam travados (0)
    private final Semaphore fumanteTabaco = new Semaphore(0);
    private final Semaphore fumantePapel = new Semaphore(0);
    private final Semaphore fumanteFosforo = new Semaphore(0);

    public void agenteColocarIngredientes(int escolha) throws InterruptedException {
        // Agente aguarda a mesa ser liberada
        agenteSem.acquire();
        
        if (escolha == 0) {
            System.out.println("[Agente] Colocou PAPEL e FÓSFOROS na mesa.");
            fumanteTabaco.release();
        } else if (escolha == 1) {
            System.out.println("[Agente] Colocou TABACO e FÓSFOROS na mesa.");
            fumantePapel.release();
        } else {
            System.out.println("[Agente] Colocou TABACO e PAPEL na mesa.");
            fumanteFosforo.release();
        }
    }

    public void fumantePegarIngredientes(String ingrediente) throws InterruptedException {
        // Cada fumante trava no seu respectivo semáforo até o agente liberá-lo
        if (ingrediente.equals("Tabaco")) fumanteTabaco.acquire();
        else if (ingrediente.equals("Papel")) fumantePapel.acquire();
        else if (ingrediente.equals("Fósforo")) fumanteFosforo.acquire();
    }

    public void fumanteTerminou() {
        // Libera a mesa para o agente colocar mais ingredientes
        agenteSem.release();
    }
}