package warmup;

import physics.*;

public class Ball {
    private Vect velocity;
    private Geometry.DoublePair position;
    private Circle circle;
    
    public Ball(Vect velocity, Geometry.DoublePair position){
        this.velocity = velocity;
        this.position = position;
        this.circle = new Circle(position.d1, position.d2, 1);
    }
    
    public Vect getVelocity(){
        return this.velocity;
    }
    
    public Geometry.DoublePair getPosition(){
        return this.position;
    }
    
    public Circle getCircle() {
        return this.circle;
    }
    
    public void setVelocity(Vect newVelocity){
        this.velocity = newVelocity;
    }
    
    public void setPosition(Geometry.DoublePair newPosition){
        this.position = newPosition;
        this.circle = new Circle(newPosition.d1, newPosition.d2, 1);
    }
}
