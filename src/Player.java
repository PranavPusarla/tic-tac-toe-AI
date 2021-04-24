import java.util.*;

public class Player {
    String icon = "";
    ArrayList<Position> history = new ArrayList();
    public Player(String xo) {
        icon = xo;
    }
    public void addMoves(Position move) {
        history.add(move);
    }
    public void showMoves() {
        for (int i = 0; i< history.size();i ++) {
            Position move = history.get(i);
            System.out.println(i+". Marked row "+move.row + " and column "+move.col);
        }
    }
}
