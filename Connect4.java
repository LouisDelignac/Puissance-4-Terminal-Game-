import java.util.Scanner;

public class Connect4 {

    public static void main(String[] args) {

        System.out.println("Welcome to Connect 4 by Louis Delignac.");

        Scanner scan = new Scanner(System.in);

        char start = ' ';
        while(start != 'y') {
            System.out.println("Do you want to started the game? ('y' or 'n')");
            start = scan.nextLine().toLowerCase().charAt(0);
            if(start == 'n') {
                System.out.println("Oh ! It is very sad...");
            }
        }

        while(start == 'y'){
            RunGame(scan);
            System.out.println("\n________________________________________\n________________________________________\n\nDo you want to play again? ('y' or 'n')");
            scan.nextLine();
            start = scan.nextLine().toLowerCase().charAt(0);
        }
        
        System.out.println("\nGAME OVER");

        scan.close();
    }

    static void RunGame(Scanner scan) {

        Table game = initGame();
        boolean connect4 = false;
        Case actualPlayer = Case.X;

        while(!connect4){
            System.out.println("It is the turn of Player " + actualPlayer + ".\nWhere do you want to place your token?");

            int column = scan.nextInt();
            
            if(game.addToken(actualPlayer, column)) {
                switch(actualPlayer){
                    case X: actualPlayer = Case.O;
                    break;
                    default: actualPlayer = Case.X;
                }
                game.printTable();
                connect4 = verifyConnectFour(game.table, column-1);
            }
        }
    }

    static Table initGame() {
        Table game = new Table();
        game.printTable();
        return game;
    }

    static boolean verifyConnectFour(Case[][] table, int lastTokenColumn) {

        // get the line of the last token
        int lastTokenLine = 0;
        while(table[lastTokenLine+1][lastTokenColumn] != Case.EMPTY) {
            lastTokenLine++;
        }

        Case winner = table[lastTokenLine][lastTokenColumn];

        // vertical test
        int line = lastTokenLine;
        int cpt = 1;
        while(line-1 >= 0 && winner == table[line-1][lastTokenColumn]) {
            line--;
            cpt++;
        }
        if(cpt >= 4) {
            System.out.println("GG Player " + winner);
            return true;
        }

        // horrizontal test
        int column = lastTokenColumn;
        cpt = 1;
        while(column+1 < 7 && winner == table[lastTokenLine][column+1]) {
            column++;
            cpt++;
        }
        column = lastTokenColumn;
        while(column-1 >= 0 && winner == table[lastTokenLine][column-1]) {
            column--;
            cpt++;
        }
        if(cpt >= 4) {
            System.out.println("GG Player " + winner);
            return true;
        }

        // diagonal test 1 : bottom left and top right
        line = lastTokenLine;
        column = lastTokenColumn;
        cpt = 1;
        while(line+1 < 6 && column+1 < 7 && winner == table[line+1][column+1]) {
            line++;
            column++;
            cpt++;
        }
        line = lastTokenLine;
        column = lastTokenColumn;
        while(line-1 >= 0 && column-1 >= 0 && winner == table[line-1][column-1]) {
            line--;
            column--;
            cpt++;
        }
        if(cpt >= 4) {
            System.out.println("GG Player " + winner);
            return true;
        }

        // diagonal test 2 : top left and bottom right
        line = lastTokenLine;
        column = lastTokenColumn;
        cpt = 1;
        while(line-1 >= 0 && column+1 < 7 && winner == table[line-1][column+1]) {
            line--;
            column++;
            cpt++;
        }
        line = lastTokenLine;
        column = lastTokenColumn;
        while(line+1 < 6 && column-1 >= 0 && winner == table[line+1][column-1]) {
            line++;
            column--;
            cpt++;
        }
        if(cpt >= 4) {
            System.out.println("GG Player " + winner);
            return true;
        }

        return false;
    }

}