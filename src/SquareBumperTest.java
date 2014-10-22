import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.*;


public class SquareBumperTest {
    /*
     * Test Strategies:
     *      GetMinCollisionTime:
     *          -when ball is touching this, minCollisionTime is 0
     *          -when ball will not collide with this, minCollisionTime is Double.POSITIVE_INFINITY
     *      InteractWithBall: 
     *          -ball hits a corner at an angle
     *          -ball hits a corner/line segment head-on
     *      ToString:
     *          -returns #
     *      IsOccupying:
     *          -True: upper-left bounding box corner
     *          -False: outside
     *      GetPosition:
     *          -returns position
     *          
     */

    //a square bumper in the "center" of the board
    private static SquareBumper bumperInMiddle;
    
    //a ball touching the top-left corner of bumperInMiddle, whose velocity is "due southeast"
    private static Ball topLeftCornerBallCollideAtAngle; 
    
    //a ball touching the top-left corner of bumperInMiddle, whose velocity is "due east"
    private static Ball topLeftCornerBallCollideHeadOn; 
    
    //a ball touching the left side of bumperInMiddle, whose velocity is "due east"
    private static Ball leftSideBall; 
    
    //a ball that will not collide with bumperInMiddle (moving parallel to the bumper)
    private static Ball noCollisionBall; 

    private static final int MAX_COORDINATE = 19;
    private static final int HALF_OF_MAX_COORDINATE = MAX_COORDINATE/2;
    private static final double TILE_SIZE = 1.0;
    private static final double BALL_RADIUS = 0.25;
    private static final double BALL_CENTER_TRANSLATION_AMOUNT_TO_BE_AT_CORNER = BALL_RADIUS/Math.sqrt(2.0);
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        bumperInMiddle = new SquareBumper(HALF_OF_MAX_COORDINATE, HALF_OF_MAX_COORDINATE);
        topLeftCornerBallCollideAtAngle = new Ball(new Vect(1,1), new Geometry.DoublePair(HALF_OF_MAX_COORDINATE-BALL_CENTER_TRANSLATION_AMOUNT_TO_BE_AT_CORNER, HALF_OF_MAX_COORDINATE-BALL_CENTER_TRANSLATION_AMOUNT_TO_BE_AT_CORNER));
        topLeftCornerBallCollideHeadOn = new Ball(new Vect(1,0), new Geometry.DoublePair(HALF_OF_MAX_COORDINATE-BALL_CENTER_TRANSLATION_AMOUNT_TO_BE_AT_CORNER, HALF_OF_MAX_COORDINATE-BALL_CENTER_TRANSLATION_AMOUNT_TO_BE_AT_CORNER));
        leftSideBall = new Ball(new Vect(1,0), new Geometry.DoublePair(HALF_OF_MAX_COORDINATE-BALL_CENTER_TRANSLATION_AMOUNT_TO_BE_AT_CORNER, HALF_OF_MAX_COORDINATE-BALL_CENTER_TRANSLATION_AMOUNT_TO_BE_AT_CORNER+(TILE_SIZE/2)));
        noCollisionBall = new Ball(new Vect(0,1), new Geometry.DoublePair(HALF_OF_MAX_COORDINATE-TILE_SIZE, HALF_OF_MAX_COORDINATE-TILE_SIZE));
    }

    @Test
    public final void testGetMinCollision() {
        assertTrue(Util.doublesAreEqual(bumperInMiddle.getMinCollisionTime(topLeftCornerBallCollideAtAngle), 0));
        assertTrue(Util.doublesAreEqual(bumperInMiddle.getMinCollisionTime(topLeftCornerBallCollideHeadOn), 0));
        assertTrue(Util.doublesAreEqual(bumperInMiddle.getMinCollisionTime(leftSideBall), 0));
        assertTrue(Double.isInfinite(bumperInMiddle.getMinCollisionTime(noCollisionBall)));
    }

    
    //When a ball hits a corner at an angle; chose 45-degrees -> ball's x and y velocities are each flipped
    @Test
    public final void testinteractWithBallCornerAngle() {

        double initialXVelocity = 1.0;
        double initialYVelocity = 1.0;        
        //defensive copying
        Ball topLeftCornerBallCollideAtAngleCopy = new Ball(new Vect(initialXVelocity, initialYVelocity), new Geometry.DoublePair(HALF_OF_MAX_COORDINATE-BALL_CENTER_TRANSLATION_AMOUNT_TO_BE_AT_CORNER, HALF_OF_MAX_COORDINATE-BALL_CENTER_TRANSLATION_AMOUNT_TO_BE_AT_CORNER));
        
        bumperInMiddle.interactWithBall(topLeftCornerBallCollideAtAngleCopy);
        
        double finalXVelocity = topLeftCornerBallCollideAtAngleCopy.getVelocity().x();
        double finalYVelocity = topLeftCornerBallCollideAtAngleCopy.getVelocity().y();
        
        assertTrue(Util.doublesAreEqual(initialXVelocity, -finalXVelocity));
        assertTrue(Util.doublesAreEqual(initialYVelocity, -finalYVelocity));
    }
    
   
    //When a ball hits ball hits a corner/line segment; chose head-on collision with left side of bumper -> ball's x-velocity is flipped, y velocity same
    @Test
    public final void testinteractWithBallLineSegment() {

        double initialXVelocity = 1.0;
        double initialYVelocity = 0.0;        
        //defensive copying    
        Ball leftSideBall = new Ball(new Vect(initialXVelocity,initialYVelocity), new Geometry.DoublePair(HALF_OF_MAX_COORDINATE-BALL_CENTER_TRANSLATION_AMOUNT_TO_BE_AT_CORNER, HALF_OF_MAX_COORDINATE-BALL_CENTER_TRANSLATION_AMOUNT_TO_BE_AT_CORNER+(TILE_SIZE/2)));
        
        bumperInMiddle.interactWithBall(leftSideBall);
        
        double finalXVelocity = leftSideBall.getVelocity().x();
        double finalYVelocity = leftSideBall.getVelocity().y();
        
        assertTrue(Util.doublesAreEqual(initialXVelocity, -finalXVelocity));
        assertTrue(Util.doublesAreEqual(initialYVelocity, finalYVelocity));
    }
    

    @Test
    public final void testToString() {
        assertTrue(bumperInMiddle.toString().equals("#"));
    }

    @Test
    public final void testIsOccupying() {
        assertTrue(bumperInMiddle.isOccupying(HALF_OF_MAX_COORDINATE,HALF_OF_MAX_COORDINATE));
        assertFalse(bumperInMiddle.isOccupying(HALF_OF_MAX_COORDINATE+1,HALF_OF_MAX_COORDINATE+1));
    }

    @Test
    public final void testgetPosition() {
        assertEquals(bumperInMiddle.getPosition(), new Geometry.DoublePair(HALF_OF_MAX_COORDINATE, HALF_OF_MAX_COORDINATE));
    }
    
}
