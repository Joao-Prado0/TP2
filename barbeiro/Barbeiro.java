package barbeiro;

public class Barbeiro extends Thread {
    private Barbearia barbearia;

    public Barbeiro(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        while (true) { // barbeiro trabalha em loop infinito
            try {
                barbearia.cortarCabelo();
            } catch (InterruptedException e) {
                System.out.println("Expediente encerrado. Barbeiro foi para casa.");
                break; // sai do loop se a thread for interrompida no final
            }
        }
    }
}