import java.util.Scanner;
//TODO check all point depth.
public class Main {
    static Scanner scanner=new Scanner(System.in);
    static controller controller=new controller();
    static int depth=5;
    public static void main(String[] args) {
        int x,y;
        System.out.println("\n  ******** Ultimate Tic-Tac-Toe Game ******** \n");
        Grid grid=new Grid();
        System.out.print(" Enter Your Location in Big Grid :");
        x = scanner.nextInt();
        y = scanner.nextInt();
        if((x>2 || y>2) || (x<0 || y <0)){
            System.out.print(" You went out of bounds matrix ReEnter Your Location in Big Grid :");
            x = scanner.nextInt();
            y = scanner.nextInt();
        }
        startGame(grid,x,y);

    }

    public static void startGame(Grid grid,int x,int y){
        int row, col;
        while (grid.Win() == Cell.State.Empty){
                System.out.print(" Enter Your Location (" +x+","+y+") in Small Grid :");
                row = scanner.nextInt();
                col = scanner.nextInt();
                // To check is any cell are token or not
                while (grid.check(row,col,x,y)){
                    System.out.println(" This Location is already token Or went out of bounds matrix or the matrix is win");
                    grid.print();
                    System.out.print(" ReEnter your location (" +x+","+y+") :");
                    row=scanner.nextInt();
                    col=scanner.nextInt();
                }
                grid.bigGrid[x][y].getCells()[row][col].setState(Cell.State.X);
                grid.print();
                // if for check for win
                // else if for check for game finish(ismovesfinish)
                Position position=controller.computerMove(grid,grid.bigGrid[row][col],depth);
                x=position.row;
                y=position.col;
                grid.print();
            }
        }

    }


