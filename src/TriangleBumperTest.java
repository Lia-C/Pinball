import static org.junit.Assert.*;
import physics.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class TriangleBumperTest {
    /*
     * Testing Strategies:
     *      -Test all four orientations
     *      isOccupying:
     *          -True: upper-left bounding box corner
     *          -False: outside
     *      interactWithBall: 
     *          -ball hits a corner at an angle
     *          -ball hits a line segment 
     *      toString:
     *          -returns "/" for orientation 0, 180
     *          -returns "\" for orientation 90, 270
     *      getPosition:
     *          -returns position
     *      trigger:
     *          -returns toTrigger
     *      getMinCollisionTime:
     *          -returns top wall when approaching head-on from top
     *          -when ball is touching this, minCollisionTime is 0
     *          -when ball will not collide with this, minCollisionTime is Double.POSITIVE_INFINITY
     */
    
    private static TriangleBumper bumper0; //orientation = 0
    private static TriangleBumper bumper90; //orientation = 90
    private static TriangleBumper bumper180; //orientation = 180
    private static TriangleBumper bumper270; //orientation = 270
    private static Ball hittingCorner; //Ball starting at (3,3) with velocity (1,1)
    
    @BeforeClass
    public static void setUpBeforeClass() {
        bumper0 = new TriangleBumper(3, 3, new Gadget[]{new TriangleBumper(0,0,new Gadget[0])});
        bumper90 = new TriangleBumper(5, 5, new Gadget[0]);
        bumper180 = new TriangleBumper(10, 10, new Gadget[0]);
        bumper270 = new TriangleBumper(17, 17, new Gadget[0]);
        comingFromCorner = new Ball(new Vect(1.0, 1.0), new Geometry.DoublePair(1.0,1.0));
    }

    @Test
    public void testIsOccupying() {
        assertTrue(bumper0.isOccupying(3,3));
        assertFalse(bumper0.isOccupying(2,3));
        assertTrue(bumper90.isOccupying(5,5));
        assertFalse(bumper90.isOccupying(3,3));
        assertTrue(bumper180.isOccupying(10,10));
        assertFalse(bumper180.isOccupying(3,3));
        assertTrue(bumper270.isOccupying(17,17));
        assertFalse(bumper270.isOccupying(3,3));
    }

    @Test
    public void testInteractWithBallCornerHit() {
        //ball hits at 45 degree angle -> x,y should be negated
        bumper0.interactWithBall(hittingCorner);
        assertTrue(Util.doublesAreEqual(-1.0, hittingCorner.getVelocity().x()));
        assertTrue(Util.doublesAreEqual(-1.0, hittingCorner.getVelocity().y()));
    }
    
    @Test
    public void testAction() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testToString() {
        fail("Not yet implemented"); // TODO
    }

    
}
