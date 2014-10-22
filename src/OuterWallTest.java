import static org.junit.Assert.*;
import physics.*;
import org.junit.Test;


public class OuterWallTest {
    /*
     * Test Strategies:
     *      Action: 
     *          -Test that a ball passed in has its proper component of its velocity flipped
     */

    
    
    /**
     * A ball hitting the bottom wall at a 45-degree angle
     */
    @Test
    public void testAction() {
        final int LENGTH=20;
        final int MAX_COORDINATE = LENGTH-1;
        final int HALF_OF_MAX_COORDINATE = MAX_COORDINATE/2; //this is just to the right of the midpoint bc of integer division
        
        double initialXVelocity = 1.0;
        double initialYVelocity = 1.0;
        Vect ballVelocity = new Vect(initialXVelocity, initialYVelocity); // ball moving due "southeast"
        Geometry.DoublePair ballPosition = new Geometry.DoublePair(MAX_COORDINATE, HALF_OF_MAX_COORDINATE);
        Ball ball=new Ball(ballVelocity, ballPosition);
        
        OuterWall wall = new OuterWall(LENGTH, LENGTH, false);
        
        wall.Action(ball);
        
        double finalXVelocity = ball.getVelocity().x();
        double finalYVelocity = ball.getVelocity().y();
        
        //the ball's x-velocity should be unchanged, y-velocity should be negated
        assertTrue(Util.doublesAreEqual(initialXVelocity, finalXVelocity));
        assertTrue(Util.doublesAreEqual(initialYVelocity, -finalYVelocity));
    }

}
