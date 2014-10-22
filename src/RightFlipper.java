import physics.*;

/**
 * A mutable class representing a right flipper.
 * 
 * Bounding box of size 2Lx2L
 * The flipper's pivot point is in the northeast corner of its bounding box, and its
 * initial rotation is clockwise for the default orientation (0 degrees).
 * Requires that orientation is 0, 90, 180, or 270.
 * Coefficient of reflection: 0.95
 * Rotates 90 degrees when triggered.
 */
public class RightFlipper implements Gadget {
    private final int xCor, yCor, orientation; //orientation must be 0 or 90
    private final LineSegment flipper;
    private final Circle pivot, endpoint;
    private final boolean flipping;
    private final boolean isVertical;
    
    private final double COEFFICIENT_OF_REFLECTION = 0.95;
    private final double ANGULAR_VELOCITY = 1080.0;
    
    //Rep invariant:
    //  xCor, yCor are in the range [0, 18] inclusive
    //  (18 because a horizontal flipper takes up two cells)
    
    //Abstraction function:
    //  represents a flipper that can rotate 90 degrees counterclockwise about its 
    //  own pivot point and redirects the ball when they come into contact
    
    /**
     * Make new RightFlipper
     * 
     * @param xCor
     *          x-coordinate of the upper-left corner of the bumper's bounding box
     *          must be in the range [0,19]
     * @param yCor
     *          y-coordinate of the upper-left corner of the bumper's bounding box
     *          must be in the range [0,19]
     * @param orientation
     *          rotation in degrees from the default orientation
     *          must be 0, 90, 180, or 270
     */
    public RightFlipper(int xCor, int yCor, int orientation) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.orientation = orientation;
        if (orientation == 0) {
            this.flipper = new LineSegment(xCor+1, yCor, xCor+1, yCor+1);
            this.pivot = new Circle(xCor+1, yCor, 0);
            this.endpoint = new Circle(xCor+1, yCor+1, 0);
            this.isVertical = true;
        } else if (orientation == 90) {
            this.flipper = new LineSegment(xCor+1, yCor+1, xCor, yCor+1);
            this.pivot = new Circle(xCor+1, yCor+1, 0);
            this.endpoint = new Circle(xCor, yCor+1, 0);
            this.isVertical = false;
        } else if (orientation == 180) {
            this.flipper = new LineSegment(xCor, yCor, xCor, yCor+1);
            this.pivot = new Circle(xCor, yCor+1, 0);
            this.endpoint = new Circle(xCor, yCor, 0);
            this.isVertical = true;
        } else if (orientation == 270) {
            this.flipper = new LineSegment(xCor, yCor, xCor+1, yCor);
            this.pivot = new Circle(xCor, yCor, 0);
            this.endpoint = new Circle(xCor+1, yCor, 0);
            this.isVertical = false;
        } else { throw new IllegalArgumentException("orientation must be 0, 90, 180, or 270"); }
        this.flipping = false;
        checkRep();
    }
    
    /**
     * Make new RightFlipper with default orientation (0 degrees) 
     * 
     * @param xCor
     *          x-coordinate of the upper-left corner of the bumper's bounding box
     *          must be in the range [0,19]
     * @param yCor
     *          y-coordinate of the upper-left corner of the bumper's bounding box
     *          must be in the range [0,19]
     * @param orientation
     *          rotation in degrees from the default orientation
     *          must be 0, 90, 180, or 270
     */
    public RightFlipper(int xCor, int yCor) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.orientation = 0;
        this.flipper = new LineSegment(xCor+1, yCor, xCor+1, yCor+1);
        this.pivot = new Circle(xCor+1, yCor, 0);
        this.endpoint = new Circle(xCor+1, yCor+1, 0);
        this.flipping = false;
        this.isVertical = true;
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
    
    public Geometry.DoublePair getPosition() {
        return new Geometry.DoublePair(xCor, yCor);
    }
    
    public boolean isOccupying(int x, int y) {
        if (orientation == 0) {
            if (isVertical) {
                if (x == xCor+1 && y >= yCor && y <= yCor+1) { return true; } 
                else { return false; }
            } else {
                if (x >= xCor && x <= xCor+1 && y == yCor) { return true; } 
                else { return false; }
            }
        } else if (orientation == 90) {
            if (isVertical) {
                if (x == xCor+1 && y >= yCor && y <= yCor+1) { return true; } 
                else { return false; }
            } else {
                if (x >= xCor && x <= xCor+1 && y == yCor+1) { return true; } 
                else { return false; }
            }
        } else if (orientation == 180) {
            if (isVertical) {
                if (x == xCor && y >= yCor && y <= yCor+1) { return true; } 
                else { return false; }
            } else {
                if (x >= xCor && x <= xCor+1 && y == yCor+1) { return true; } 
                else { return false; }
            }
        } else if (orientation == 270) {
            if (isVertical) {
                if (x == xCor && y >= yCor && y <= yCor+1) { return true; } 
                else { return false; }
            } else {
                if (x >= xCor && x <= xCor+1 && y == yCor) { return true; } 
                else { return false; }
            }
        } else { throw new RuntimeException("isOccupying error, did not return true or false"); }
    }
    
    public double getMinCollisionTime(Ball ball) {
        LineSegment[] lineSegments = new LineSegment[]{flipper};
        Circle[] circles = new Circle[]{pivot, endpoint};
        return Util.getMinCollisionTime(circles, lineSegments, ball);
    }
    
    /**
     * Mutates the ball's velocity when the ball hits the bumper and 
     * rotates itself 90 degrees around its pivot point.
     * 
     * @param ball
     *          the ball which hit the bumper
     */
    public void Action(Ball ball) {//TODO
        
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    @Override
    public String toString() {
        if (isVertical) { return "|"; }
        else { return "-"; }
    }
    
}