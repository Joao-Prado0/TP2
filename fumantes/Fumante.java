package fumantes;

import java.util.Random;

public class Fumante extends Thread {
    private final String nome;
    private final String ingredientePossuido;
    private final MesaFumantes mesa;
    private final Random random = new Random();

    public Fumante(String nome, String ingredientePossuido, MesaFumantes mesa) {
        this.nome = nome;
        this.ingredientePossuido = ingredientePossuido;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        // Continua rodando até que o Runner encerre a simulação
        while (!Thread.currentThread().isInterrupted()) {
            try {
                mesa.fumantePegarIngredientes(ingredientePossuido);
                
                System.out.println("[" + nome + "] (tem " + ingredientePossuido + ") pegou os ingredientes na mesa.");
                System.out.println("[" + nome + "] está preparando o cigarro...");
                Thread.sleep(random.nextInt(1000) + 500);

                System.out.println("[" + nome + "] está fumando no fumódromo.");
                Thread.sleep(random.nextInt(1500) + 500);

                System.out.println("[" + nome + "] terminou de fumar e avisou o Agente.\n");
                mesa.fumanteTerminou();

            } catch (InterruptedException e) {
                // Quando o agente for embora, as threads são interrompidas e caem aqui
                System.out.println(">>> [" + nome + "] ENCERROU SUAS ATIVIDADES DO DIA. <<<");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}