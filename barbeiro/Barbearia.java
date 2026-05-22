package barbeiro;

import java.util.concurrent.Semaphore;

public class Barbearia {
    private final int totalCadeiras;
    private int clientesEsperando = 0; 

    
    private final Semaphore clientes;  
    private final Semaphore barbeiros; 
    private final Semaphore mutex;    

    public Barbearia(int cadeiras) {
        this.totalCadeiras = cadeiras;
        this.clientes = new Semaphore(0);
        this.barbeiros = new Semaphore(0);
        this.mutex = new Semaphore(1); 
    }

    // metodo executado pelo barbeiro
    public void cortarCabelo() throws InterruptedException {
        clientes.acquire(); //barbeiro dorme se não houver clientes
        
        mutex.acquire(); // pega o controle da região crítica
        clientesEsperando--; // um cliente a menos esperando
        barbeiros.release(); // barbeiro sinaliza que está pronto para cortar
        mutex.release(); // libera a região crítica
        
        System.out.println("Barbeiro: Cortando o cabelo de um cliente... (Restam " + clientesEsperando + " esperando)");
        Thread.sleep(2000); // simula o tempo do corte de cabelo
        System.out.println("Barbeiro: Terminou o corte.");
    }

    // metodo executado pelo Cliente
    public void tentarCortarCabelo(int idCliente) throws InterruptedException {
        mutex.acquire(); // tenta entrar na barbearia 
        
        if (clientesEsperando < totalCadeiras){
            clientesEsperando++; //espera
            System.out.println("Cliente " + idCliente + ": Entrou e sentou para esperar. (Esperando: " + clientesEsperando + ")");
            
            clientes.release(); // acorda o barbeiro 
            mutex.release(); // libera a região crítica para outros entrarem
            
            barbeiros.acquire(); // espera até que o barbeiro esteja pronto para ele
            System.out.println("Cliente " + idCliente + ": Está cortando o cabelo agora.");
         } else {
            // não tem cadeira vazia
            mutex.release(); // libera a região crítica e vai embora
            System.out.println("Cliente " + idCliente + ": Barbearia cheia! Foi embora sem cortar.");
        }
    }
}