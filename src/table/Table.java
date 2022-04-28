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
        this.createTablePosition();
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

        // For passando por cada casa do tabuleiro e setando o valor 0 ou 1 começando em 0
        // Quando passa pra próxima linha ele reajusta o valor do x para 1 ou 0 para n ficar igual a linah anterior
        for (int i = 0; i < 8; i++) {
            x = (i % 2 == 0) ?  1 : 0;
            for (int j = 0; j < 8; j++) {
                x = (x == 0) ? 1 : 0;
                this.houseTable[i][j] = aux[x];
            }
        }
    }
    // Coloca as posições inciais de cada jogador no tabuleiro
    private void createTablePosition() {
        // Para linha < 3 as peças de número 10 ocuparão as casas de valor 10
        // Para linha > 4 as peças de número 11 ocuparão as casas de valor 10
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(i < 3 && this.houseTable[i][j] == 0) {
                    this.houseTable[i][j] = 10;
                }
                if(i > 4 && houseTable[i][j] == 0) {
                    this.houseTable[i][j] = 11;
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
                if(j == 7) {
                    System.out.print(String.format("  %d", i));
                }
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
            }
            System.out.println();
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
    // Valida a posição da jogada
    public boolean validatePosition(char startA, int startY, char endA, int endY){
        int startX = this.convertPosition(startA);
        int endX = this.convertPosition(endA);   

        // Valida se a posição existe no tabuleiro
        if (endX - startX > 2 || endX - startX < -2) {
            return false;
        }
        if (endY - startY > 2 || endY - startY < -2) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(this.houseTable[i][j] > 2) {
                    return false;
                }
            }
        }
        return (endX %2 == 0 && endY %2 == 0);
    }
    // Valida de quem é a peça
    public boolean validatePiece(int player, char a, int y){      
        int playerNumber = player == 1 ? 10 : 11;
        int x = this.convertPosition(a);
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }
        return this.houseTable[x][y] == playerNumber;
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
    // Jogada Player -> Coordenadas Inciciais e Finas
    public void applyPlayerPlay(int player, char startA, int startY, char endA, int endY){
        int startX = this.convertPosition(startA);
        int endX = this.convertPosition(endA);
        int playerNumber = player == 1 ? 10 : 11;

        // ********* Validar se a casa do destino é do jogador
        // ********* 
        
        this.houseTable[endX][endY] = playerNumber;
        this.houseTable[startX][startY] = 0;
        if (startX-endX == 2 || startX-endX == -2) {
            if (this.houseTable[(startX+endX)/2][(startY+endY)/2] != playerNumber){                
                this.houseTable[(startX+endX)/2][(startY+endY)/2] = 0;
            }
        } 
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

