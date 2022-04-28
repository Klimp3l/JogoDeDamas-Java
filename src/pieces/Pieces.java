package pieces;

public class Pieces {
    // Dois tipos de peças
    // Atributos:
    // 0 - Id
    // 1 - Jogador
    // 2 - Função
    // 3 - Situação
    private int[][] piecesOfJogador = new int[24][4];

    public Pieces() {
        this.createPieces();
    }

    public void createPieces() {
        for (int i=0; i < 24; i++) {
            for (int j=0; j < 4; j++) {
                if (j==0) {
                    // Id
                    this.piecesOfJogador[i][j] = i+1;
                } else if (j==1) {
                    // Jogador
                    this.piecesOfJogador[i][j] = i < 12 ? 10 : 11;
                } else if (j==2) {
                    // Funcao -> 0: peão, 1: dama
                    this.piecesOfJogador[i][j] = 0;
                } else if (j==3) {
                    // Situacao -> 0: ativa, 1: inativa
                    this.piecesOfJogador[i][j] = 0;
                }
            }
        }
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
