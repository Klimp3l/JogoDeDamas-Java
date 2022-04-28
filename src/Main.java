import table.Table;

public class Main {

    public static void main(String[] args) {
        
        Table table = new Table();

        table.printTableWithPieces();
        System.out.println();
        // Função para debugar valores da casa
        // table.debugTable();

        // Jogada
        table.applyPlayerPlay(1, 'C', 2, 'D', 3);

        //table.getScore();
        table.printTableWithPieces();
    }
}