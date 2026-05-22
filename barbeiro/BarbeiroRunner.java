package barbeiro;

public class BarbeiroRunner {
    public static void executar() {
        System.out.println("\n--- Abrindo a Barbearia ---");
        int NUM_CADEIRAS = 5;
        Barbearia barbearia = new Barbearia(NUM_CADEIRAS);

        // inicializa a thread do barbeiro
        Barbeiro barbeiro = new Barbeiro(barbearia);
        barbeiro.start();

        // cria e inicializa várias threads de clientes 
        int NUM_CLIENTES = 10;
        Cliente[] clientes = new Cliente[NUM_CLIENTES];
        for (int i = 0; i < NUM_CLIENTES; i++) {
            clientes[i] = new Cliente(barbearia, i + 1);
            clientes[i].start();
        }

        // aguarda todas as threads de clientes terminarem
        try {
            for (int i = 0; i < NUM_CLIENTES; i++) {
                clientes[i].join();
            }
            
            Thread.sleep(3000); 
            
            // interrompe o barbeiro para poder voltar ao menu principal
            barbeiro.interrupt(); 
            barbeiro.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("--- Barbearia Fechada ---\n");
    }
}