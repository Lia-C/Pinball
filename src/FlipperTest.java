import static org.junit.Assert.*;
import physics.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class FlipperTest {
    /*
     * Testing Strategies:
     *      -Test all four orientations
     *      isOccupying:
     *          -True: depending on type and orientation
     *          -False: outside
     *      interactWithBall: 
     *          -ball hits a corner
     *          -ball hits a line segment 
     *      toString:
     *          -returns "|" or "-" depending on type and orientation
     *      getPosition:
     *          -returns position
     *      trigger:
     *          -returns toTrigger
     *      getMinCollisionTime:
     *          -when ball will not collide with this, minCollisionTime is Double.POSITIVE_INFINITY
     */
    
    private static Gadget[] empty;
    private static LeftFlipper leftFlipper0;
    private static LeftFlipper leftFlipper90;
    private static LeftFlipper leftFlipper180;
    private static LeftFlipper leftFlipper270;
    private static RightFlipper rightFlipper0;
    private static RightFlipper rightFlipper90;
    private static RightFlipper rightFlipper180;
    private static RightFlipper rightFlipper270;
    private static Ball noCollision;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        leftFlipper0 = new LeftFlipper(0,0,new Gadget[]{new TriangleBumper(0,0,empty)});
        leftFlipper90 = new LeftFlipper(0,0,90,empty);
        leftFlipper180 = new LeftFlipper(0,0,180,empty);
        leftFlipper270 = new LeftFlipper(0,0,270,empty);
        rightFlipper0 = new RightFlipper(0,0,new Gadget[]{new TriangleBumper(0,0,empty)});
        rightFlipper90 = new RightFlipper(0,0,90,empty);
        rightFlipper180 = new RightFlipper(0,0,180,empty);
        rightFlipper270 = new RightFlipper(0,0,270,empty);
        noCollision = new Ball(new Vect(1, 0), new Geometry.DoublePair(5.0,5.0));
    }
    
    @Test
    public void testIsOccupying() {
        assertTrue(leftFlipper0.isOccupying(0, 0));
        assertTrue(leftFlipper0.isOccupying(0, 1));
        assertFalse(leftFlipper0.isOccupying(1, 0));
        assertTrue(leftFlipper90.isOccupying(0, 0));
        assertTrue(leftFlipper90.isOccupying(1, 0));
        assertFalse(leftFlipper90.isOccupying(1, 1));
        assertTrue(leftFlipper180.isOccupying(1, 0));
        assertTrue(leftFlipper180.isOccupying(1, 1));
        assertFalse(leftFlipper180.isOccupying(0, 0));
        assertTrue(leftFlipper270.isOccupying(0, 1));
        assertTrue(leftFlipper270.isOccupying(1, 1));
        assertFalse(leftFlipper270.isOccupying(0, 0));
        assertTrue(rightFlipper0.isOccupying(1, 0));
        assertTrue(rightFlipper0.isOccupying(1, 1));
        assertFalse(rightFlipper0.isOccupying(0, 0));
        assertTrue(rightFlipper90.isOccupying(0, 1));
        assertTrue(rightFlipper90.isOccupying(1, 1));
        assertFalse(rightFlipper90.isOccupying(0, 0));
        assertTrue(rightFlipper180.isOccupying(0, 0));
        assertTrue(rightFlipper180.isOccupying(0, 1));
        assertFalse(rightFlipper180.isOccupying(1, 0));
        assertTrue(rightFlipper270.isOccupying(0, 0));
        assertTrue(rightFlipper270.isOccupying(1, 0));
        assertFalse(rightFlipper270.isOccupying(0, 1));
    }
    
    @Test
    public void testToString() {
        assertEquals(leftFlipper0.toString(), "|");
        assertEquals(leftFlipper90.toString(), "-");
        assertEquals(leftFlipper180.toString(), "|");
        assertEquals(leftFlipper270.toString(), "-");
        assertEquals(rightFlipper0.toString(), "|");
        assertEquals(rightFlipper90.toString(), "-");
        assertEquals(rightFlipper180.toString(), "|");
        assertEquals(rightFlipper270.toString(), "-");
    }
    
    @Test
    public void testGetPosition() {
        assertTrue(Util.doublesAreEqual(0, leftFlipper0.getPosition().d1));
        assertTrue(Util.doublesAreEqual(0, rightFlipper270.getPosition().d2));
    }
    
    @Test
    public void testTrigger() {
        assertTrue(Util.doublesAreEqual(leftFlipper0.trigger()[0].getPosition().d1, new TriangleBumper(0,0,new Gadget[0]).getPosition().d1));
        assertTrue(Util.doublesAreEqual(leftFlipper0.trigger()[0].getPosition().d2, new TriangleBumper(0,0,new Gadget[0]).getPosition().d2));
        assertTrue(Util.doublesAreEqual(rightFlipper0.trigger()[0].getPosition().d1, new TriangleBumper(0,0,new Gadget[0]).getPosition().d1));
        assertTrue(Util.doublesAreEqual(rightFlipper0.trigger()[0].getPosition().d2, new TriangleBumper(0,0,new Gadget[0]).getPosition().d2));
    }
    
    @Test
    public void testGetMinCollisionTime() {
        assertTrue(Double.isInfinite(leftFlipper0.getMinCollisionTime(noCollision)));
        assertTrue(Double.isInfinite(leftFlipper90.getMinCollisionTime(noCollision)));
        assertTrue(Double.isInfinite(leftFlipper180.getMinCollisionTime(noCollision)));
        assertTrue(Double.isInfinite(leftFlipper270.getMinCollisionTime(noCollision)));
        assertTrue(Double.isInfinite(rightFlipper0.getMinCollisionTime(noCollision)));
        assertTrue(Double.isInfinite(rightFlipper90.getMinCollisionTime(noCollision)));
        assertTrue(Double.isInfinite(rightFlipper180.getMinCollisionTime(noCollision)));
        assertTrue(Double.isInfinite(rightFlipper270.getMinCollisionTime(noCollision)));
    }
}