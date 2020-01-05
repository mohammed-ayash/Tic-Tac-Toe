

public class subGrid {
    private Cell  [][] subgrid;
    private int weight;
    private char winner;


    public subGrid(int weight) {
        subgrid = new Cell [3][3];
        subgrid [0][0]=new Cell (3);
        subgrid [0][1]=new Cell (2);
        subgrid [0][2]=new Cell (3);
        subgrid [1][0]=new Cell (2);
        subgrid [1][1]=new Cell (4);
        subgrid [1][2]=new Cell (2);
        subgrid [2][0]=new Cell (3);
        subgrid [2][1]=new Cell (2);
        subgrid [2][2]=new Cell (3);

        this.weight = weight;
    }

    public Cell[][] getSubgrid() {
        return subgrid;
    }
    public int getWeight() {
        return weight;
    }
    public char getWinner() {
        return winner;
    }


    public void setSubgrid(Cell[][] subgrid) {
        this.subgrid = subgrid;
    }
    public void setWinner(char winner) {
        this.winner = winner;
    }

}
