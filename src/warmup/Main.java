package warmup;
import physics.*;

/**
 * TODO: put documentation for your class here
 * board
 *   -has 4 linesegments
 *   -has a ball
 *   
 *   -toString(): array of arrays 
 *   
 *   -run(ballstart,ballend)
 *      while True:
 *      
     *      if timeToPrint(currentTime):
     *          print new string
     *          
     *      lastTime = currentTime()
     *      currentTime = System.currentTimeinMillis()
     *      
     *      recalculate ball's new x and y
     *          translate(ball, timedelta) --> 
         *      if ball's new x-coord/y-coord out of board:
         *       Vect reflectWall(LineSegment line, Vect velocity)
         *      else:
         *       set ball's x and y
 *   
 *  
 * ball 
 * _pos (x,y) --> geometry.doublepair()
 *  
 *  Vect velocity = length of vector is speed, dir is velocity dir
 *  toString() --> *
 *  getPositionOnBoard ()
 *  
 *  setLocation() 
 *  getLocation() --> geometry.doubleapir
 *  
 * print 10-20 times per second
 * 
 */

public class Main {
    
    /**
     * TODO: describe your main function's command line arguments here
     */
    
    public static void main(String[] args) {
        Ball ball = new Ball(new Vect(1.2,7), new Geometry.DoublePair(5, 5));
        Board board= new Board(ball, 20, 20);
        board.printBoard();
        board.run();
       
    }
    
}