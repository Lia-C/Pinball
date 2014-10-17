package warmup;

import physics.*;

public class Ball {
    private Vect velocity;
    private Geometry.DoublePair position;
    
    public Ball(Vect velocity, Geometry.DoublePair position){
        this.velocity = velocity;
        this.position = position;
    }
    
    public Vect getVelocity(){
        return this.velocity;
    }
    
    public Geometry.DoublePair getPosition(){
        return this.position;
    }
    
    public void setVelocity(Vect newVelocity){
        this.velocity = newVelocity;
    }
    
    public void setPosition(Geometry.DoublePair newPosition){
        this.position = newPosition;
    }
}
