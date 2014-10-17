package warmup;

import physics.*;

public class Ball {
    Vect velocity;
    Geometry.DoublePair position;
    
    public Ball(Vect velocity, Geometry.DoublePair position){
        this.velocity = velocity;
        this.position = position;
    }
    
    public Geometry.DoublePair getPosition(){
        return this.position;
    }
    
    public void setPosition(Geometry.DoublePair newPosition){
        this.position = newPosition;
    }
}
