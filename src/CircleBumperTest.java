import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.Geometry;
import physics.Vect;


public class CircleBumperTest {
    /*
     * Test Strategies:
     *      IsEmpty:
     *          -return false
     *      GetMinCollisionTime:
     *          -when ball is touching this, minCollisionTime is 0
     *          -when ball will not collide with this, minCollisionTime is Double.POSITIVE_INFINITY
     *      Action: 
     *          
     *      ToString:
     *          -returns O
     *      IsOccupying:
     *          -True: upper-left bounding box corner
     *          -False: outside
     *      GetPosition:
     *          -returns position
     *          
     */
    
    //a circle bumper in the "center" of the board
    private static CircleBumper bumperInMiddle;
    
    //a ball touching the left side of bumperInMiddle, whose velocity is "due east"
    private static Ball leftSideBall; 
    
    //a ball that will not collide with bumperInMiddle (moving parallel to the bumper)
    private static Ball noCollisionBall; 
    
    private static final int MAX_COORDINATE = 19;
    private static final int HALF_OF_MAX_COORDINATE = MAX_COORDINATE/2;
    private static final double TILE_SIZE = 1.0;
    private static final double BALL_RADIUS = 0.25;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        bumperInMiddle = new CircleBumper(HALF_OF_MAX_COORDINATE, HALF_OF_MAX_COORDINATE);
        leftSideBall = new Ball(new Vect(1,0), new Geometry.DoublePair(HALF_OF_MAX_COORDINATE-(BALL_RADIUS/4), HALF_OF_MAX_COORDINATE+BALL_RADIUS));
        noCollisionBall = new Ball(new Vect(0,1), new Geometry.DoublePair(HALF_OF_MAX_COORDINATE-TILE_SIZE, HALF_OF_MAX_COORDINATE-TILE_SIZE));
    }

    @Test
    public final void testIsEmpty() {
        assertFalse(bumperInMiddle.isEmpty());
    }

    @Test
    public final void testGetMinCollisionTime() {
        assertTrue(Util.doublesAreEqual(bumperInMiddle.getMinCollisionTime(leftSideBall), 0));
        assertTrue(Double.isInfinite(bumperInMiddle.getMinCollisionTime(noCollisionBall)));
    }
    
//    @Test
//    public final void testAction() {
//        fail("Not yet implemented"); // TODO
//    }

    @Test
    public final void testToString() {
        assertTrue(bumperInMiddle.toString().equals("O"));
    }

    
    @Test
    public final void testIsOccupying() {
        assertTrue(bumperInMiddle.isOccupying(HALF_OF_MAX_COORDINATE,HALF_OF_MAX_COORDINATE));
        assertFalse(bumperInMiddle.isOccupying(HALF_OF_MAX_COORDINATE+1,HALF_OF_MAX_COORDINATE+1));
    }

    @Test
    public final void testGetPosition() {
        assertEquals(bumperInMiddle.getPosition(), new Geometry.DoublePair(HALF_OF_MAX_COORDINATE, HALF_OF_MAX_COORDINATE));
    }

}
