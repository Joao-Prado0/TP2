import java.util.Scanner;

public class Menu {

    private final Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("ESCOLHA O ALGORITMO PARA EXECUTAR:");
            System.out.println("1. Produtor/Consumidor (Parte I) [cite: 15]");
            System.out.println("2. Barbeiro Dorminhoco [cite: 184]");
            System.out.println("3. Leitores e Escritores [cite: 211]");
            System.out.println("4. Problema dos Fumantes [cite: 227]");
            System.out.println("5. Jantar dos Filósofos [cite: 237]");
            System.out.println("6. Jantar dos Selvagens (Opcional) [cite: 256]");
            System.out.println("7. Problema dos Três Robôs (Opcional) [cite: 265]");
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                processarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido.\n");
            }
        }
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> System.out.println("\nExecutando: Produtor/Consumidor...\n");
                // Ex: ProdutorConsumidorRunner.start();
            case 2 -> System.out.println("\nExecutando: Barbeiro Dorminhoco...\n");
            case 3 -> System.out.println("\nExecutando: Leitores e Escritores...\n");
            case 4 -> System.out.println("\nExecutando: Problema dos Fumantes...\n");
            case 5 -> System.out.println("\nExecutando: Jantar dos Filósofos...\n");
            case 6 -> System.out.println("\nExecutando: Jantar dos Selvagens...\n");
            case 7 -> System.out.println("\nExecutando: Problema dos Três Robôs...\n");
            case 0 -> System.out.println("Encerrando programa.");
            default -> System.out.println("Opção inválida!");
        }
    }
    
}