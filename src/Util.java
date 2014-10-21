import physics.Circle;
import physics.Geometry;
import physics.LineSegment;


public class Util {
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
}
