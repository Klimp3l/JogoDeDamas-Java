import java.util.Scanner;
import table.*;
import Play.*;

public class Main {
    public static void main(String[] args) throws Exception{
        int player = 1;
        Play play = new Play();
        Table table = new Table();
        Scanner scanner = new Scanner(System.in);        

        table.printTableWithPieces();
        
        while (!play.isGameOver()){
            int playerTurn = play.getPlayerTurn(player);
            play.getPlayerName(player);
            
            printInstructions();
            int idPiece = -1;
            char coluna = '0';
            int linha = -1;
            System.out.print("\nInforme o número da peça: ");
            do {
                if (scanner.hasNextInt()){
                    idPiece = scanner.nextInt();
                    scanner.nextLine();
                } else{
                    System.out.println("\nJogada Inválida! ");
                    System.out.print("Informe o número da peça(apenas números): ");
                    scanner.nextLine();
                }
            } while(idPiece == -1);

            
            System.out.print("Informe o número coluna: ");
            do {
                if (!scanner.hasNextInt()){
                    coluna = scanner.next().toUpperCase().charAt(0);
                    scanner.nextLine();
                } else{
                    System.out.println("Jogada Inválida! ");
                    System.out.print("\nInforme o nome coluna(apenas 1 letra): ");
                    scanner.nextLine();
                }
            } while(coluna == '0');

            System.out.print("Informe o número linha: ");
            do {
                if (scanner.hasNextInt()){
                    linha = scanner.nextInt();
                    scanner.nextLine();
                } else{
                    System.out.println("Jogada Inválida! ");
                    System.out.print("\nInforme o número da linha(apenas números): ");
                    scanner.nextLine();
                }
            } while(linha == -1);

            if (play.applyPlayerPlay(playerTurn, idPiece, coluna, linha)) {
                player++;
            } else {
                System.out.println("Jogada inválida, tente novamente");
            }
            
        }
        
    }

    public static void printInstructions(){
        System.out.println("\nPor favor digite sua jogada:");
        System.out.println("Informando Número da peça, coluna e linha");
    }
}