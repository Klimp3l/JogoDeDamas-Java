package Play;
import table.*;
import pieces.*;

public class Play {
    Table table = new Table(this);
    Pieces pieces = new Pieces();

    // Funções da rodada
    public boolean isGameOver(Table t) {
        int firstPlayerTotalPieces = 0;
        int secondPlayerTotalPieces = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (t.getHouseTable(i, j) != -1){
                    if (pieces.getPlayerPiece(t.getHouseTable(i,j)) == 1) {
                        firstPlayerTotalPieces++;
                    } else if (pieces.getPlayerPiece(t.getHouseTable(i,j)) == 2) {
                        secondPlayerTotalPieces++;
                    }
                }
                
            }
        }
        return (firstPlayerTotalPieces == 0 || secondPlayerTotalPieces == 0);
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
        int startY = lineInitial;
        int startX = collumnInitial;
        

        if (pieces.validatePiece(player, idPiece)) {
            if (validatePosition(player, idPiece, endY, endX)) {

                // Comportamento da peça
                // 1. Se a peça é Dama
                // 2. Se a peça é peão
                if (this.isSuperPiece(idPiece)) {
                    //matriz da xuxa
                    //pegar maior linha e coluna
                    int maxLine = Math.max(startX, endX);                    
                    int minLine = Math.min(startX, endX) + 1;
                    int maxCollumn = Math.max(startY, endY);
                    int minCollumn = Math.min(startY, endY) + 1;
                    //System.out.println(String.format("\nMaxLine: %d\nMinLine: %d\nMaxCollumn: %d\nMinCollumn: %d", maxLine, minLine, maxCollumn, minCollumn));
                    //verificar se a peça está na diagonal
                    //percorrendo a matriz

                    int j = minCollumn;
                    for (int i = minLine; i < maxLine; i++) {                        
                        if (pieces.getPlayerPiece(table.getHouseTable(j, i)) == player) {
                            return false;
                        }
                        j++;                        
                    }

                    j = minCollumn;
                    for (int i = minLine; i < maxLine; i++) {
                        
                        if (table.getHouseTable(j, i) != 0 && pieces.getPlayerPiece(table.getHouseTable(j, i)) != player) {
                            pieces.setPiece(table.getHouseTable(j, i), 2, 1);
                            table.setHouseTable(j, i, 0);

                        }

                        j++;
                        
                    }

                    
                    this.playValid(idPiece, endX, endY);
                    return true;
                   
                } else {
                    // Validar se a peça pulou mais de uma linha 
                    // 1. se foi 2 linhas

                    if (lineInitial - endY > 2 || endY - lineInitial > 2) {
                        return false;
                    }
                    
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
    public boolean isSuperPiece(int idPiece){
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
