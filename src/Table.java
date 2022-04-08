public class Table {
    // create table with 8 rows and 8 columns
    public int[][] table = new int[8][8];
    
    
    public void createTable () {
        int[] aux = new int[2];
        aux[0] = 0;
        aux[1] = 1;
        int x = 1;

        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                x = 1;
            } else {
                x = 0;
            }
            for (int j = 0; j < 8; j++) {
                if (x == 0) {
                    x = 1;
                } else {
                    x = 0;
                }
                table[i][j] = aux[x];
            }
        }
    }

    public void createTablePosition () {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(i < 3 && table[i][j] == 0) {
                    table[i][j] = 10;
                };
                if(i > 4 && table[i][j] == 0) {
                    table[i][j] = 11;
                };
            }
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    // print table
    public void printTable() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(i < 3 ) {
                    if(table[i][j] == 0 || table[i][j] == 10) {
                        System.out.print(ANSI_BLACK_BACKGROUND + " " + ANSI_RED + table[i][j] + " " + ANSI_RESET);
                    } else {
                        System.out.print(ANSI_WHITE_BACKGROUND + "  " + " " + " " + ANSI_RESET);
                    }
                } else if (i > 4) {
                    if(table[i][j] == 0 || table[i][j] == 11) {
                        System.out.print(ANSI_BLACK_BACKGROUND + " " + ANSI_BLUE + table[i][j] + " " + ANSI_RESET);
                    } else {
                        System.out.print(ANSI_WHITE_BACKGROUND + "  " + " " + " " + ANSI_RESET);
                    }
                } else {
                    if(table[i][j] == 0) {
                        System.out.print(ANSI_BLACK_BACKGROUND + "  " + " " + " " + ANSI_RESET);
                    } else {
                        System.out.print(ANSI_WHITE_BACKGROUND + "  " + " " + " " + ANSI_RESET);
                    }
                }
                
            }
            System.out.println();
        }
    }
}
