package Play;
import table.*;
import pieces.*;

public class Play {
    Table table = new Table();
    Pieces pieces = new Pieces();

    // Funções da rodada
    public boolean isGameOver() {
        return false;
    }
    public int getPlayerTurn(int player){
        return player %2 == 0 ? 2 : 1;
    }
    public void getPlayerName(int player){
        String playerName = player %2 == 0 ? "Player 2" : "Player 1";
        System.out.println(String.format("Vez do jogador %s realizar a jogada", playerName));
    }

    // Jogada Player -> idPiece -> coordenada final
    public boolean applyPlayerPlay(int player, int idPiece, char endA, int endY) {
        int endX = this.convertPosition(endA);
        int lineInitial = table.getPieceLineOrCollumn(idPiece, 'L');
        int collumnInitial = table.getPieceLineOrCollumn(idPiece, 'C');
        int lineMedia;
        int collumnMedia;

        if (pieces.validatePiece(player, idPiece)) {
            if (validatePosition(player, idPiece, endY, endX)) {

                // Comportamento da peça
                // 1. Se a peça é Dama
                // 2. Se a peça é peão
                if (this.isSuperPiece(idPiece)) {
                    
                    // int diffLine = endY - lineInitial;
                    // int diffColumn = endX - collumnInitial;
                    // for (int i = lineInitial; i != endY;) {
                    //     for (int j = collumnInitial; j != endX;) {
                    //         if (diffLine > 0) {
                    //             i--;
                    //             diffLine--;
                    //         } else if (diffLine < 0) {
                    //             i++;
                    //             diffLine++;
                    //         } else if (diffColumn > 0) {
                    //             j--;
                    //             diffColumn--;
                    //         } else if (diffColumn < 0) {
                    //             j++;
                    //             diffColumn++;
                    //         }
                    //         if (table.getHouseTable(i, j) != 0 && pieces.getPlayerPiece(table.getHouseTable(i, j)) != player) {
                    //             // Se comeu tem que retirar a peça do openente;
                    //             pieces.setPiece(table.getHouseTable(i, j), 2, 1);
                    //             table.setHouseTable(i, j, 0);
                    //         }
                    //     }
                    // }
                } else {
                    // Validar se a peça pulou mais de uma linha 
                    // 1. se foi 2 linhas
                    if (Math.abs(lineInitial - endY) == 2 || Math.abs(collumnInitial - endX) == 2) {
                        lineMedia = (lineInitial + endY) / 2;
                        collumnMedia = (collumnInitial + endX) / 2;

                        // valida se a casa ado meio contem uma peça
                        if (table.getHouseTable(lineMedia, collumnMedia) != 0 && pieces.getPlayerPiece(table.getHouseTable(lineMedia, collumnMedia)) != player) {
                            // Se comeu tem que retirar a peça do openente;
                            pieces.setPiece(table.getHouseTable(lineMedia, collumnMedia), 2, 1);
                            table.setHouseTable(lineMedia, collumnMedia, 0);

                            this.playValid(idPiece, endX, endY);
                            return true;
                        } else {
                            return false;
                        }
                    }
                    else {
                        this.playValid(idPiece, endX, endY);
                        return true;
                    }
                }
            }
        } 
        return false;
    }
    
    // Jogada Valida
    private void playValid(int idPiece, int endX, int endY) {
        this.validateSuperPiece(idPiece, endY);
        table.setPosition(idPiece);
        table.setHouseTable(endY, endX, idPiece);
        table.printTableWithPieces();
    }
    // Validações da dama
    private void validateSuperPiece(int idPiece, int endY){
        int player = pieces.getPlayerPiece(idPiece);
        if (!this.isSuperPiece(idPiece)){
            if (player == 1 && endY == 7){
                setSuperPiece(idPiece);
            }
            else if (player == 2 && endY == 0){
                setSuperPiece(idPiece);
            }
        }
    }
    private boolean isSuperPiece(int idPiece){
        return pieces.getPiece(idPiece, 1) == 1 ? true : false;
    }
    private void setSuperPiece(int idPiece){
        pieces.setPiece(idPiece, 1, 1);
    }

    // Valida se a Jogada acontecera
    private boolean validatePosition(int player, int idPiece, int endY, int endX) {
        int lineInitial = table.getPieceLineOrCollumn(idPiece, 'L');

        // Se alguma das coordenadas for < 0 ou > 7 retorna false
        if (endY < 0 || endY > 7 || endX < 0 || endX > 7) {
            return false;
        }
        // Se a peça é um Peão valida quem é o player e se a direção é válida
        if (!this.isSuperPiece(idPiece)) {
            if (player == 1 ) {
                if (lineInitial > endY){
                    return false;
                }
            } else {
                if (lineInitial < endY){
                    return false;
                }
            }
        }
        // Se o destino é uma casa válida
        if (table.getHouseTable(endY,endX) == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Converte a posicao das letras para número
    private int convertPosition(char a){
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
}
