import physics.*;

/**
 * An immutable class representing a circle bumper.
 */
public class CircleBumper implements Gadget{
    private final int xCor, yCor;
    private final Circle circle;
    
    private final double RADIUS = 0.5;
    private final double COEFFICIENT_OF_REFLECTION = 1.0;

    /**
     * Make new circle bumper
     * @param xCor
     *          x-coordinate of the desired upper-left of the bumper's bounding box
     * @param yCor
     *          y-coordinate of the desired upper-left of the bumper's bounding box
     */
    public CircleBumper(int xCor, int yCor){
        this.xCor = xCor;
        this.yCor = yCor;
        this.circle = new Circle(xCor+0.5, yCor+0.5, RADIUS);
    }
    
    
    public boolean isOccupying(int x, int y){
        return x == xCor && y == yCor;
    }
    
    public boolean isEmpty(){
        return false;
    }
    
    /**
     * Mutates the ball's velocity when the ball hits the bumper.
     * 
     * @param ball
     *          the ball which hit the bumper
     */
    public void Action(Ball ball){
       //TODO 
        
    }
    
   
//    /**
//    * Get the x-coordinate of upper-left corner of the circle bumper's bounding box
//    * @return xCor of upper-left corner of the circle bumper's bounding box
//    */
//    public int getXCor(){
//        return xCor;
//    }
//    
//    /**
//    * Get the y-coordinate of upper-left corner of the circle bumper's bounding box
//    * @return yCor of upper-left corner of the circle bumper's bounding box
//    */
//    public int getYCor(){
//        return yCor;
//    }
   
    
    
    @Override
    public String toString(){
        return "O";
    }
    
    
}
