import java.util.ArrayList;

public class Grid {
    private  int GridRow = 3;
    private  int GridCol = 3;
    private  SmallGrid[][] bigGrid;

    public Grid(){
        bigGrid =new SmallGrid[GridRow][GridCol];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bigGrid[i][j]=new SmallGrid();
            }
        }
    }

    public SmallGrid[][] getBigGrid() {
        return bigGrid;
    }

    public void print(){
        System.out.println();
        System.out.println("----------+------------+------------+");
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j <3 ; ++j) {
                for (int k = 0; k <3 ; ++k) {
                    for (int l = 0; l < 3; ++l) {
                        char symbole =(bigGrid[i][k].getCells()[j][l].getState() == Cell.State.Empty? '_' : bigGrid[i][k].getCells()[j][l].getState() == Cell.State.O ? 'O' : 'X');
                        System.out.print(symbole + " | ");
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.print("----------+------------+------------+");
            System.out.println();
        }
    }

    public void setBigGrid(SmallGrid[][] bigGrid) {
        this.bigGrid = bigGrid;
    }


    public  Cell.State checkRowCol(Cell.State c1, Cell.State c2, Cell.State c3)
    {
        if ((c1 == Cell.State.X) && (c1 == c2) && (c2 == c3))
            return Cell.State.X;
        if ((c1 == Cell.State.O) && (c1 == c2) && (c2 == c3))
            return Cell.State.O;
        return Cell.State.Empty;
    }

    public Cell.State Win()
    {
        for (int i = 0; i < 3; i++) {
            // Rows
            if (checkRowCol(bigGrid[i][0].Win(),bigGrid[i][1].Win(),bigGrid[i][2].Win()) == Cell.State.X)
                return Cell.State.X;
            else if (checkRowCol(bigGrid[i][0].Win(), bigGrid[i][1].Win(),bigGrid[i][2].Win()) == Cell.State.O)
                return Cell.State.O;
                // Columns
            else if (checkRowCol(bigGrid[0][i].Win(), bigGrid[1][i].Win(), bigGrid[2][i].Win()) == Cell.State.X)
                return Cell.State.X;
            else if (checkRowCol(bigGrid[0][i].Win(), bigGrid[1][i].Win(), bigGrid[2][i].Win()) == Cell.State.O)
                return Cell.State.O;
                // Diameters
            else if ((checkRowCol(bigGrid[0][0].Win(),bigGrid[1][1].Win(), bigGrid[2][2].Win()) == Cell.State.X) || checkRowCol(bigGrid[0][2].Win(),bigGrid[1][1].Win(), bigGrid[2][0].Win()) == Cell.State.X)
                return Cell.State.X;
            else if ((checkRowCol(bigGrid[0][0].Win(),bigGrid[1][1].Win(), bigGrid[2][2].Win()) == Cell.State.O) || checkRowCol(bigGrid[0][2].Win(),bigGrid[1][1].Win(), bigGrid[2][0].Win()) == Cell.State.O)
                return Cell.State.O;
        }
        return Cell.State.Empty;
    }

    public boolean check(int row, int column,int x, int y)
    {
        if( (row>2 || column>2) || (row<0 || column <0) )
        {
            System.out.println("out of bounds matrix");
            return true;
        }
        else if (bigGrid[x][y].getCells()[row][column].getState() == Cell.State.X || bigGrid[x][y].getCells()[row][column].getState() == Cell.State.O)
        {
            System.out.println("already token");
            return true;
        }
        return false;
    }

}

