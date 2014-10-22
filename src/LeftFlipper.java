import physics.*;

/**
 * A mutable class representing a left flipper.
 * 
 * Bounding box of size 2Lx2L
 * The flipper's pivot point is in the northwest corner of its bounding box, and its
 * initial rotation is counterclockwise for the default orientation (0 degrees).
 * Requires that orientation is 0, 90, 180, or 270.
 * Coefficient of reflection: 0.95
 * Rotates 90 degrees when triggered.
 */
public class LeftFlipper implements Gadget {
    private final int xCor, yCor, orientation; 
    private double time;
    private LineSegment flipper;
    private Circle endpoint;
    private final Circle pivot;
    private final Gadget[] toTrigger;
    private boolean flipping; 
    private boolean isVertical;
    
    private final double COEFFICIENT_OF_REFLECTION = 0.95;
    private final double ANGULAR_VELOCITY = 1080.0; //LeftFlippers always rotate counterclockwise
    
    //Rep invariant:
    //  xCor, yCor are in the range [0, 18] inclusive
    //  (18 because a horizontal flipper takes up two cells)
    
    //Abstraction function:
    //  represents a flipper that can rotate 90 degrees counterclockwise about its 
    //  own pivot point and redirects the ball when they come into contact
    
    /**
     * Make new LeftFlipper
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
    public LeftFlipper(int xCor, int yCor, int orientation, Gadget[] toTrigger) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.orientation = orientation;
        this.toTrigger = toTrigger;
        if (orientation == 0) {
            this.flipper = new LineSegment(xCor, yCor, xCor, yCor+1);
            this.pivot = new Circle(xCor, yCor, 0);
            this.endpoint = new Circle(xCor, yCor+1, 0);
            this.isVertical = true;
        } else if (orientation == 90) {
            this.flipper = new LineSegment(xCor, yCor, xCor+1, yCor);
            this.pivot = new Circle(xCor+1, yCor, 0);
            this.endpoint = new Circle(xCor, yCor, 0);
            this.isVertical = false;
        } else if (orientation == 180) {
            this.flipper = new LineSegment(xCor+1, yCor, xCor+1, yCor+1);
            this.pivot = new Circle(xCor+1, yCor+1, 0);
            this.endpoint = new Circle(xCor+1, yCor, 0);
            this.isVertical = true;
        } else if (orientation == 270) {
            this.flipper = new LineSegment(xCor, yCor+1, xCor+1, yCor+1);
            this.pivot = new Circle(xCor, yCor+1, 0);
            this.endpoint = new Circle(xCor+1, yCor+1, 0);
            this.isVertical = false;
        } else { throw new IllegalArgumentException("orientation must be 0, 90, 180, or 270"); }
        this.flipping = false;
        checkRep();
    }
    
    /**
     * Make new LeftFlipper with default orientation (0 degrees)
     * 
     * @param xCor
     *          x-coordinate of the upper-left corner of the bumper's bounding box
     *          must be in the range [0,19]
     * @param yCor
     *          y-coordinate of the upper-left corner of the bumper's bounding box
     *          must be in the range [0,19]
     */
    public LeftFlipper(int xCor, int yCor, Gadget[] toTrigger) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.orientation = 0;
        this.toTrigger = toTrigger;
        this.flipper = new LineSegment(xCor, yCor, xCor, yCor+1);
        this.pivot = new Circle(xCor, yCor, 0);
        this.endpoint = new Circle(xCor, yCor+1, 0);
        this.flipping = false;
        this.isVertical = true;
        checkRep();
    }
    
    private void checkRep() {
        assert(xCor >= 0 && xCor <= 18);
        assert(yCor >= 0 && yCor <= 18);
    }
    
    public Geometry.DoublePair getPosition() {
        return new Geometry.DoublePair(xCor, yCor);
    }
    
    public void setTime(double time) {
        this.time = time;
    }
    
    public boolean isActing() {
        return flipping;
    }
    
    public boolean isOccupying(int x, int y) {
        if (orientation == 0) {
            if (isVertical) {
                if (x == xCor && y >= yCor && y <= yCor+1) { return true; } 
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
                if (x >= xCor && x <= xCor+1 && y == yCor) { return true; } 
                else { return false; }
            }
        } else if (orientation == 180) {
            if (isVertical) {
                if (x == xCor+1 && y >= yCor && y <= yCor+1) { return true; } 
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
                if (x >= xCor && x <= xCor+1 && y == yCor+1) { return true; } 
                else { return false; }
            }
        } else { throw new RuntimeException("isOccupying error, did not return true or false"); }
    }
    
    public double getMinCollisionTime(Ball ball) {
        LineSegment[] lineSegments = new LineSegment[]{flipper};
        Circle[] circles = new Circle[]{pivot, endpoint};
        return Util.getMinCollisionTime(circles, lineSegments, ball);
    }
    
    public void Action() { 
        flipping = true;
        Angle angleRotated = new Angle(Math.toRadians(ANGULAR_VELOCITY * time));
        if (angleRotated.compareTo(Angle.DEG_90) >= 0) { 
            flipping = false;
            angleRotated = Angle.DEG_90;
            isVertical = !isVertical;
        }
        flipper = Geometry.rotateAround(flipper, pivot.getCenter(), angleRotated);
        endpoint = Geometry.rotateAround(endpoint, pivot.getCenter(), angleRotated);
        time = 0;
    }
    
    public void interactWithBall(Ball ball) {
        LineSegment[] lineSegments = new LineSegment[]{flipper};
        Circle[] circles = new Circle[]{pivot, endpoint};
        
        Vect newVel = new Vect(0,0);
        if (Util.getPartOfGadgetThatBallWillCollideWith(circles, lineSegments, ball) instanceof LineSegment) {
            LineSegment wall = (LineSegment)Util.getPartOfGadgetThatBallWillCollideWith(circles, lineSegments, ball);
            if (flipping) {
                newVel = Geometry.reflectRotatingWall(wall, pivot.getCenter(), ANGULAR_VELOCITY, ball.getCircle(), ball.getVelocity(), COEFFICIENT_OF_REFLECTION);
                ball.setVelocity(newVel);
            } else {
                newVel = Geometry.reflectWall(wall, ball.getVelocity(), COEFFICIENT_OF_REFLECTION);
                ball.setVelocity(newVel);
            }
        } else if (Util.getPartOfGadgetThatBallWillCollideWith(circles, lineSegments, ball) instanceof Circle) { 
            Circle corner = (Circle)Util.getPartOfGadgetThatBallWillCollideWith(circles, lineSegments, ball);
            if (flipping && corner.getCenter().x() == pivot.getCenter().x() && corner.getCenter().y() == pivot.getCenter().y()) {
                //corner is pivot, is not moving
                newVel = Geometry.reflectCircle(corner.getCenter(), ball.getCircle().getCenter(), ball.getVelocity(), COEFFICIENT_OF_REFLECTION);
                ball.setVelocity(newVel);
            } else if(flipping) { //corner is endpoint, is moving
                newVel = Geometry.reflectRotatingCircle(endpoint, pivot.getCenter(), ANGULAR_VELOCITY, ball.getCircle(), ball.getVelocity(), COEFFICIENT_OF_REFLECTION);
                ball.setVelocity(newVel);
            } else { //not flipping
                newVel = Geometry.reflectCircle(corner.getCenter(), ball.getCircle().getCenter(), ball.getVelocity(), COEFFICIENT_OF_REFLECTION);
                ball.setVelocity(newVel);    
            }
        }
    }
    
    public Gadget[] trigger() {
        return toTrigger;
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
