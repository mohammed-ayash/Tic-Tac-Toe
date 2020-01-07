
public class Cell {

    public enum State {
        X,
        O,
        Empty
    }

    private State state;
    private int weight;
    private int x;
    private int y;


    public Cell(int x, int y, int weight) {
        this.state = State.Empty;
        this.weight = weight;
        this.x=x;
        this.y=y;
    }

    public void setState(State state) {
        this.state = state;
    }


    public State getState() {
        return state;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWeight() {
        return weight;
    }

}
