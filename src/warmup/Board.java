package warmup;
import physics.*;

public class Board {
    
    private final Ball ball;
    private final LineSegment top, bottom, left, right;
    
    public Board(Ball ball, int width, int height){
        this.ball = ball;
        this.top = new LineSegment(0,0,width,0);
        this.bottom = new LineSegment(0,height,width,height);
        this.left = new LineSegment(0,0,0,height);
        this.right = new LineSegment(width,0,width,height);
    }

    
    public void run(){
        //ball.
    }
    
    private final String[][] board= new String[][]{{".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{"."," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","."},{".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".","."}};
    
    private void printBoard(){
        for (int i=0; i<board.length;i++){
            for (int j=0; j<board[i].length;j++){
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
    }
    
    public void translate(double deltaT) {
        double deltaX = this.ball.getVelocity().x() * deltaT;
        double deltaY = this.ball.getVelocity().y() * deltaT;
        double newX;
        Geometry.DoublePair newLoc; 
    }
    private void moveWithoutCollision(Geometry.DoublePair newLoc){
        this.ball.setPosition(newLoc);
    }
  
}
