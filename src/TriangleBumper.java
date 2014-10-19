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
    
    //Rep invariant:
    //  xCor, yCor are in the range [1, 20] inclusive
    
    //Abstraction function:
    //  represents a triangle-shaped bumper that redirects
    //  the ball if they come into contact
    
    public TriangleBumper(int xCor, int yCor) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.orientation = 0;
        checkRep();
    }
    
    public TriangleBumper(int xCor, int yCor, int orientation) {
        this.xCor = xCor;
        this.yCor = yCor;
        if (orientation == 0 || orientation == 90 || orientation == 180 || orientation == 270) {
            this.orientation = orientation;
        } else { throw new IllegalArgumentException("orientation must be 0, 90, 180, or 270"); }
        checkRep();
    }
    
    private void checkRep() {
        assert(xCor >= 1 && xCor <= 20);
        assert(yCor >= 1 && yCor <= 20);
    }
    
    //xCor, yCor, COEFFICIENT_OF_REFLECTION, and orientation will only be used
    //within this class, so these getter methods are not necessary.
//    /**
//    * @return xCor of upper-left corner of the triangle bumper's bounding box
//    */
//    public int getXCor(){
//        return xCor;
//    }
//    
//    /**
//    * @return yCor of upper-left corner of the triangle bumper's bounding box
//    */
//    public int getYCor(){
//        return yCor;
//    }
//    
//    public double getCoeffOfReflection(){
//        return COEFFICIENT_OF_REFLECTION;
//    }
//    
//    public int getOrientation() {
//        return orientation;
//    }
//    
    
    public boolean isOccupying(int x, int y) {
        if (x >= xCor && x <= xCor+1 && y >= yCor && y <= yCor+1) { return true; } 
        else { return false; }
    }
    
    /**
     * Mutates the ball's velocity when the ball hits the bumper.
     * 
     * @param ball
     *          the ball which hit the bumper
     */
    public void Action(Ball ball) {
        checkRep();
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
