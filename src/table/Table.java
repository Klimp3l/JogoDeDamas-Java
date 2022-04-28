package table;

public class Table {
    // Cores para printar no console
    // Red -> Jogador 1
    // Blue -> Jogador 2
    private static final String ANSI_RED = "\u001B[31m"; 
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    private int[][] houseTable = new int[8][8];

    // Executa as funções para criar o tabuleiro
    public Table(){
        this.createTable();
    }

    // Cria o Tabuleiro e define as casas válidas
    private void createTable() {
        // Cria duas variaveis para setar os campos em que o Jogo de DAMA acontece
        // Casas validas = 0
        // Casas bloqueadas = 1
        int[] aux = new int[2];
        aux[0] = 0;
        aux[1] = 1;
        int x = 1;

        // For passando por cada casa do tabuleiro e setando o valor 0 ou 1 começando em 0 e seta as posições iniciais
        // Se a linha for menor que 3 e x = 0 a casa recebe 10 se linha for maior que 4 e x = 0 a casa recebe 11 
        // Quando passa pra próxima linha ele reajusta o valor do x para 1 ou 0 para n ficar igual a linah anterior
        for (int i = 0; i < 8; i++) {
            x = (i % 2 == 0) ?  1 : 0;
            for (int j = 0; j < 8; j++) {
                x = (x == 0) ? 1 : 0;
                if(i < 3 && x == 0) {
                    this.houseTable[i][j] = 10;
                }
                else if(i > 4 && x == 0) {
                    this.houseTable[i][j] = 11;
                } else {
                    this.houseTable[i][j] = aux[x];
                }
            }
        }
    }
    
    // Printa os Tabuleiros com as peças
    public void printTableWithPieces(){
        // Printa a primeira para localizar das coordenadas x
        // E se a coluna ('j') for 7 printa o número da linha ('i')
        System.out.println("  A   B   C   D   E   F   G   H");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Valida qual o valor das casa e printa as cores
                if(this.houseTable[i][j] == 10) {
                    System.out.print(ANSI_BLACK_BACKGROUND + " " + ANSI_RED + this.houseTable[i][j] + " " + ANSI_RESET);
                } else if(this.houseTable[i][j] == 11) {
                    System.out.print(ANSI_BLACK_BACKGROUND + " " + ANSI_BLUE + this.houseTable[i][j] + " " + ANSI_RESET);
                } else if(this.houseTable[i][j] == 0) {
                    System.out.print(ANSI_BLACK_BACKGROUND + "    " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_WHITE_BACKGROUND + "    " + ANSI_RESET);
                }
                if(j == 7) {
                    System.out.print(String.format("  %d", i));
                }
            }
            System.out.println();
        }
    }

    // Pega o score de cada jogador
    public void getScore(){
        int player1 = 0;
        int player2 = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(i < 3 ) {
                    if(this.houseTable[i][j] == 0 || this.houseTable[i][j] == 10) {
                        player1++;
                    }
                } else if (i > 4) {
                    if(this.houseTable[i][j] == 0 || this.houseTable[i][j] == 11) {
                        player2++;
                    }
                }
            }
        }        
        System.out.println("Score: ");
        System.out.println("Player 1: " + player1);
        System.out.println("Player 2: " + player2);
    }
    
    // Convert a posicao das letras para número
    public int convertPosition(char a){
        int x = 0;
        switch (a) {
            case 'A':
                x = 0;
                break;
            case 'B':
                x = 1;
                break;
            case 'C':
                x = 2;
                break;
            case 'D':
                x = 3;
                break;
            case 'E':
                x = 4;
                break;
            case 'F':
                x = 5;
                break;
            case 'G':
                x = 6;
                break;
            case 'H':
                x = 7;
                break;
            default:
                break;
        }
        return x;
    }
    
    // Valida a posição da jogada
    public boolean validatePosition(int playerNumber, int endY, int endX) {
        if (gethouseTable(endY,endX) == playerNumber) {
            return false;
        }
        return (gethouseTable(endY, endX) == 0);
    }
    
    // Valida se a peça é de quem ta jogando e se as posições passadas são validas
    public boolean validatePiece(int playerNumber, int y, int x){
        if (y < 0 || y > 7 || x < 0 || x > 7) {
            return false;
        }
        return gethouseTable(y, x) == playerNumber;
    }
    
    // Jogada Player -> Coordenadas Inciciais e Finas
    public void applyPlayerPlay(int player, char startA, int startY, char endA, int endY){
        int startX = this.convertPosition(startA);
        int endX = this.convertPosition(endA);
        int playerNumber = player == 1 ? 10 : 11;

        if (validatePiece(playerNumber, startY, startX)) {
            if (validatePosition(playerNumber, endY, endX)) {
                sethouseTable(startY, startX, 0);
                sethouseTable(endY, endX, playerNumber);
            } else {
                System.out.println("A posição destino não é valida Jogador "+player);
            }
        } else {
            System.out.println("Não Há peças sua nessa casa Jogador "+player);
        }
    }

    // Pega o valor da casa
    public int gethouseTable(int i, int j) {
        return this.houseTable[i][j];
    }
    // Seta o valor da casa
    public void sethouseTable(int i, int j, int value) {
        this.houseTable[i][j] = value;
    }

    public void debugTable(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(this.houseTable[i][j] + " ");
            }
            System.out.println();
        }
    }
}

git config --global user.email "bernardokozan@gmail.com"
  git config --global user.name "Your Name"