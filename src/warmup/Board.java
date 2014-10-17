package warmup;
import physics.*;

public class Board {
    
    private final Ball ball;
    private int priorX;
    private int priorY;
    private final LineSegment top, bottom, left, right;
    
    public Board(Ball ball, int width, int height){
        this.ball = ball;
        this.top = new LineSegment(0,0,width,0);
        this.bottom = new LineSegment(0,height,width,height);
        this.left = new LineSegment(0,0,0,height);
        this.right = new LineSegment(width,0,width,height);
    }

    
    public void run(){
        
        long lastTimePrintedMillis = System.currentTimeMillis();
        long lastTimeUpdatedMillis = System.currentTimeMillis();
        long deltaT = 0;
        
        while(true){
            
            //print the board if it's time to
            if (timeToPrint(lastTimePrintedMillis)){
                printBoard();
                lastTimePrintedMillis = System.currentTimeMillis();
            }
            
            
            deltaT = System.currentTimeMillis() - lastTimeUpdatedMillis;
            
            //TODO: calculate the ball's newX, newY, and newVelocity
            translate(deltaT); //this isn't right for the time-being, but we can come back to this -Lia 
            //TODO: check if the newx and newy are out-of-bounds
            //if out-of-bounds:
            //  newVelocity = Vect reflectWall(LineSegment line, Vect velocity)
            //else:
            //  ball.setPosition(newX, newY)
            //  ball.setVelocity(newVelocity)
            
            //after updating ball's location, re-update the lastTimeUpdated
            lastTimeUpdatedMillis = System.currentTimeMillis();
            
            
        
        }
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
    
    public void translate(long deltaT) {
        double deltaX = this.ball.getVelocity().x() * deltaT;
        double deltaY = this.ball.getVelocity().y() * deltaT;
        double newX;
        Geometry.DoublePair newLoc; 
    }
    
    
    private void updateBallOnMap(){
        Geometry.DoublePair loc =this.ball.getPosition();
        int xPos=(int) loc.d1;
        int yPos=(int) loc.d2;
        board[yPos][xPos]="*";
        board[this.priorY][this.priorX]=" ";
        this.priorX=xPos;
        this.priorY=yPos;
    }
    
    private void moveWithoutCollision(Geometry.DoublePair newLoc){
        this.ball.setPosition(newLoc);
    }
  


    
    private boolean timeToPrint(long lastTimePrintedMillis){
        double timeDeltaSecs = (System.currentTimeMillis() - lastTimePrintedMillis)/1000.0;
        return timeDeltaSecs > 1/20.; //prints every 20 times per second
    }
    

}
