import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.*;


public class BoardTest {
    private static Ball ball1;
    private static Ball ball2;
    private static Ball ball3;
    private static Ball ball4;
    private static Board board;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {         
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

    //Checks that two colliding balls along the Y axis will reflect. (Intentional pun)
    @Test
    public final void testMakeBallsCollide(){
        
        Vect ball1PriorVel=new Vect(0,1);
        Vect ball2PriorVel=new Vect( 0,-1.);
        //position the balls to be set to collide
        ball1=new Ball(ball1PriorVel, new Geometry.DoublePair(1,1.5));
        ball2=new Ball(ball2PriorVel, new Geometry.DoublePair(1,2));
        
        try {
             java.lang.reflect.Method method = board.getClass().getDeclaredMethod("makeBallsCollide", new Class[] {Ball.class,Ball.class} );
             method.setAccessible(true);
             try {
                method.invoke(board, ball1,ball2);
                assertTrue(ball1.getVelocity().y()==-ball1PriorVel.y()&&ball2.getVelocity().y()==-ball2PriorVel.y());
            } catch (IllegalAccessException e) {
                assertTrue(false);
            } catch (IllegalArgumentException e) {
                assertTrue(false);
            } catch (InvocationTargetException e) {   
                assertTrue(false);
            }
        } catch (NoSuchMethodException e) {
            assertTrue(false);
        } catch (SecurityException e) {
            assertTrue(false);
        }
        
    }
    
    @Test
    public final void testMoveWithoutCollision() {
        double timeDelta=1;
        //Setting the position to the middle of the map so no collision will occur, and with initial timeDelta.
        ball1 = new Ball (new Vect(0,1),new Geometry.DoublePair(5.,5.));
        ball1.setTime(timeDelta);
        try {
            java.lang.reflect.Method method = board.getClass().getDeclaredMethod("moveWithoutCollision", new Class[] {Ball.class,double.class} );
            method.setAccessible(true);
            try {
               method.invoke(board, ball1,timeDelta);
               //The current position should have y component one greater that it used to be & there should be no remaining time
               assertTrue(ball1.getPosition().d2-1==5.&&ball1.getTime()==0);
           } catch (IllegalAccessException e) {
               assertTrue(false);
           } catch (IllegalArgumentException e) {
               assertTrue(false);
           } catch (InvocationTargetException e) {   
               assertTrue(false);
           }
       } catch (NoSuchMethodException e) {
           assertTrue(false);
       } catch (SecurityException e) {
           assertTrue(false);
       }
        
    }
    
    @Test
    public final void testGetBallWithMinCollisionTime(){
        double timeDelta=1;
        ball1=new Ball(new Vect(0,-1),new Geometry.DoublePair(3, 4));
        ball2=new Ball(new Vect(0,0),new Geometry.DoublePair(3, 3));
        ball3=new Ball(new Vect( 0,1.), new Geometry.DoublePair(3,1));
        ball4=new Ball(new Vect( 0,1.), new Geometry.DoublePair(6,6));
        Ball[] balls={ball1,ball2,ball3,ball4};
        Board newBoard=new Board(balls, null);
        try {
            java.lang.reflect.Method method = newBoard.getClass().getDeclaredMethod("getBallWithMinCollisionTime", new Class[] {Ball.class,double.class} );
            method.setAccessible(true);
            try {
               int collidedIndex= (int) method.invoke(newBoard, ball2,timeDelta);
               int missedIndex=(int) method.invoke(newBoard, ball4,timeDelta);
               //The collision should happen with ball1, and ball4 should miss, thus the method should return balls.length
               assertTrue(collidedIndex==0&&missedIndex==balls.length);
           } catch (IllegalAccessException e) {
               assertTrue(false);
               e.printStackTrace();
           } catch (IllegalArgumentException e) {
               assertTrue(false);
               e.printStackTrace();
           } catch (InvocationTargetException e) {   
               
               e.printStackTrace();
               assertTrue(false);
           }
       } catch (NoSuchMethodException e) {
           assertTrue(false);
           e.printStackTrace();
       } catch (SecurityException e) {
           assertTrue(false);
           e.printStackTrace();
       }
        
    }

}
