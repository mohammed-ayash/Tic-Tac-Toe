import java.util.ArrayList;

public class controller {

    // Methods
    public static int getWeight(int i,int j) {
        if (i == 1 && j == 1)
            return 4;
        else if (i == 0 && j == 0)
            return 3;
        else if (i == 0 && j == 2)
            return 3;
        else if (i == 2 && j == 0)
            return 3;
        else if (i == 2 && j == 2)
            return 3;
        else
            return 2;

    }



    public boolean isMovesFinish(Cell board[][]){
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j].getState() == Cell.State.Empty)
                    return true;
        return false;
    }

    private int Evaluate(Cell board[][]) {
        int score = 0;
        // Rows Win
        for (int i = 0; i < 3; i++) {
            //Check that the row is equal
            if (board[i][0].getState() == board[i][1].getState() && board[i][1].getState()== board[i][ 2].getState()) {
                if (board[i][0].getState() == Cell.State.O)
                score += 100;
                else if (board[i][0].getState() == Cell.State.X)
                score -= 100;
            }
        }
        // Column Win
        for (int i = 0; i < 3; i++) {
            //Check that the Column is equal
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


        return score;
    }


    public int minMax(Grid grid,SmallGrid board,int depth,boolean isMax){
        int score= Evaluate(board.getCells());
        // If Opponent (Max) has won the game
        if(depth == 0)
            return score;
        // No Steps Available and No Winner
        if(!isMovesFinish(board.getCells()))
            return 0;
        //if the matrix is closed
        if (board.Win() != Cell.State.Empty) {
            if (isMax) {
                int temp = -10000;
                for (int a = 0; a < 3; a++)
                    for (int b = 0; b < 3; b++)
                        if (grid.getBigGrid()[a][b].Win() == Cell.State.Empty) {
                            temp = Math.max(temp, minMax(grid, grid.getBigGrid()[a][b], depth - 1, true));
                        }
                return temp;
            } else {
                int temp = +10000;
                for (int a = 0; a < 3; a++)
                    for (int b = 0; b < 3; b++)
                        if (grid.getBigGrid()[a][b].Win() == Cell.State.Empty) {
                            temp = Math.min(temp, minMax(grid, grid.getBigGrid()[a][b], depth - 1, false));
                        }
                return temp;
            }
        }
        // Opponent(Max) Moves
        if(isMax){
            int temp = -10000;
            for (int i = 0; i <3 ; i++) {
                for (int j = 0; j <3 ; j++) {
                    if(board.getCells()[i][j].getState() == Cell.State.Empty){
                        int weight=board.getCells()[i][j].getWeight();
                        board.getCells()[i][j].setState(Cell.State.O);
                        temp=Math.max(temp,(score*weight)+minMax(grid,grid.getBigGrid()[i][j],depth-1,false));
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
                        temp=Math.min(temp,(score*weight)+minMax(grid,grid.getBigGrid()[i][j],depth-1,true));
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
                int moveVal = minMax(grid, grid.getBigGrid()[i][j], depth, false);
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



    public Position computerMove2(Grid gridBig, Position positionSmall, int depth){
        Grid best = getBestPositionForAllMoveAvailable(gridBig, positionSmall, depth);
        return best.getPositions().get(0);
    }




    public Grid getBestPositionForAllMoveAvailable(Grid gridBig, Position positionSmall, int depth){
        Cell.State player = depth % 2 == 0 ? Cell.State.O : Cell.State.X;
        Grid bestGrid = null;
        for (int i = 0; i <  gridBig.getBigGrid()[positionSmall.row][positionSmall.col].getAllPossibleMoves().size(); i++) {
            Grid tempGridBig = gridBig.deepCopy();
            SmallGrid tempSmallGrid = tempGridBig.getBigGrid()[positionSmall.row][positionSmall.col];
            Cell cell = tempSmallGrid.getAllPossibleMoves().get(i);
            cell.setState(player);
            Position position = new Position(cell.getX(), cell.getY());
            tempGridBig.addPos(position);
            if (depth>0){
                depth--;
                return getBestPositionForAllMoveAvailable(tempGridBig, position, depth);
            }else {
                int tempScore = tempGridBig.calculateEvaluateInBigGridComputer();
                int enemyEvaluate =  tempGridBig.calculateEvaluateInBigGridPlayer(Cell.State.X);
                if (bestGrid == null || tempScore > enemyEvaluate){
                    bestGrid = tempGridBig;
                }
            }
        }
        return bestGrid;
    }






}
