package barbeiro;

public class Cliente extends Thread {
    private Barbearia barbearia;
    private int idCliente;

    public Cliente(Barbearia barbearia, int idCliente) {
        this.barbearia = barbearia;
        this.idCliente = idCliente;
    }

    @Override
    public void run() {
        try {
            // simula um tempo para o cliente chegar 
            Thread.sleep((int) (Math.random() * 5000)); 
            barbearia.tentarCortarCabelo(idCliente);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}