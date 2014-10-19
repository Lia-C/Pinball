import physics.*;

/**
 * An immutable class representing a triangle bumper.
 * 
 * Bounding box of size 1Lx1L
 * Default orientation (0 degrees) places corners in the northeast, 
 * northwest, and southwest. Requires that orientation is 0, 90, 180, or 270.
 * Coefficient of reflection: 1.0
 */

public class TriangleBumper implements Gadget {
    private final int xCor, yCor, orientation; //orientation must be 0, 90, 180, or 270
    
    private final double COEFFICIENT_OF_REFLECTION = 1.0;
    
    public TriangleBumper(int xCor, int yCor) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.orientation = 0;
    }
    
    public TriangleBumper(int xCor, int yCor, int orientation) {
        this.xCor = xCor;
        this.yCor = yCor;
        if (orientation == 0 || orientation == 90 || orientation == 180 || orientation == 270) {
            this.orientation = orientation;
        } else { throw new IllegalArgumentException("orientation must be 0, 90, 180, or 270"); }
    }
    
    /**
    * @return xCor of upper-left corner of the triangle bumper's bounding box
    */
    public int getXCor(){
        return xCor;
    }
    
    /**
    * @return yCor of upper-left corner of the triangle bumper's bounding box
    */
    public int getYCor(){
        return yCor;
    }
    
    //consider just using coeff or reflection within this class
    /*public double getCoeffOfReflection(){
        return COEFFICIENT_OF_REFLECTION;
    }*/
    
    //consider just using orientation within this class
    /*public int getOrientation() {
        return orientation;
    }*/ 
    
    public boolean isOccupying(int x, int y) {
        if (x >= xCor && x <= xCor+1 && y >= yCor && y <= yCor+1) { return true; } 
        else { return false; }
    }
    
    public void Action(Ball ball) {
        
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    @Override
    public String toString() {
        if (orientation == 0 || orientation == 180) {
            return "/";
        } else { return "\\"; }
    }
}
