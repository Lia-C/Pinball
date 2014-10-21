import physics.*;

/**
 * 
 * A mutable class representing the ball in the game.
 * (mutable because we need to set the velocity and position of the ball)
 *
 */
public class Ball {
    private Vect velocity;
    private Geometry.DoublePair position;
    private Circle circle;
    
    private final double BALL_RADIUS = 0.25;
    
    public Ball(Vect velocity, Geometry.DoublePair position){
        this.velocity = velocity;
        this.position = position;
        this.circle = new Circle(position.d1, position.d2, BALL_RADIUS);
    }
    
    public Vect getVelocity(){
        return this.velocity;
    }
    
    public Geometry.DoublePair getPosition(){
        return this.position;
    }

    
    public boolean isInBallSpace(double x, double y){
        double distFromOrigin=Math.sqrt(Math.pow((x-this.position.d1),2)+Math.pow((y-this.position.d2),2));
        if (distFromOrigin<=this.BALL_RADIUS){
            return true;
        }
        return false;
    }
    public Circle getCircle() {
        return this.circle;
    }
    
    public void setVelocity(Vect newVelocity){
        this.velocity = newVelocity;
    }
    
    public void setPosition(Geometry.DoublePair newPosition){
        this.position = newPosition;
        this.circle = new Circle(newPosition.d1, newPosition.d2, BALL_RADIUS);
    }
}
