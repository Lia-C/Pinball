import physics.*;

/**
 * An immutable class representing a flipper.
 * 
 * Bounding box of size 2Lx2L
 * For a left flipper, the pivot point is in the northwest corner, and its
 * initial rotation is counterclockwise for the default orientation (0 degrees).
 * For a right flipper, the pivot point is in the northeast corner, and its
 * initial rotation is clockwise for the default orientation (0 degrees).
 * Requires that orientation is 0 or 90.
 * Requires that type is "left" or "right".
 * Coefficient of reflection: 0.95
 * Rotates 90 degrees when hit by the ball.
 */
public class Flipper implements Gadget {
    private final int xCor, yCor, orientation; //orientation must be 0 or 90
    private final String type; //must be "left" or "right"
    
    private final double COEFFICIENT_OF_REFLECTION = 0.95;
    
    public Flipper(int xCor, int yCor, String type) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.type = type;
        this.orientation = 0;
    }
    
    public Flipper(int xCor, int yCor, String type, int orientation) {
        this.xCor = xCor;
        this.yCor = yCor;
        if (type.equalsIgnoreCase("left") || type.equalsIgnoreCase("right")) {
            this.type = type;
        } else { throw new IllegalArgumentException("type must be \"left\" or \"right\""); }
        if (orientation == 0 || orientation == 90) {
            this.orientation = orientation;
        } else { throw new IllegalArgumentException("orientation must be 0 or 90"); }
    }
    
    //xCor, yCor, COEFFICIENT_OF_REFLECTION, orientation, and type will only be 
    //used within this class, so these getter methods are not necessary.
//    /**
//    * @return xCor of upper-left corner of the flipper's bounding box
//    */
//    public int getXCor(){
//        return xCor;
//    }
//    
//    /**
//    * @return yCor of upper-left corner of the flipper's bounding box
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
//    public String getType() {
//        return type;
//    }
    
    
    public boolean isOccupying(int x, int y) {
        if (type.equalsIgnoreCase("left")) {
            if (orientation == 0) {
                if (x == xCor && y >= yCor && y <= yCor+1) { return true; } 
                else { return false; }
            } else if (orientation == 90) {
                if (x >= xCor && x <= xCor+1 && y == yCor) { return true; } 
                else { return false; }
            } 
        } else if (type.equalsIgnoreCase("right")) {
            if (orientation == 0) {
                if (x == xCor+1 && y >= yCor && y <= yCor+1) { return true; } 
                else { return false; }
            } else if (orientation == 90) {
                if (x >= xCor && x <= xCor+1 && y == yCor) { return true; } 
                else { return false; }
            } 
        }
        throw new RuntimeException("isOccupying error, did not return true or false");
    }
    
    /**
     * Mutates the ball's velocity when the ball hits the bumper and 
     * rotates itself 90 degrees around its pivot point.
     * 
     * @param ball
     *          the ball which hit the bumper
     */
    public void Action(Ball ball) {
        
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    @Override
    public String toString() {
        if (orientation == 0) { return "|"; }
        else { return "-"; }
    }
    
}
