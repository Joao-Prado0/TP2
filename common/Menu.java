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
            System.out.println("1. Barbeiro Dorminhoco");
            System.out.println("2. Leitores e Escritores ");
            System.out.println("3. Problema dos Fumantes ");
            System.out.println("4. Jantar dos Filósofos ");
            System.out.println("5. Jantar dos Selvagens");
            System.out.println("6. Problema dos Três Robôs");
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
            case 1 -> System.out.println("\nExecutando: Barbeiro Dorminhoco...\n");
            case 2 -> LeitoresEscritoresRunner.executar();
            case 3 -> System.out.println("\nExecutando: Problema dos Fumantes...\n");
            case 4 -> System.out.println("\nExecutando: Jantar dos Filósofos...\n");
            case 5 -> System.out.println("\nExecutando: Jantar dos Selvagens...\n");
            case 6 -> System.out.println("\nExecutando: Problema dos Três Robôs...\n");
            case 0 -> System.out.println("Encerrando programa.");
            default -> System.out.println("Opção inválida!");
        }
    }
    
}