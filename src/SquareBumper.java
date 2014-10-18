import physics.*;
/**
 * An immutable class representing a square bumper.
 */

public class SquareBumper implements Gadget{
    private final LineSegment top, bottom, left, right;
    private final int xCor, yCor;
    
    private final int SIDE_LENGTH = 1;
    private final double COEFFICIENT_OF_REFLECTION = 1.0;
    
    
    public SquareBumper(int xCor, int yCor){
        this.xCor = xCor;
        this.yCor = yCor;
        this.top = new LineSegment(xCor, yCor, xCor+1, yCor);
        this.bottom = new LineSegment(xCor, yCor+1, xCor+1, yCor+1);
        this.left = new LineSegment(xCor, yCor, xCor, yCor+1);
        this.right = new LineSegment(xCor+1, yCor, xCor+1, yCor+1);
    }
    
    public int getXCor(){
        return xCor;
    }
    
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
