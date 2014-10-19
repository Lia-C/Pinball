import static org.junit.Assert.*;
import physics.*;
import org.junit.Test;


public class OuterWallTest {
    /*
     * Test Strategies:
     *      isOccupying:
     *          -Test that a wall occupies all 21 spaces that it should and none it should;
     *          //(when transparency does happen)-Test that a transparent wall doesn't occupy space
     *      Action: 
     *          -Test that a ball passed in has its proper component of its velocity flipped
     */
    @Test
    public void testIsOccupying() {
        int wallLen=21;
        OuterWall wall = new OuterWall(0, 0, wallLen, false);
        boolean occupies=true;
        for (int i=0; i<wallLen;i++){
            if (!(wall.isOccupying(i, 0))){
                occupies=false;
            }
        }
        assertTrue(occupies&&!(wall.isOccupying(22, 22)));
    }
    
    @Test
    public void testAction() {
        double xVel=0;
        double yVel=1;
        int wallLen=21;
        OuterWall wall = new OuterWall(0, 0, wallLen, false);
        Ball ball=new Ball(new Vect(xVel, yVel), new Geometry.DoublePair(1));
        wall.Action(ball);
        Vect resultVel=ball.getVelocity();
        assertTrue(resultVel.y()==-yVel&&resultVel.x()==xVel);
    }

}
