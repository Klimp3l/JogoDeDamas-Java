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
        aux[1] = -1;
        int x = 1;

        int idPiece = 1;
        // For passando por cada casa do tabuleiro e setando o valor 0 ou -1 começando em 0 e seta as posições iniciais
        // Se a linha for menor que 3 e x = 0 e se linha maior que 4 e x = 0 a casa recebe idPeca (Sempre quando atribuir a casa uma peça ele acrescenta um no id auxiliar)
        // Quando passa pra próxima linha ele reajusta o valor do x para 1 ou 0 para n ficar igual a linha anterior
        for (int i = 0; i < 8; i++) {
            x = (i % 2 == 0) ?  1 : 0;
            for (int j = 0; j < 8; j++) {
                x = (x == 0) ? 1 : 0;
                if(i < 3 && x == 0) {
                    setHouseTable(i, j, idPiece);
                    idPiece++;
                }
                else if(i > 4 && x == 0) {
                    setHouseTable(i, j, idPiece);
                    idPiece++;
                } else {
                    setHouseTable(i, j, aux[x]);
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
                if(getHouseTable(i, j) <= 12 && getHouseTable(i, j) != -1 && getHouseTable(i, j) != 0) {
                    if (getHouseTable(i, j) < 10) {
                        System.out.print(ANSI_BLACK_BACKGROUND + "  " + ANSI_RED + getHouseTable(i, j) + " " + ANSI_RESET);
                    } else {                        
                        System.out.print(ANSI_BLACK_BACKGROUND + " " + ANSI_RED + getHouseTable(i, j) + " " + ANSI_RESET);
                    }
                } else if(getHouseTable(i, j) > 12 && getHouseTable(i, j) != -1) {
                    System.out.print(ANSI_BLACK_BACKGROUND + " " + ANSI_BLUE + getHouseTable(i, j) + " " + ANSI_RESET);
                } else if(getHouseTable(i, j) == 0) {
                    System.out.print(ANSI_BLACK_BACKGROUND + "    " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_WHITE_BACKGROUND + "    " + ANSI_RESET);
                }
                if (j == 7) {
                    System.out.print(String.format("  %d", i));
                }
            }
            System.out.println();
        }
    }

    // setar como 0 a coordenada inicial do idPeca 
    public void setPosition(int idPeca){        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getHouseTable(i, j) == idPeca) {
                    setHouseTable(i, j, 0);
                }
            }
        }
    }

    // pegar linha ou coluna do idPeca
    public int getPieceLineOrCollumn(int idPeca, char option) { 
        int retorno = -1;   
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getHouseTable(i, j) == idPeca) {
                    retorno = option == 'L' ? i : j;
                }
            }
        }
        return retorno;
    }

    // Pega o valor da casa
    public int getHouseTable(int i, int j) {
        return this.houseTable[i][j];
    }
    // Seta o valor da casa
    public void setHouseTable(int i, int j, int value) {
        this.houseTable[i][j] = value;
    }
    // Debugar os valores das casas
    public void debugTable(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(this.houseTable[i][j] + " ");
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
    
}