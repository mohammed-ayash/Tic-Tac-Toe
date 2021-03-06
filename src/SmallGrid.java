import java.lang.reflect.Method;
import java.util.ArrayList;

public class SmallGrid {
    private int smalllRow = 3;
    private int smallCol = 3;
    private int weight;
    private Cell [][] Cells;

    SmallGrid(int weight) {
        this.weight = weight;
        Cells = new Cell[smalllRow][smallCol];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Cells[i][j]=new Cell(i,j, controller.getWeight(i,j));
            }
    }



    public ArrayList<Cell> getAllPossibleMoves()
    {
        ArrayList<Cell> arrayCell = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Cells[i][j].getState() == Cell.State.Empty) {
                    arrayCell.add(Cells[i][j]);
                }
            }
        }
        return arrayCell;
    }


    public int getWeight() {
        return weight;
    }

    public Cell[][] getCells()
    {
        return Cells;
    }

    public void setCells(Cell[][] Cells)
    {
        this.Cells = Cells;
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
            if (checkRowCol(Cells[i][0].getState(), Cells[i][1].getState(), Cells[i][2].getState()) == Cell.State.X)
                return Cell.State.X;
            else if (checkRowCol(Cells[i][0].getState(), Cells[i][1].getState(), Cells[i][2].getState()) == Cell.State.O)
                return Cell.State.O;
                // Columns
            else if (checkRowCol(Cells[0][i].getState(), Cells[1][i].getState(), Cells[2][i].getState()) == Cell.State.X)
                return Cell.State.X;
            else if (checkRowCol(Cells[0][i].getState(), Cells[1][i].getState(), Cells[2][i].getState()) == Cell.State.O)
                return Cell.State.O;
        }
            // Diameters
        if ((checkRowCol(Cells[0][0].getState(),Cells[1][1].getState(), Cells[2][2].getState()) == Cell.State.X) || checkRowCol(Cells[0][2].getState(),Cells[1][1].getState(), Cells[2][0].getState()) == Cell.State.X)
            return Cell.State.X;
        else if ((checkRowCol(Cells[0][0].getState(),Cells[1][1].getState(), Cells[2][2].getState()) == Cell.State.O) || checkRowCol(Cells[0][2].getState(),Cells[1][1].getState(), Cells[2][0].getState()) == Cell.State.O)
            return Cell.State.O;

        return Cell.State.Empty;
    }


    public int getEvaluatePlayer(Cell.State player){
        int score = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Cells[i][j].getState() == player){
                    score += Cells[i][j].getWeight();
                }
            }
        }
        return score;
    }

}
