import static org.junit.Assert.*;
import physics.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class AbsorberTest {

    /*
     * Test Strategies:
     *      isOccupying:
     *          -True: within rectangle
     *          -False: outside
     *      interactWithBall: 
     *          -ball hits, is stored
     *      toString:
     *          -returns "="
     *      getPosition:
     *          -returns position
     *      trigger:
     *          -returns toTrigger
     *      getMinCollisionTime:
     *          -when ball will not collide with this, minCollisionTime is Double.POSITIVE_INFINITY
     */
    
    private static Gadget[] empty;
    private static Absorber absWide;
    private static Absorber absNarrow;
    private static Ball noCollision;
    private static Ball collisionWide;
    private static Ball collisionNarrow;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        empty = new Gadget[0];
        absWide = new Absorber(0, 15, 20, 2, empty);
        absNarrow = new Absorber(10, 10, 5, 2, new Gadget[]{new TriangleBumper(0,0,empty)});
        noCollision = new Ball(new Vect(1, 0), new Geometry.DoublePair(5.0,5.0));
        collisionWide = new Ball(new Vect(0, 1), new Geometry.DoublePair(2.0,5.0));
        collisionNarrow = new Ball(new Vect(0, 1), new Geometry.DoublePair(11.0,1.0));
    }
    
    @Test
    public void testIsOccupying() {
        assertTrue(absWide.isOccupying(16, 16));
        assertFalse(absWide.isOccupying(11, 19));
        assertTrue(absNarrow.isOccupying(10, 11));
        assertFalse(absNarrow.isOccupying(1, 16));
    }

    @Test
    public void testToString() {
        assertEquals("=", absWide.toString());
    }
    
    @Test
    public void testGetPosition() {
        assertTrue(Util.doublesAreEqual(0, absWide.getPosition().d1));
        assertTrue(Util.doublesAreEqual(15, absWide.getPosition().d2));
        assertTrue(Util.doublesAreEqual(10, absNarrow.getPosition().d1));
        assertTrue(Util.doublesAreEqual(10, absNarrow.getPosition().d2));
    }
    
    @Test
    public void testTrigger() {
        assertTrue(Util.doublesAreEqual(absNarrow.trigger()[0].getPosition().d1, new TriangleBumper(0,0,new Gadget[0]).getPosition().d1));
        assertTrue(Util.doublesAreEqual(absNarrow.trigger()[0].getPosition().d2, new TriangleBumper(0,0,new Gadget[0]).getPosition().d2));
    }
    
    @Test
    public void testGetMinCollisionTime() {
        assertTrue(Double.isInfinite(absWide.getMinCollisionTime(noCollision)));
        assertTrue(Double.isInfinite(absNarrow.getMinCollisionTime(noCollision)));
    }
    
    @Test
    public void testInteractWithBall() {
        absWide.interactWithBall(collisionWide);
        assertTrue(Util.doublesAreEqual(0, collisionWide.getVelocity().x()));
        assertTrue(Util.doublesAreEqual(0, collisionWide.getVelocity().y()));
        assertTrue(Util.doublesAreEqual(0, collisionNarrow.getVelocity().x()));
    }

}