package pieces;

public class Pieces {
    // Dois tipos de peças
    // Atributos:
    // 0 - Jogador
    // 1 - Função
    // 2 - Situação
    private int[][] piecesOfJogador = new int[25][4];

    public Pieces() {
        this.createPieces();
    }

    public void createPieces() {
        for (int i=0; i < 24; i++) {
            if (i==0) {

            } else {
                for (int j=0; j < 4; j++) {
                    if (j==0) {
                        // Jogador
                        this.piecesOfJogador[i][j] = i < 13 ? 1 : 2;
                    } else if (j==1) {
                        // Funcao -> 0: peão, 1: dama
                        this.piecesOfJogador[i][j] = 0;
                    } else if (j==2) {
                        // Situacao -> 0: ativa, 1: inativa
                        this.piecesOfJogador[i][j] = 0;
                    }
                }
            }
        }
    }

    // Valida se a peça é de quem ta jogando e se a peça está 'Ativa'
    public boolean validatePiece(int playerNumber, int idPeca){
        return getPiece(idPeca, 0) == playerNumber && getPiece(idPeca, 2) == 0;
    }

    // Pega o jogador da peca
    public int getPlayerPiece(int idPeca) {
        return getPiece(idPeca, 0);
    }
    // Pega o valor da casa
    public int getPiece(int i, int j) {
        return this.piecesOfJogador[i][j];
    }

    public int getPieceValue(int i, int j) {
        return this.piecesOfJogador[i][j];
    }
    // Seta o valor da casa
    public void setPiece(int i, int j, int value) {
        this.piecesOfJogador[i][j] = value;
    }

    public void debugPieces() {
        for (int i=0; i < 24; i++) {
            for (int j=0; j < 4; j++) {
                if (j==0) {
                    System.out.println("Id " + this.piecesOfJogador[i][j]);
                } else if (j==1) {
                    System.out.println("Jogador " + this.piecesOfJogador[i][j]);
                } else if (j==2) {
                    // Funcao -> P: peão, D: dama
                    System.out.println("Funcao " + this.piecesOfJogador[i][j]);
                } else if (j==3) {
                    // Situacao -> A: ativa, I: inativa
                    System.out.println("Situacao " + this.piecesOfJogador[i][j]);
                }
            }
            System.out.println();
        }
    }
}
