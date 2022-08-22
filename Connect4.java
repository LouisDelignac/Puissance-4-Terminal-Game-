import java.util.Scanner;

public class Connect4 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Connect 4 by Louis Delignac.\nDo you want to started the game? ('y' or 'n')");
        char start = yesOrNo(scan); // gets the response from the players

        while(start == 'y') {
            runGame(scan); // start the game
            System.out.println("\n________________________________________\n________________________________________\n\nDo you want to play again? ('y' or 'n')");
            scan.nextLine();
            start = yesOrNo(scan); // asks if the players want to start a new game or stop
        }
        
        System.out.println("Oh ! It is very sad... Maybe next time !\n\nGAME OVER");
        scan.close();
    }

    static char yesOrNo(Scanner scan) {
    /* takes the scanner as a parameter
     * return 'y' or 'n'
     */
        String response;
        response = scan.nextLine().toLowerCase();

        while(response.length() == 0 || response.charAt(0) != 'y' && response.charAt(0) != 'n') {
            response = scan.nextLine().toLowerCase();       
        }
        return response.charAt(0);
    }

    static void runGame(Scanner scan) {
    /* takes the scanner as a parameter
     * creates the game, run it and verify and checks if the game is over 
     */
        Grid game = initGame();
        boolean connect4 = false;
        boolean equality = false;
        Grid.Case actualPlayer = Grid.Case.X;

        while(!connect4 && !equality) {
            System.out.println("It is the turn of Player " + actualPlayer + ".\nWhere do you want to place your token?");
            int column = scan.nextInt();
            
            if(game.addToken(actualPlayer, column)) {
                switch(actualPlayer) {
                    case X: actualPlayer = Grid.Case.O;
                    break;
                    default: actualPlayer = Grid.Case.X;
                }
                game.printGrid();
                connect4 = game.checkConnectFour(column-1);
                equality = game.checkEquality();

                if(connect4) {
                    System.out.println("GG Player " + actualPlayer);
                }
            }
        }
    }

    static Grid initGame() {
    /* creates a new instance of the class Grid and prints and return it */
        Grid game = new Grid();
        game.printGrid();
        return game;
    }
}