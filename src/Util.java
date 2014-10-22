import physics.Circle;
import physics.Geometry;
import physics.LineSegment;


public class Util {
    
    /**
     * Returns the smallest collision time within a group of circles and linesegments 
     * for the ball to hit
     * 
     * @param circles
     *          an array of circles 
     * @param lineSegments
     *          an array of linesegments
     * @param ball
     *          the ball which may hit the circles/linesegments
     * @return
     *          the smallest collision time within a group of circles and linesegments 
     *          for the ball to hit
     */
    public static double getMinCollisionTime(Circle[] circles, LineSegment[] lineSegments, Ball ball){

        double minCollisionTime = Double.POSITIVE_INFINITY;
        double current = Double.POSITIVE_INFINITY;
        
        
        for(Circle circle:circles){
            current = Geometry.timeUntilCircleCollision(circle, ball.getCircle(), ball.getVelocity());
            if (current < minCollisionTime) minCollisionTime = current;
        }
        
        for(LineSegment lineSegment:lineSegments){
            current = Geometry.timeUntilWallCollision(lineSegment, ball.getCircle(), ball.getVelocity());
            if (current < minCollisionTime) minCollisionTime = current;
        }
       
        return minCollisionTime;
    }
    
    /**
     * Returns the circle/linesegment with the minimum collision time out of the array of circles and array of linesegments
     * In other words, it returns whichever circle/linesegment the ball would hit first
     * 
     * @param circles
     *          an array of circles
     * @param lineSegments
     *          an array of linesegments
     * @param ball
     *          the ball which may hit the circles/linesegments
     * @return
     *          the circle/linesegment with the minimum collision time out of the array of circles and array of linesegments
     *          in other words, this returns whichever circle/linesegment the ball would hit first
     *          NOTE: in the case that there is no minimum (this should NEVER happen), this method returns an empty gadget
     */
    
    public static Object getPartOfGadgetThatBallWillCollideWith(Circle[] circles, LineSegment[] lineSegments, Ball ball){
        double minCollisionTime = getMinCollisionTime(circles, lineSegments, ball);
        double current = Double.POSITIVE_INFINITY;
        
        for(Circle circle:circles){
            current = Geometry.timeUntilCircleCollision(circle, ball.getCircle(), ball.getVelocity());
            if (current == minCollisionTime) return circle;
        }
        
        for(LineSegment lineSegment:lineSegments){
            current = Geometry.timeUntilWallCollision(lineSegment, ball.getCircle(), ball.getVelocity());
            if (current == minCollisionTime) return lineSegment;
        }
      
        //this code should never be reached
        return new Empty();
        
    }
    
}
