
public class Main {
    public static void main(String[] args) {
        // Exibe o cabeçalho obrigatório uma única vez ao iniciar 
        imprimirCabecalhoGrupo();
        
        // Inicia o menu principal
        Menu menu = new Menu();
        menu.exibir();
    }

    private static void imprimirCabecalhoGrupo() {
      String cabecalho = """ 
      ====================================================
        PUC Minas - Engenharia de Software
        Sistemas Operacionais - Professor Diego Rocha
        TRABALHO PRÁTICO 2
        --------------------------------------------------
        GRUPO: 
        - João Prado
        - Davi Martins
        - Fillipe Gabriel
        - Daniel Bony
        - Gabriel Maximo
      ====================================================
        """;
      System.out.print(cabecalho);
    }
}