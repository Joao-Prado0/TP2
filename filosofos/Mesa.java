package filosofos;

import java.util.concurrent.Semaphore;

public class Mesa {
    private static final int TOTAL_FILOSOFOS = 5; // 
    private final Estado[] estados = new Estado[TOTAL_FILOSOFOS];
    private final Semaphore[] semaforosFilosofos = new Semaphore[TOTAL_FILOSOFOS];
    private final Semaphore mutex = new Semaphore(1); // Garante exclusão mútua ao mudar estados

    public Mesa() {
        for (int i = 0; i < TOTAL_FILOSOFOS; i++) {
            estados[i] = Estado.PENSANDO; // Todos começam pensando 
            semaforosFilosofos[i] = new Semaphore(0); // Bloqueados inicialmente
        }
    }

    public void pegarGarfos(int id) throws InterruptedException {
        mutex.acquire(); // Entra na região crítica
        estados[id] = Estado.FOME;
        System.out.println("Filósofo " + id + " está com FOME.");
        testar(id); // Tenta pegar os dois garfos 
        mutex.release(); // Sai da região crítica

        // Se não conseguiu os garfos no teste, vai ficar bloqueado aqui
        semaforosFilosofos[id].acquire();
    }

    public void largarGarfos(int id) throws InterruptedException {
        mutex.acquire(); // Entra na região crítica
        estados[id] = Estado.PENSANDO;
        System.out.println("Filósofo " + id + " terminou de comer e voltou a PENSAR.");
        
        // Avisa os vizinhos da esquerda e da direita que os garfos foram liberados
        testar(vizinhoEsquerda(id));
        testar(vizinhoDireita(id));
        
        mutex.release(); // Sai da região crítica
    }

    private void testar(int id) {
        // Um filósofo só pode comer se estiver com fome E nenhum dos vizinhos estiver comendo 
        if (estados[id] == Estado.FOME 
                && estados[vizinhoEsquerda(id)] != Estado.COMENDO 
                && estados[vizinhoDireita(id)] != Estado.COMENDO) {
            
            estados[id] = Estado.COMENDO;
            System.out.println("Filósofo " + id + " pegou os garfos e começou a COMER. 🍜");
            semaforosFilosofos[id].release(); // Libera o filósofo para rodar
        }
    }

    private int vizinhoEsquerda(int id) {
        return (id + TOTAL_FILOSOFOS - 1) % TOTAL_FILOSOFOS; // (id + 1) no sentido horário 
    }

    private int vizinhoDireita(int id) {
        return (id + 1) % TOTAL_FILOSOFOS; // id do palito à direita 
    }
}