import physics.*;


public class CircleBumper implements Gadget{
    private final int xCor, yCor;
    private final Circle circle;
    
    private final double RADIUS = 0.5;
    private final double COEFFICIENT_OF_REFLECTION = 1.0;
    
    /**
     * An immutable class representing a circle bumper.
     * 
     * Abstraction function: represents a circle bumper with a specific location on the board
     * Rep invariant: xCor and yCor are in [1,20]
     * 
     * Size and shape: a circular shape with diameter 1L
     * Coefficient of reflection: 1.0
     * Orientation: not applicable (symmetric to 90 degree rotations)
     * Trigger: generated whenever the ball hits it
     * Action: none
     */
    
    
    /**
     * Make new circle bumper
     * @param xCor
     *          x-coordinate of the desired upper-left of the bumper's bounding box
     *          has to be in the range [1,20] (needs to be in the playing area)
     * @param yCor
     *          y-coordinate of the desired upper-left of the bumper's bounding box
     *          has to be in the range [1,20] (needs to be in the playing area)
     */
    public CircleBumper(int xCor, int yCor){
        this.xCor = xCor;
        this.yCor = yCor;
        this.circle = new Circle(xCor+0.5, yCor+0.5, RADIUS);
        checkRep();
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
   
    private void checkRep(){
        assert xCor >= 1 && xCor <= 20 && yCor >= 1 && yCor <= 20;
    }
    
    
    @Override
    public String toString(){
        return "O";
    }
    
    
    
    
}
