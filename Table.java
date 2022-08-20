enum Case {
    EMPTY, O, X;
 }

public class Table {
    
    Case[][] table = new Case[6][7];

    public Table(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++)
            this.table[i][j] = Case.EMPTY;
        }
    }

    void printTable() {
        System.out.println(" _______________");
        for(int i = 5; i >= 0; i--) {
            System.out.print("| ");
            for(int j = 0; j < 7; j++) {
                if(table[i][j] == Case.EMPTY) {
                    System.out.print("* ");
                } else {
                    System.out.print(table[i][j] + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("|---------------|");
    }

    boolean addToken(Case color, int column) {
        
        if(column < 1 || column > 7) {
            System.out.println("Error in the column number.\nThe column number must be between 1 and 7.");
            return false;
        }

        int i = 0;
        while(this.table[i][column-1] != Case.EMPTY){
            i++;
            if(i == 6){
                System.out.println("Error in the column number.\nThis column is already filled.");
                return false;
            }
        }
        this.table[i][column-1] = color;
        return true;
    }

}