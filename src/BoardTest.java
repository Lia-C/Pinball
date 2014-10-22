import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.*;


public class BoardTest {
    private static Ball ball1;
    private static Ball ball2;
    private static Board board;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
         ball1=new Ball(new Vect(1., 0), new Geometry.DoublePair(1.));
         ball2=new Ball(new Vect(-1., 0), new Geometry.DoublePair(2.));
         board=new Board("test");
    }

    @Test
    public final void testBoard() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testRun() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testTranslate() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testMakeBallsCollide(){
        Vect ball1PriorVel=ball1.getVelocity();
        Vect ball2PriorVel=ball2.getVelocity();
        //position the balls to be set to collide
        ball1.setPosition(new Geometry.DoublePair(1,1.5));
        ball2.setPosition(new Geometry.DoublePair(1,2));
        try {
             java.lang.reflect.Method method = board.getClass().getDeclaredMethod("makeBallsCollide", new Class[] {Ball.class,Ball.class} );
             method.setAccessible(true);
             try {
                method.invoke(board, ball1,ball2);
                assertTrue(ball1.getVelocity().y()==-ball1PriorVel.y()&&ball2.getVelocity().y()==-ball2PriorVel.y());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {   
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        
    }
    
    @Test
    public final void testToString() {
        fail("Not yet implemented"); // TODO
    }

}
