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
     *          -ball hits a corner
     *          -ball hits a line segment 
     *      toString:
     *          -returns "/" for orientation 0, 180
     *          -returns "\" for orientation 90, 270
     *      getPosition:
     *          -returns position
     *      trigger:
     *          -returns toTrigger
     *      getMinCollisionTime:
     *          -when ball will not collide with this, minCollisionTime is Double.POSITIVE_INFINITY
     */
    
    private static TriangleBumper bumper0; //orientation = 0
    private static TriangleBumper bumper90; //orientation = 90
    private static TriangleBumper bumper180; //orientation = 180
    private static TriangleBumper bumper270; //orientation = 270
    private static Ball hittingCorner; //Ball starting at (3,3) with velocity (1,1)
    private static Ball hittingWall; //Ball starting at (12,10.5) with velocity(-1,0)
    private static Ball noCollision; //Ball that will not collide with any bumper
    private static Gadget[] empty;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        empty = new Gadget[0];
        bumper0 = new TriangleBumper(3, 3, new Gadget[]{new TriangleBumper(0,0,empty)});
        bumper90 = new TriangleBumper(5, 5, 90, empty);
        bumper180 = new TriangleBumper(10, 10, 180,empty);
        bumper270 = new TriangleBumper(17, 17, 270, empty);
        hittingCorner = new Ball(new Vect(1.0, 1.0), new Geometry.DoublePair(3.0,3.0));
        hittingWall = new Ball(new Vect(-1.0, 0), new Geometry.DoublePair(12.0,10.5));
        noCollision = new Ball(new Vect(0, 1), new Geometry.DoublePair(1.0,1.0));
    }

    @Test
    public void testIsOccupying() {
        assertTrue(bumper0.isOccupying(3, 3));
        assertFalse(bumper0.isOccupying(2, 3));
        assertTrue(bumper90.isOccupying(5, 5));
        assertFalse(bumper90.isOccupying(3, 3));
        assertTrue(bumper180.isOccupying(10, 10));
        assertFalse(bumper180.isOccupying(3, 3));
        assertTrue(bumper270.isOccupying(17, 17));
        assertFalse(bumper270.isOccupying(3, 3));
    }

    @Test
    public void testInteractWithBallCornerHit() {
        bumper0.interactWithBall(hittingCorner);
        assertTrue(Util.doublesAreEqual(-1.0, hittingCorner.getVelocity().x()));
        assertTrue(Util.doublesAreEqual(-1.0, hittingCorner.getVelocity().y()));
    }

    @Test
    public void testInteractWithBallWallHit() {
        //ball hits leg head on -> x should be negated, y should be unchanged
        bumper180.interactWithBall(hittingWall);
        assertTrue(Util.doublesAreEqual(1.0, hittingWall.getVelocity().x()));
        assertTrue(Util.doublesAreEqual(0.0, hittingWall.getVelocity().y()));
    }

    @Test
    public void testToString() {
        assertEquals(bumper0.toString(), "/");
        assertEquals(bumper90.toString(), "\\");
    }
    
    @Test
    public void testTrigger() {
        assertTrue(Util.doublesAreEqual(bumper0.trigger()[0].getPosition().d1, new TriangleBumper(0,0,new Gadget[0]).getPosition().d1));
        assertTrue(Util.doublesAreEqual(bumper0.trigger()[0].getPosition().d2, new TriangleBumper(0,0,new Gadget[0]).getPosition().d2));
    }
    
    @Test
    public void testGetPosition() {
        assertTrue(Util.doublesAreEqual(3, bumper0.getPosition().d1));
        assertTrue(Util.doublesAreEqual(10, bumper180.getPosition().d2));
    }
    
    @Test
    public void testGetMinCollisionTime() {
        assertTrue(Double.isInfinite(bumper0.getMinCollisionTime(noCollision)));
        assertTrue(Double.isInfinite(bumper90.getMinCollisionTime(noCollision)));
        assertTrue(Double.isInfinite(bumper180.getMinCollisionTime(noCollision)));
        assertTrue(Double.isInfinite(bumper270.getMinCollisionTime(noCollision)));
    }

}