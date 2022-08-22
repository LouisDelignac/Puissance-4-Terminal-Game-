/**
 * attribute : grid which represents a Connect 4 grid
 * methods : constructor, PrintGrid, addToken, verifyConnectFour
 * enum : Case
 */
public class Grid {
    
    Case[][] grid = new Case[6][7];

    enum Case {
        EMPTY, O, X;
     }

    public Grid(){
    /* creates an empty grid */
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++)
            this.grid[i][j] = Case.EMPTY;
        }
    }

    void printGrid() {
    /* print the grid as a Connect 4 grid */
        System.out.println(" _______________");
        for(int i = 5; i >= 0; i--) {
            System.out.print("| ");
            for(int j = 0; j < 7; j++) {
                if(grid[i][j] == Case.EMPTY) {
                    System.out.print("* ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("|---------------|");
    }

    boolean addToken(Case player, int column) {
    /* adds a player's token to the column */
        if(column < 1 || column > 7) {
            System.out.println("Error in the column number.\nThe column number must be between 1 and 7.");
            return false;
        }

        int i = 0;
        while(this.grid[i][column-1] != Case.EMPTY){
            i++;
            if(i == 6){
                System.out.println("Error in the column number.\nThis column is already filled.");
                return false;
            }
        }
        this.grid[i][column-1] = player;
        return true;
    }

    boolean checkConnectFour(int lastTokenColumn) {
        /* checks if the last token played creates is a Connect 4 */
    
            // gets the line of the last token
            int lastTokenLine = 0;
            while(lastTokenLine < 5 && this.grid[lastTokenLine+1][lastTokenColumn] != Case.EMPTY) {
                lastTokenLine++;
            }
     
            Case winner = this.grid[lastTokenLine][lastTokenColumn];
    
            // vertical test
            int line = lastTokenLine;
            int cpt = 1;
            while(line-1 >= 0 && winner == this.grid[line-1][lastTokenColumn]) {
                line--;
                cpt++;
            }
            if(cpt >= 4) {
                return true;
            }
    
            // horrizontal test
            int column = lastTokenColumn;
            cpt = 1;
            while(column+1 < 7 && winner == this.grid[lastTokenLine][column+1]) {
                column++;
                cpt++;
            }
            column = lastTokenColumn;
            while(column-1 >= 0 && winner == this.grid[lastTokenLine][column-1]) {
                column--;
                cpt++;
            }
            if(cpt >= 4) {
                return true;
            }
    
            // diagonal test 1 : bottom left and top right
            line = lastTokenLine;
            column = lastTokenColumn;
            cpt = 1;
            while(line+1 < 6 && column+1 < 7 && winner == this.grid[line+1][column+1]) {
                line++;
                column++;
                cpt++;
            }
            line = lastTokenLine;
            column = lastTokenColumn;
            while(line-1 >= 0 && column-1 >= 0 && winner == this.grid[line-1][column-1]) {
                line--;
                column--;
                cpt++;
            }
            if(cpt >= 4) {
                return true;
            }
    
            // diagonal test 2 : top left and bottom right
            line = lastTokenLine;
            column = lastTokenColumn;
            cpt = 1;
            while(line-1 >= 0 && column+1 < 7 && winner == this.grid[line-1][column+1]) {
                line--;
                column++;
                cpt++;
            }
            line = lastTokenLine;
            column = lastTokenColumn;
            while(line+1 < 6 && column-1 >= 0 && winner == this.grid[line+1][column-1]) {
                line++;
                column--;
                cpt++;
            }
            if(cpt >= 4) {
                return true;
            }
    
            return false;
        }

        boolean checkEquality() {
        /* checks if all columns are filled */
            for(int i = 0; i < 7; i++)
            if(this.grid[5][i] == Case.EMPTY) {
                return false;
            }
            return true;
        }
}