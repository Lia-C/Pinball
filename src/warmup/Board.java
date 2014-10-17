package warmup;
import physics.*;

public class Board {
    
    private final Ball ball;
    private final LineSegment top, bottom, left, right;
    private String[][] board;
    private int width;
    private int height;
    
    
    public Board(Ball ball, int width, int height){
        this.ball = ball;
        this.top = new LineSegment(0,0,width,0);
        this.bottom = new LineSegment(0,height,width,height);
        this.left = new LineSegment(0,0,0,height);
        this.right = new LineSegment(width,0,width,height);
        this.board = makeBoard(width,height);
        this.width = width;
        this.height = height;
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
            //^All of this is to be handled in translate
            translate(deltaT);
            //after updating ball's location, re-update the lastTimeUpdated
            lastTimeUpdatedMillis = System.currentTimeMillis();
            
        
        }
    }
    
    private String[][] makeBoard(int width,int height){
        String[][] board =new String [width][height];
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                if (i==0||i==(height-1)||j==0||j==(width-1)){
                    board[i][j]=".";
                }
                else{
                    board[i][j]=" ";
                }
            }
        }
        return board;
    }

    
    public void translate(long deltaT) {
        double deltaX = this.ball.getVelocity().x() * deltaT/1000;
        double deltaY = this.ball.getVelocity().y() * deltaT/1000;
        double newX = this.ball.getPosition().d1 + deltaX;
        double newY = this.ball.getPosition().d2 + deltaY;
        double xOver = Math.abs(newX - width);
        double yOver = Math.abs(newY - height);
        Geometry.DoublePair newLoc = new Geometry.DoublePair(newX, newY); 
        LineSegment collisionWall = new LineSegment(0,0,0,0);
        
        if (newX >= width && newY <= height && newY >= 0){
            collisionWall = right;
        } else if (newX <= 0 && newY <= height && newY >= 0){
            collisionWall = left;
        } else if (newY >= height && newX <= width && newX >= 0){
            collisionWall = bottom;
        } else if (newY <= 0 && newX <= width && newX >= 0){
            collisionWall = top;
        } else if (newX >= width && newY >= height){
            if (xOver > yOver){ collisionWall = right; }
            else { collisionWall = bottom; }
        } else if (newX >= width && newY <= 0){
            if (xOver > yOver){ collisionWall = right; }
            else { collisionWall = top; }
        } else if (newX <= 0 && newY >= height){ 
            if (xOver > yOver){ collisionWall = left; }
            else { collisionWall = bottom; }
        } else if (newX <= 0 && newY <= 0){
            if (xOver > yOver){ collisionWall = left; }
            else { collisionWall = top; }
        }
        
        if (newX > 0 && newX < width && newY > 0 && newY < width){
            moveWithoutCollision(newLoc);
        } else {
            moveWithCollision(newLoc, collisionWall, deltaT);
        }
    }
    

        
        //TODO: check if the newx and newy are out-of-bounds
        //if out-of-bounds:
        //  moveWithCollision
        //  newVelocity = Vect reflectWall(LineSegment line, Vect velocity)
        //else:
        //  moveWithoutCollision
        //  ball.setPosition(newX, newY)
        //  ball.setVelocity(newVelocity)
    
    
    private void moveWithoutCollision(Geometry.DoublePair newLoc){
        this.ball.setPosition(newLoc);
        int x=(int)this.ball.getPosition().d1;
        int y=(int)this.ball.getPosition().d2;
        if (x<=0){
            x=1;
            
            this.ball.setVelocity(new Vect(-this.ball.getVelocity().x(),this.ball.getVelocity().y()));
        }
        if (x>=this.board[0].length-1){
            x=this.board[0].length-2;
            this.ball.setVelocity(new Vect(-this.ball.getVelocity().x(),this.ball.getVelocity().y()));
        }
        if (y<=0){
            y=1;
            this.ball.setVelocity(new Vect(this.ball.getVelocity().x(),-this.ball.getVelocity().y()));
        }
        if (y>=this.board.length-1){
            y=this.board.length-2;
            this.ball.setVelocity(new Vect(this.ball.getVelocity().x(),-this.ball.getVelocity().y()));
        }
        this.ball.setPosition( new Geometry.DoublePair(x, y));
    }
  
    private void moveWithCollision(Geometry.DoublePair newLoc, LineSegment wall, long deltaT){
        double n = Geometry.timeUntilWallCollision(wall, this.ball.getCircle(), this.ball.getVelocity());
        if (n != Double.POSITIVE_INFINITY){
            long timeUntilCollision = (long) n;
            this.translate(timeUntilCollision);
            this.ball.setVelocity(Geometry.reflectWall(wall, this.ball.getVelocity()));
            this.translate(deltaT - timeUntilCollision);
        }
    }
    
    private boolean timeToPrint(long lastTimePrintedMillis){
        double timeDeltaSecs = (System.currentTimeMillis() - lastTimePrintedMillis)/1000.0;
        return timeDeltaSecs > 1/20.; //prints every 20 times per second
    }
    
    public void printBoard(){
        int x=(int)this.ball.getPosition().d1;
        int y=(int)this.ball.getPosition().d2;
        for (int i=0; i<board.length;i++){
            for (int j=0; j<board[i].length;j++){
                if(i==y&&x==j){
                    System.out.print("*");
                }
                else{
                    System.out.print(board[i][j]);
                }
                
            }
            System.out.print("\n");
        }
    }
    
    
  
}
