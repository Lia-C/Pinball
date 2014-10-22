import physics.*;

import java.util.ArrayList;

/**
 * An immutable class representing an absorber that stores and launches balls.
 * 
 * Bounding box of size kLxmL where k,m are positive integers <= 20 Trigger:
 * generated whenever the ball hits it Action: launches a stored ball
 */
public class Absorber implements Gadget {
    private final int xCor, yCor, height, width;
    private final LineSegment top, left, bottom, right;
    private final Circle topLeft, topRight, bottomLeft, bottomRight;
    private ArrayList<Ball> storedBalls;
    private final Geometry.DoublePair storageLoc;
    private final Gadget[] toTrigger;
    private double time;
    private boolean isActing;

    private final Vect LAUNCH_VELOCITY = new Vect(0, -50.0);

    /*
     * Rep Invariant: Must be contained within the board. i.e. xCor, yCor are in
     * range [0,19] and xCor+width, yCor+height are in range [0,20] Abstraction
     * Function: Represents a Gadget that stops balls and shoots them out.
     */

    /**
     * Absorbers move any ball that contact them to their bottom right corner
     * and then shoot them out when triggered again.
     * 
     * @param height
     *            must be in range [0,20]
     * @param width
     *            must be in range [0,20]
     * @param xCor
     *            must be in range [0,19]
     * @param yCor
     *            must be in range [0,19]
     */
    public Absorber(int xCor, int yCor, int width, int height,
            Gadget[] toTrigger) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.height = height;
        this.width = width;
        this.toTrigger = toTrigger;
        this.top = new LineSegment(xCor, yCor, xCor + width, yCor);
        this.left = new LineSegment(xCor, yCor, xCor, yCor + height);
        this.bottom = new LineSegment(xCor, yCor + height, xCor + width, yCor
                + height);
        this.right = new LineSegment(xCor + width, yCor, xCor + width, yCor
                + height);
        this.topLeft = new Circle(xCor, yCor, 0);
        this.topRight = new Circle(xCor + width, yCor, 0);
        this.bottomLeft = new Circle(xCor, yCor + height, 0);
        this.bottomRight = new Circle(xCor + width, yCor + height, 0);
        this.storageLoc = new Geometry.DoublePair(xCor + width - .25, yCor
                + height - .25);
        this.isActing = false;
        this.storedBalls = new ArrayList<Ball>();
        checkRep();
    }

    private void checkRep() {
        assert height > 0 && height <= 20;
        assert width > 0 && width <= 20;
        assert xCor >= 0 && xCor <= 19;
        assert yCor > 0 && yCor <= 19;
        assert xCor + width <= 20 && yCor + height <= 20;
    }

    public Geometry.DoublePair getPosition() {
        return new Geometry.DoublePair(xCor, yCor);
    }

    public boolean isOccupying(int x, int y) {

        if (x >= xCor && x <= xCor + width && y >= yCor && y <= yCor + height) {
            return true;
        }
        return false;
    }

    public double getMinCollisionTime(Ball ball) {
        double ballX = ball.getPosition().d1;
        double ballY = ball.getPosition().d2;
       if (ballX >= xCor && ballX <= xCor + width && ballY >= yCor && ballY <= yCor + height) {
            return Double.POSITIVE_INFINITY;
        }
        LineSegment[] lineSegments = new LineSegment[] { top, left, bottom,
                right };
        Circle[] circles = new Circle[] { topLeft, topRight, bottomLeft,
                bottomRight };
        return Util.getMinCollisionTime(circles, lineSegments, ball);
    }

    public String toString() {
        return "=";
    }

    public void Action() {
        if(!isActing && !storedBalls.isEmpty()) {
            storedBalls.get(0).setVelocity(LAUNCH_VELOCITY);
            storedBalls.get(0).release();
            this.storedBalls.remove(0);
            double distToRelease = storageLoc.d2 - yCor;
            double v = LAUNCH_VELOCITY.length();
            double timeToRelease = distToRelease / v;
            if (timeToRelease > time) {
                isActing = false;
            }
        }
        time = 0;
    }

    public Gadget[] trigger() {
        return toTrigger;
    }

    public void interactWithBall(Ball ball) {
        ball.setVelocity(new Vect(0, 0));
        ball.setPosition(storageLoc);
        storedBalls.add(ball);
        ball.hold();
    }

    public void setTime(double time) {
        this.time = time;
    }

    public boolean isActing() {
        return isActing;
    }

}
