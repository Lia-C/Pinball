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
    private final LineSegment leftLeg, rightLeg, hypotenuse; //leftLeg goes from baseCorner to leftCorner
                                                             //rightLeg goes from baseCorner to rightCorner
    private final Circle baseCorner, leftCorner, rightCorner; //baseCorner is the corner opposite the hypotenuse.
                                                              //leftCorner, rightCorner are to the left and right if you
                                                              //are sitting at the baseCorner and facing the hypotenuse.
    private final Gadget[] toTrigger;
    
    private final double COEFFICIENT_OF_REFLECTION = 1.0;
    
    //Rep invariant:
    //  xCor, yCor are in the range [0, 19] inclusive
    
    //Abstraction function:
    //  represents a triangle-shaped bumper that redirects
    //  the ball if they come into contact
    
    /**
     * Make new TriangleBumper
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
    public TriangleBumper(int xCor, int yCor, int orientation, Gadget[] toTrigger) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.orientation = orientation;
        this.toTrigger = toTrigger;
        if (orientation == 0) {
            this.baseCorner = new Circle(xCor, yCor, 0);
            this.leftCorner = new Circle(xCor+1, yCor, 0);
            this.rightCorner = new Circle(xCor, yCor+1, 0);
            this.leftLeg = new LineSegment(xCor, yCor, xCor+1, yCor);
            this.rightLeg = new LineSegment(xCor, yCor, xCor, yCor+1);
            this.hypotenuse = new LineSegment(xCor+1, yCor, xCor, yCor+1);
        } else if (orientation == 90) {
            this.baseCorner = new Circle(xCor+1, yCor, 0);
            this.leftCorner = new Circle(xCor+1, yCor+1, 0);
            this.rightCorner = new Circle(xCor, yCor, 0);
            this.leftLeg = new LineSegment(xCor+1, yCor, xCor+1, yCor+1);
            this.rightLeg = new LineSegment(xCor+1, yCor, xCor, yCor);
            this.hypotenuse = new LineSegment(xCor, yCor, xCor+1, yCor+1);
        } else if (orientation == 180) {
            this.baseCorner = new Circle(xCor+1, yCor+1, 0);
            this.leftCorner = new Circle(xCor, yCor+1, 0);
            this.rightCorner = new Circle(xCor+1, yCor, 0);
            this.leftLeg = new LineSegment(xCor+1, yCor+1, xCor, yCor+1);
            this.rightLeg = new LineSegment(xCor+1, yCor+1, xCor+1, yCor);
            this.hypotenuse = new LineSegment(xCor, yCor+1, xCor+1, yCor);
        } else if (orientation == 270) { 
            this.baseCorner = new Circle(xCor, yCor+1, 0);
            this.leftCorner = new Circle(xCor, yCor, 0);
            this.rightCorner = new Circle(xCor+1, yCor+1, 0);
            this.leftLeg = new LineSegment(xCor, yCor+1, xCor, yCor);
            this.rightLeg = new LineSegment(xCor, yCor+1, xCor+1, yCor+1);
            this.hypotenuse = new LineSegment(xCor, yCor, xCor+1, yCor+1);
        } else { throw new IllegalArgumentException("orientation must be 0, 90, 180, or 270"); }
        checkRep();
    }
    
    /**
     * Make new TriangleBumper with default orientation (0 degrees)
     * 
     * @param xCor
     *          x-coordinate of the upper-left corner of the bumper's bounding box
     *          must be in the range [0,19]
     * @param yCor
     *          y-coordinate of the upper-left corner of the bumper's bounding box
     *          must be in the range [0,19]
     */
    public TriangleBumper(int xCor, int yCor, Gadget[] toTrigger) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.orientation = 0;
        this.toTrigger = toTrigger;
        this.baseCorner = new Circle(xCor, yCor, 0);
        this.leftCorner = new Circle(xCor+1, yCor, 0);
        this.rightCorner = new Circle(xCor, yCor+1, 0);
        this.leftLeg = new LineSegment(xCor, yCor, xCor+1, yCor);
        this.rightLeg = new LineSegment(xCor, yCor, xCor, yCor+1);
        this.hypotenuse = new LineSegment(xCor+1, yCor, xCor, yCor+1);
        checkRep();
    }
    
    private void checkRep() {
        assert(xCor >= 0 && xCor <= 19);
        assert(yCor >= 0 && yCor <= 19);
    }
    
    public Geometry.DoublePair getPosition() {
        return new Geometry.DoublePair(xCor, yCor);
    }
    
    public boolean isOccupying(int x, int y) {
        if (x == xCor && y == yCor) { return true; } 
        else { return false; }
    }

    public void Action() {
        //TriangleBumper has no action
    }
    
    public boolean isActing() {
        return false; //TriangleBumper has no action
    }
    
    public void setTime(double time) {
        //TriangleBumper is not time dependent
    }
    
    public void interactWithBall(Ball ball) {
        LineSegment[] lineSegments = new LineSegment[]{leftLeg, rightLeg, hypotenuse};
        Circle[] circles = new Circle[]{baseCorner, leftCorner, rightCorner};
        Vect newVel=new Vect(0,0);
        if (Util.getPartOfGadgetThatBallWillCollideWith(circles, lineSegments, ball) instanceof LineSegment) {
            LineSegment wall = (LineSegment)Util.getPartOfGadgetThatBallWillCollideWith(circles, lineSegments, ball);
            newVel=Geometry.reflectWall(wall, ball.getVelocity(), COEFFICIENT_OF_REFLECTION);
        } else if (Util.getPartOfGadgetThatBallWillCollideWith(circles, lineSegments, ball) instanceof Circle) { 
            Circle corner = (Circle)Util.getPartOfGadgetThatBallWillCollideWith(circles, lineSegments, ball);
            newVel=Geometry.reflectCircle(corner.getCenter(), ball.getCircle().getCenter(), ball.getVelocity(), COEFFICIENT_OF_REFLECTION);
        }
        ball.setVelocity(newVel);
    }
    
    public Gadget[] trigger() {
        return toTrigger;
    }
    
    public double getMinCollisionTime(Ball ball) {
        LineSegment[] lineSegments = new LineSegment[]{leftLeg, rightLeg, hypotenuse};
        Circle[] circles = new Circle[]{baseCorner, leftCorner, rightCorner};
        return Util.getMinCollisionTime(circles, lineSegments, ball);
    }
    
    @Override
    public String toString() {
        if (orientation == 0 || orientation == 180) {
            return "/";
        } else { return "\\"; }
    }
}
