import static org.junit.Assert.*;

import org.junit.Test;

import physics.Geometry;
import physics.Vect;

public class AbsorberTest {

    /*
     * Test Strategies: 
     *      isOccupying: 
     *          -Test that an absorber occupies all of the space in its area, and not out of it. 
     *      Action: 
     *          -Test that a ball passed in is moved to the bottom right corner, and that a ball is launched.
     */
    @Test
    public void testIsOccupying() {
        int height = 2;
        int width = 2;
        int x = 1;
        int y = 2;
        Absorber wall = new Absorber(height, width, x, y);
        boolean occupies = true;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!wall.isOccupying(x + width, y + height)) {
                    occupies = false;
                }
            }
        }
        assertTrue(occupies);
    }

    @Test
    public void testAction() {
        double xVel = 0;
        double yVel = 1;
        int wallLen = 21;
        OuterWall wall = new OuterWall(0, 0, wallLen, false);
        Ball ball = new Ball(new Vect(xVel, yVel), new Geometry.DoublePair(1));
        wall.Action(ball);
        Vect resultVel = ball.getVelocity();
        assertTrue(resultVel.y() == -yVel && resultVel.x() == xVel);
    }

}
