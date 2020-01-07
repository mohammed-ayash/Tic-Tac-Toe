import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Scanner;

public class controller {

    // Methods
    public boolean isMovesFinish(Cell board[][]){
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j].getState() == Cell.State.Empty)
                    return true;
        return false;
    }

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
                    score += board[i][j].getWeight();
                if (board[i][j].getState() == Cell.State.X)
                    score -= board[i][j].getWeight();
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


        return score;
    }

    public int EvalueateBiggrid(Grid grid){
        SmallGrid board;
        int score=0;
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
            {   board= grid.getBigGrid()[i][j];
                score+=Evalueate(board.getCells());
            }
        return score;
    }


    public int minMax(Grid grid,SmallGrid board,int depth,boolean isMax,int alpha,int beta){
        int scoreBeta=beta;
        int scoreAlpha=alpha;
        int s;
        // If Opponent (Max) has won the game
        if(depth == 0) {
            s=EvalueateBiggrid(grid);
            return s;
        }
        // No Steps Available and No Winner
        //if the matrix is closed
        if (board.Win() != Cell.State.Empty || !isMovesFinish(board.getCells())) {
            if (isMax) {
                //   int temp = -10000;
                for (int a = 0; a < 3; a++)
                    for (int b = 0; b < 3; b++)
                        if (grid.getBigGrid()[a][b].Win() == Cell.State.Empty || isMovesFinish(board.getCells())) {
                            scoreAlpha =  minMax(grid, grid.getBigGrid()[a][b], depth - 1, true,scoreAlpha,scoreBeta);
                            scoreAlpha = Math.max(alpha,scoreAlpha);
                            if (scoreAlpha < alpha)
                                return alpha;
                        }
                return scoreAlpha;
            } else {
                //   int temp = +10000;
                for (int a = 0; a < 3; a++)
                    for (int b = 0; b < 3; b++)
                        if (grid.getBigGrid()[a][b].Win() == Cell.State.Empty) {
                            scoreBeta=minMax(grid, grid.getBigGrid()[a][b], depth - 1, false,alpha,scoreBeta);
                            scoreBeta = Math.min(beta,scoreBeta);
                            if (scoreBeta > beta)
                                return alpha;
                        }
                return scoreBeta;
            }
        }
        // Opponent(Max) Moves
        if(isMax){
            //  int temp = Integer.MIN_VALUE;
            for (int i = 0; i <3 ; i++) {
                for (int j = 0; j <3 ; j++) {
                    if(board.getCells()[i][j].getState() == Cell.State.Empty){
                        //    int weight=board.getCells()[i][j].getWeight();
                        board.getCells()[i][j].setState(Cell.State.O);
                        scoreAlpha=minMax(grid,grid.getBigGrid()[i][j],depth-1,false,scoreAlpha,scoreBeta);
                        scoreAlpha=Math.max(alpha,scoreAlpha);
                        board.getCells()[i][j].setState(Cell.State.Empty);
                        if (scoreAlpha < alpha)
                            return alpha;
                    }
                }
            }
            return scoreAlpha;
        }
        else {
            //  int temp=Integer.MAX_VALUE;
            for (int i = 0; i <3 ; i++) {
                for (int j = 0; j <3 ; j++) {
                    if(board.getCells()[i][j].getState() == Cell.State.Empty){
                        //  int weight=board.getCells()[i][j].getWeight();
                        board.getCells()[i][j].setState(Cell.State.X);
                        scoreBeta=minMax(grid,grid.getBigGrid()[i][j],depth-1,true,scoreAlpha,scoreBeta);
                        scoreBeta=Math.min(beta,scoreBeta);
                        board.getCells()[i][j].setState(Cell.State.Empty);
                        if (scoreBeta > beta)
                            return beta;
                    }
                }
            }
            return scoreBeta;
        }
    }

    public Position computerMove(Grid grid,SmallGrid board,int depth){
        int temp=Integer.MIN_VALUE;
        int moveVal=temp;
        int i,j;
        ArrayList<Cell> possibleMove =new ArrayList<>();
        if (board.Win() != Cell.State.Empty){
            for (int a=0;a<3;a++)
                for (int b=0;b<3;b++)
                    if (grid.getBigGrid()[a][b].Win() == Cell.State.Empty)
                    {
                        board=grid.getBigGrid()[a][b];
                        possibleMove.addAll(board.getAllPossibleMoves());
                    }
        }
        else
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
                moveVal = minMax(grid, grid.getBigGrid()[i][j], depth, false,moveVal,Integer.MAX_VALUE);
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
        System.out.println("position " + position.row + " " + position.col);
        board.getCells()[position.row][position.col].setState(Cell.State.O);
        return position;

    }
}
