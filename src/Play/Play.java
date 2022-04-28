package Play;
import table.*;
import pieces.*;

public class Play {
    Table table = new Table();
    Pieces pieces = new Pieces();

    public boolean isGameOver() {
        return false;
    }

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
    
    // Jogada Player -> Coordenadas Inciciais e Finas
    public boolean applyPlayerPlay(int player, int idPeca, char endA, int endY){
        int endX = this.convertPosition(endA);
        int line = table.getPieceLine(idPeca);
        int collumn = table.getPieceCollumn(idPeca);
        int lineMedia = (line + endY) / 2;
        int collumnMedia = (collumn + endX) / 2;

        // Validar se a peça é do Jogador;
        if (pieces.validatePiece(player, idPeca)) {           
            // Validar se a posicao destino é valida
            if (validatePosition(player, idPeca, endY, endX)) {
                if (this.isSuperPiece(idPeca)){
                    // Validar se a linha pulou mais de uma linha e valida se comeu uma peça; se não comeu retorna falso
                    // if (Math.abs(line - endY) == 2 || Math.abs(collumn - endX) == 2) {
                    //     // valida se comeu peça
                    //     if (table.gethouseTable(lineMedia, collumnMedia) != 0 && pieces.getPlayerPiece(table.gethouseTable(lineMedia, collumnMedia)) != player) {
                    //         // Se comeu tem que retirar a peça do openente;
                    //         pieces.setPiece(table.gethouseTable(lineMedia, collumnMedia), 2, 1);
                    //         table.sethouseTable(lineMedia, collumnMedia, 0);

                    //         table.setPosition(idPeca);
                    //         this.validateSuperPiece(idPeca, endY);
                    //         // Setar a coordenada destino com o idPeca
                    //         table.sethouseTable(endY, endX, idPeca);
                    //         table.printTableWithPieces();
                    //         return true;
                    //     } else {
                    //         return false;
                    //     }
                    //     // Se nao comeu peça retorna falso
                    // }
                    // else {
                    // // Setar a casa inicial como 0
                    //     table.setPosition(idPeca);
                    //     // Setar a coordenada destino com o idPeca
                    //     table.sethouseTable(endY, endX, idPeca);
                        
                    //     table.printTableWithPieces();
                    //     return true;
                    // }
                    //inicio line - endY
                    int diffLine = endY - line;
                    int diffColumn = endX - collumn;
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    for (int i = line; i != endY;) {
                        for (int j = collumn; j != endX;) {
                            if (diffLine > 0) {
                                i--;
                                diffLine--;
                            } else if (diffLine < 0) {
                                i++;
                                diffLine++;
                            } else if (diffColumn > 0) {
                                j--;
                                diffColumn--;
                            } else if (diffColumn < 0) {
                                j++;
                                diffColumn++;
                            }
                            if (table.gethouseTable(i, j) != 0 && pieces.getPlayerPiece(table.gethouseTable(i, j)) != player) {
                                // Se comeu tem que retirar a peça do openente;
                                pieces.setPiece(table.gethouseTable(i, j), 2, 1);
                                table.sethouseTable(i, j, 0);
                            }
                        }
                    }
                }else{
                
                    // Validar se a linha pulou mais de uma linha e valida se comeu uma peça; se não comeu retorna falso
                    if (Math.abs(line - endY) == 2 || Math.abs(collumn - endX) == 2) {
                        // valida se comeu peça
                        if (table.gethouseTable(lineMedia, collumnMedia) != 0 && pieces.getPlayerPiece(table.gethouseTable(lineMedia, collumnMedia)) != player) {
                            // Se comeu tem que retirar a peça do openente;
                            pieces.setPiece(table.gethouseTable(lineMedia, collumnMedia), 2, 1);
                            table.sethouseTable(lineMedia, collumnMedia, 0);

                            table.setPosition(idPeca);
                            this.validateSuperPiece(idPeca, endY);
                            // Setar a coordenada destino com o idPeca
                            table.sethouseTable(endY, endX, idPeca);
                            table.printTableWithPieces();
                            return true;
                        } else {
                            return false;
                        }
                        // Se nao comeu peça retorna falso
                    }
                    else {
                    // Setar a casa inicial como 0
                        table.setPosition(idPeca);
                        // Setar a coordenada destino com o idPeca
                        table.sethouseTable(endY, endX, idPeca);
                        
                        table.printTableWithPieces();
                        return true;
                    }
                }
            }
        } 
        return false;
    }

    // Valida a posição da jogada
    public boolean validatePosition(int player, int idPeca, int endY, int endX) {
        int line = table.getPieceLine(idPeca);
        int collumn = table.getPieceCollumn(idPeca);
        if (endY < 0 || endY > 7 || endX < 0 || endX > 7) {
            return false;
        }
        if (!this.isSuperPiece(idPeca)){
            if (player == 1 ) {
                if (line > endY){
                    return false;
                }
            } else {
                if (line < endY){
                    return false;
                }
            }
        }
       
        if (table.gethouseTable(endY,endX) == 0) {
            return true;
        } else {
            return false;
        }
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

    public int getPlayerTurn(int player){
        return player %2 == 0 ? 2 : 1;
    }

    public void getPlayerName(int player){
        String playerName = player %2 == 0 ? "Player 2" : "Player 1";
        System.out.println(String.format("Vez do jogador %s realizar a jogada", playerName));
    }
}
