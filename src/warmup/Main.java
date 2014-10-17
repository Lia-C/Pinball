package warmup;
import physics.*;


public class Main {
    
    public static void main(String[] args) {

        Ball ball = new Ball(new Vect(2,3), new Geometry.DoublePair(5, 5));

        Board board= new Board(ball, 20, 20);
        board.printBoard();
        board.run();
       
    }
    
}