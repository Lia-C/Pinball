import physics.*;
import physics.Geometry.DoublePair;

public class CircleBumper implements Gadget {
    private final int xCor, yCor;
    private final Circle circle;
    private final Gadget[] gadgetsThisTriggers;
    // Below variables are as defined in the specs.
    private static final double RADIUS = 0.5;
    private static final double COEFFICIENT_OF_REFLECTION = 1.0;

    /**
     * An immutable class representing a circle bumper.
     * 
     * Abstraction function: represents a circle bumper with a specific location
     * on the board Rep invariant: xCor and yCor are in [0,19]
     * 
     * Size and shape: a circular shape with diameter 1L Coefficient of
     * reflection: 1.0 Orientation: not applicable (symmetric to 90 degree
     * rotations) Trigger: generated whenever the ball hits it Action: none
     */

    /**
     * Make new circle bumper
     * 
     * @param xCor
     *            x-coordinate of the desired upper-left of the bumper's
     *            bounding box has to be in the range [0,19] (needs to be in the
     *            playing area)
     * @param yCor
     *            y-coordinate of the desired upper-left of the bumper's
     *            bounding box has to be in the range [0,19] (needs to be in the
     *            playing area)
     */
    public CircleBumper(int xCor, int yCor, Gadget[] gadgetsThisTriggers) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.circle = new Circle(xCor + RADIUS, yCor + RADIUS, RADIUS);
        this.gadgetsThisTriggers = gadgetsThisTriggers;
        checkRep();
    }

    /**
     * Meant to be used to determine which, if any, Gadget that a ball would
     * hit, and when.
     * 
     * @param ball
     *            One of the balls moving around the map
     * @return The least time it would take for the ball to collide with any of
     *         the Geometry objects in this Gadget.
     */
    @Override
    public double getMinCollisionTime(Ball ball) {
        Circle[] circles = new Circle[] { circle };
        LineSegment[] lineSegments = new LineSegment[0];
        return Util.getMinCollisionTime(circles, lineSegments, ball);
    }

    /**
     * Mutates the ball's velocity when the ball hits the bumper.
     * 
     * @param ball
     *            the ball which hit the bumper
     */
    @Override
    public void interactWithBall(Ball ball) {
        Vect newVelocity = Geometry.reflectCircle(circle.getCenter(), ball
                .getCircle().getCenter(), ball.getVelocity(),
                COEFFICIENT_OF_REFLECTION);
        ball.setVelocity(newVelocity);
    }

    @Override
    public Gadget[] trigger() {

        return this.gadgetsThisTriggers;
    }

    @Override
    public void Action() {
    }

    @Override
    public void setTime(double time) {
    }

    private void checkRep() {
        assert xCor >= 0 && xCor <= 19 && yCor >= 0 && yCor <= 19;
    }

    @Override
    public String toString() {
        return "O";
    }

    @Override
    public boolean isOccupying(int x, int y) {
        return x == this.xCor && y == this.yCor;
    }

    @Override
    public DoublePair getPosition() {
        return new Geometry.DoublePair((double) xCor, (double) yCor);
    }

    @Override
    public boolean isActing() {
        return false;
    }

}
