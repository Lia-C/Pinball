import static org.junit.Assert.*;
import physics.*;

import org.junit.Test;


public class OuterWallTest {
    /*
     * Test Strategies:
     *      GetMinCollisionTime:
     *          -when ball is touching this, minCollisionTime is 0
     *          -when ball will not collide with this, minCollisionTime is Double.POSITIVE_INFINITY
     *      ToString:
     *          -returns .
     *      IsOccupying:
     *          -True: upper-left bounding box corner
     *          -False: outside
     *      GetPosition:
     *          -returns position
     *      InteractWithBall: 
     *          -Test that a ball passed in has its proper component of its velocity flipped
     */

    private static final int LENGTH=20;
    private static final int MAX_COORDINATE = LENGTH-1;
    private static final int HALF_OF_MAX_COORDINATE = MAX_COORDINATE/2; //this is just to the right of the midpoint bc of integer division
    private static final double BALL_RADIUS = 0.25;
    
    private static final OuterWall wall = new OuterWall(0, LENGTH, false); //bottom wall
    
    
    @Test
    public void testGetMinCollisionTime(){
        Ball ballTouching = new Ball(new Vect(1, 1), new Geometry.DoublePair(HALF_OF_MAX_COORDINATE, LENGTH-BALL_RADIUS));
        assertTrue(Util.doublesAreEqual(wall.getMinCollisionTime(ballTouching), 0));
        
        Ball ballNoCollision = new Ball(new Vect(-1, 0), new Geometry.DoublePair(MAX_COORDINATE,MAX_COORDINATE));
        assertTrue(Double.isInfinite(wall.getMinCollisionTime(ballNoCollision)));
    }
    
    /**
     * A ball hitting the bottom wall at a 45-degree angle
     * -> the ball's x-velocity should be unchanged, y-velocity should be negated
     */
    @Test
    public void testinteractWithBall() {

        double initialXVelocity = 1.0;
        double initialYVelocity = 1.0;
        Vect ballVelocity = new Vect(initialXVelocity, initialYVelocity); // ball moving due "southeast"
        Geometry.DoublePair ballPosition = new Geometry.DoublePair(MAX_COORDINATE/4, MAX_COORDINATE*3/4); //place the ball near the wall
        Ball ball=new Ball(ballVelocity, ballPosition);
        
        wall.interactWithBall(ball);
        
        double finalXVelocity = ball.getVelocity().x();
        double finalYVelocity = ball.getVelocity().y();
        
        //the ball's x-velocity should be unchanged, y-velocity should be negated
        assertTrue(Util.doublesAreEqual(initialXVelocity, finalXVelocity));
        assertTrue(Util.doublesAreEqual(initialYVelocity, -finalYVelocity));
    }
    
    @Test
    public final void testToString() {
        assertTrue(wall.toString().equals("."));
    }
    
    @Test
    public final void testIsOccupying() {
        assertTrue(wall.isOccupying(0,LENGTH));
        assertFalse(wall.isOccupying(1,LENGTH+1));
    }

    @Test
    public final void testgetPosition() {
        assertEquals(wall.getPosition(), new Geometry.DoublePair(0,LENGTH));
    }

}
