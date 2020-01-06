import java.util.ArrayList;
import java.util.Scanner;

public class controller {


  //  boolean firstMove=true;

    // Methods
    public boolean isMovesFinish(Cell board[][]){
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j].getState() == Cell.State.Empty)
                    return true;
        return false;
    }
    /*
    public int evaluate(Cell board[][]){
        // OpponentWinPoints = 1000
        // ComputerWinPoints = -1000
        // Checking for Rows
        for (int row = 0; row < 3; row++)
        {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2])
            {
                if (board[row][0] == 'x')
                    return +10;
                else if (board[row][0] == 'o')
                    return -10;
            }
        }
        // Checking for Columns
        for (int col = 0; col < 3; col++)
        {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col])
            {
                if (board[0][col] == 'x')
                    return +10;

                else if (board[0][col] == 'o')
                    return -10;
            }
        }
        // Checking for Diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
        {
            if (board[0][0] == 'x')
                return +10;
            else if (board[0][0] == 'o')
                return -10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
        {
            if (board[0][2] == 'x')
                return +10;
            else if (board[0][2] == 'o')
                return -10;
        }
        return 0;
    }
*/
    private int Evalueate(Cell board[][])
    {
        int score = 0;
        // Rows Win
        for (int i = 0; i < 3; i++)
        {
            if (board[i][0].getState() ==board[i][1].getState() && board[i][1].getState()== board[i][ 2].getState())
            {
                if (board[i][0].getState() == Cell.State.O)
                score += 100;
                    else if (board[i][0].getState() == Cell.State.X)
                score -= 100;

            }
        }
        // Column Win
        for (int i = 0; i < 3; i++)
        {
            if (board[0][i].getState() == board[1][i].getState() && board[1][i].getState() == board[2][i].getState())
            {
                if (board[0][i].getState() == Cell.State.O)
                score += 100;
                    else if (board[0][i].getState() == Cell.State.X)
                score -= 100;
            }
        }
        // Diameters Win
        if (board[0][0].getState()== board[1][1].getState()&& board[1][1].getState()== board[2][2].getState())
        {
            if (board[0][0].getState() == Cell.State.O)
            score += 100;
                else if (board[0][0].getState() == Cell.State.X)
            score -= 100;
        }

        if (board[0][2].getState() == board[1][1].getState() && board[1][1].getState() == board[2][0].getState())
        {
            if (board[0][2].getState() == Cell.State.O)
            score += 100;
                else if (board[0][2].getState() == Cell.State.X)
            score -= 100;
        }

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j].getState() == Cell.State.O)
                score += 1;
                if (board[i][j].getState() == Cell.State.X)
                score -= 1;
            }

            if ((board[i][0].getState() == Cell.State.O && board[i][1].getState() == Cell.State.O) || (board[i][0].getState() == Cell.State.O && board[i][2].getState() == Cell.State.O) || (board[i][1].getState() == Cell.State.O && board[i][2].getState() == Cell.State.O))
            score += 10;

                else if ((board[i][0].getState() == Cell.State.X && board[i][1].getState() == Cell.State.X) || (board[i][0].getState() == Cell.State.X && board[i][2].getState() == Cell.State.X) || (board[i][1].getState() == Cell.State.X && board[i][2].getState() == Cell.State.X))
            score -= 10;

            if ((board[0][i].getState() == Cell.State.O && board[1][i].getState() == Cell.State.O) || (board[0][i].getState() == Cell.State.O && board[2][i].getState() == Cell.State.O) || (board[1][i].getState() == Cell.State.O && board[2][i].getState() == Cell.State.O))
            score += 10;
                else if ((board[0][i].getState() == Cell.State.X && board[1][i].getState() == Cell.State.X) || (board[0][i].getState() == Cell.State.X && board[2][i].getState() == Cell.State.X) || (board[1][i].getState() == Cell.State.X && board[2][i].getState() == Cell.State.X))
            score -= 10;
        }
        //if (board[1][1].getState() == Cell.State.O)
        //    score += 25;
        //if (board[1][1].getState() == Cell.State.X)
        //    score -= 25;


        return score;
    }


    public int minMax(Grid grid,SmallGrid board,int depth,boolean isMax){
        int score=Evalueate(board.getCells());
        // If Opponent (Max) has won the game
        if(depth == 0)
            return score;
        // No Steps Available and No Winner
        if(!isMovesFinish(board.getCells()))
            return 0;
        // Opponent(Max) Moves
        if(isMax){
            int temp = -10000;
            for (int i = 0; i <3 ; i++) {
                for (int j = 0; j <3 ; j++) {
                    if(board.getCells()[i][j].getState() == Cell.State.Empty){
                        int weight=board.getCells()[i][j].getWeight();
                        board.getCells()[i][j].setState(Cell.State.O);
                        temp=Math.max(temp,(score*weight)+minMax(grid,grid.bigGrid[i][j],depth-1,false));
                        board.getCells()[i][j].setState(Cell.State.Empty);
                    }
                }
            }
            return temp;
        }
        else {
            int temp=+10000;
            for (int i = 0; i <3 ; i++) {
                for (int j = 0; j <3 ; j++) {
                    if(board.getCells()[i][j].getState() == Cell.State.Empty){
                        int weight=board.getCells()[i][j].getWeight();
                        board.getCells()[i][j].setState(Cell.State.X);
                        temp=Math.min(temp,(score*weight)+minMax(grid,grid.bigGrid[i][j],depth-1,true));
                        board.getCells()[i][j].setState(Cell.State.Empty);
                    }
                }
            }
            return temp;
        }
    }

    public Position computerMove(Grid grid,SmallGrid board,int depth){
        int temp=-10000;
        int i,j;
        ArrayList<Cell> possibleMove;
            possibleMove = board.getAllPossibleMoves();
            Position position = new Position();
            position.row = -1;
            position.col = -1;
            for (Cell move : possibleMove) {
                i = move.getX();
                j = move.getY();
                // Check if cell is empty
                if (board.getCells()[i][j].getState() == Cell.State.Empty) {
                    // Make the move
                    board.getCells()[i][j].setState(Cell.State.O);
                    int moveVal = minMax(grid, grid.bigGrid[i][j], depth, false);
                    // Undo the move
                    board.getCells()[i][j].setState(Cell.State.Empty);
                    // If the value of the current move is more than the best value, then update temp
                    if (moveVal > temp) {
                        position.row = i;
                        position.col = j;
                        temp = moveVal;
                    }

                }
            }

            System.out.printf(" The value of the best Move " + "is : %d ", temp);
            System.out.println("positin" + position.row + " " + position.col);
            board.getCells()[position.row][position.col].setState(Cell.State.O);
            return position;

    }
    /*public void startGame(){
        int row, col;
        while (grid.Win() == '_'){
            if(firstMove)
            {
                System.out.print(" Enter Your Location in Big Grid :");
                x = scanner.nextInt();
                y = scanner.nextInt();
                if((x>2 || y>2) || (x<0 || y <0)){
                    System.out.print(" You went out of bounds matrix ReEnter Your Location in Big Grid :");
                    x = scanner.nextInt();
                    y = scanner.nextInt();
                }
                System.out.print(" Enter Your Location in Small Grid :");
                row = scanner.nextInt();
                col = scanner.nextInt();
                // To check is any cell are token or not
                while (grid.check(row,col)){
                    System.out.println(" This Location is already token Or went out of bounds matrix");
                    grid.print();
                    System.out.print(" ReEnter your location :");
                    row=scanner.nextInt();
                    col=scanner.nextInt();
                }
                grid.bigGrid[x][y].smallGrid[row][col]='x';
                grid.print();
                // if for check for win
                // else if for check for game finish(ismovesfinish)
                Position position=computerMove(grid.bigGrid[row][col].smallGrid);
                x=position.row;
                y=position.col;
                grid.print();
                firstMove=false;
            }
            else {
                System.out.print(" \n Enter Your Location in Small Grid :");
                row = scanner.nextInt();
                col = scanner.nextInt();
                grid.bigGrid[x][y].smallGrid[row][col]='x';
                grid.print();
                Position position=computerMove(grid.bigGrid[row][col].smallGrid);
                x=position.row;
                y=position.col;
                grid.print();
            }
        }

    }
*/
}


//TODO set all variable private
// if the grid win closed.

