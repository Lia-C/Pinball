import physics.*;

/**
 * An immutable class representing a right flipper.
 * 
 * Bounding box of size 2Lx2L
 * The flipper's pivot point is in the northeast corner of its bounding box, and its
 * initial rotation is clockwise for the default orientation (0 degrees).
 * Requires that orientation is 0 or 90.
 * Coefficient of reflection: 0.95
 * Rotates 90 degrees when triggered.
 */
public class RightFlipper implements Gadget {
    private final int xCor, yCor, orientation; //orientation must be 0 or 90
    private final LineSegment flipper;
    private final Circle pivot, endpoint;
    private final boolean rotating;
    
    private final double COEFFICIENT_OF_REFLECTION = 0.95;
    private final double ANGULAR_VELOCITY = 1080.0;
    
    //Rep invariant:
    //  xCor, yCor are in the range [0, 18] inclusive
    //  (18 because a horizontal flipper takes up two cells)
    
    //Abstraction function:
    //  represents a flipper that can rotate 90 degrees counterclockwise about its 
    //  own pivot point and redirects the ball when they come into contact
    
    public RightFlipper(int xCor, int yCor) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.orientation = 0;
        this.flipper = new LineSegment(xCor+1, yCor, xCor+1, yCor+1);
        this.pivot = new Circle(xCor+1, yCor, 0);
        this.endpoint = new Circle(xCor+1, yCor+1, 0);
        this.rotating = false;
        checkRep();
    }
    
    public RightFlipper(int xCor, int yCor, int orientation) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.orientation = orientation;
        this.pivot = new Circle(xCor+1, yCor, 0);
        if (orientation == 0) {
            this.flipper = new LineSegment(xCor+1, yCor, xCor+1, yCor+1);
            this.endpoint = new Circle(xCor+1, yCor+1, 0);
        } else if (orientation == 90) {
            this.flipper = new LineSegment(xCor, yCor, xCor+1, yCor);
            this.endpoint = new Circle(xCor, yCor, 0);
        } else { throw new IllegalArgumentException("orientation must be 0 or 90"); }
        this.rotating = false;
        checkRep();
    }
    
    private void checkRep() {
        assert(xCor >= 0 && xCor <= 18);
        assert(yCor >= 0 && yCor <= 18);
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
    
    
    /*public boolean isOccupying(int x, int y) {
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
    }*/
    
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