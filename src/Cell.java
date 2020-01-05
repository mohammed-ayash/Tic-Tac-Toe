
public class Cell {

    private State state;
    private int weight;

    public Cell( int weight) {
        this.state = State.Empty;
        this.weight = weight;
    }

    public void setState(State state) {
        this.state = state;
    }
    public State getState() {
        return state;
    }
    public int getWeight() {
        return weight;
    }
}
