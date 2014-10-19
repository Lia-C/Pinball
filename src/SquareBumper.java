import physics.*;
/**
 * An immutable class representing a square bumper.
 */

public class SquareBumper implements Gadget{
    private final LineSegment top, bottom, left, right;
    private final int xCor, yCor;
    
    private final double COEFFICIENT_OF_REFLECTION = 1.0;
    
    /**
     * 
     * @param xCor
     * @param yCor
     */
    public SquareBumper(int xCor, int yCor){
        this.xCor = xCor;
        this.yCor = yCor;
        this.top = new LineSegment(xCor, yCor, xCor+1, yCor);
        this.bottom = new LineSegment(xCor, yCor+1, xCor+1, yCor+1);
        this.left = new LineSegment(xCor, yCor, xCor, yCor+1);
        this.right = new LineSegment(xCor+1, yCor, xCor+1, yCor+1);
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
    
    /**
     *
     * @return xCor of upper-left corner of the square bumper's bounding box
     */
    public int getXCor(){
        return xCor;
    }
    
    /**
     * 
     * @return yCor of upper-left corner of the square bumper's bounding box
     */
    public int getYCor(){
        return yCor;
    }
    
    public LineSegment getTop(){
        return top;
    }
    
    public LineSegment getBottom(){
        return bottom;
    }
    
    public LineSegment getLeft(){
        return left;
    }
    
    public LineSegment getRight(){
        return right;
    }
    
    public double getCoeffOfReflection(){
        return COEFFICIENT_OF_REFLECTION;
    }
    
    @Override
    public String toString(){
        return "#";
    }
    
}
