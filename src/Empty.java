import physics.Geometry.DoublePair;

/**
 * An immutable class representing an empty space on the Board.
 * Does not occupy any space on the board or interact with the ball.
 */
public class Empty implements Gadget {

    /**
     * The only method of this class that should actually be called is isEmpty();
     */
    public Empty () {
        
    }
    
    public boolean isOccupying(int x, int y) {
        return false;
    }

    public String toString() {
        return " ";
    }
    
    public boolean isEmpty() {
        return true;
    }
    
    @Override
    public void Action(Ball ball) {
                
    }

    @Override
    public double getMinCollisionTime(Ball ball) {
        return 0;
    }

    @Override
    public DoublePair getPosition() {
        return null;
    }

}
