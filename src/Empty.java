/**
 * An immutable class representing an empty space on the Board.
 * Does not occupy any space on the board or interact with the ball.
 */
public class Empty implements Gadget {

    public Empty (){
        
    }
    
    public boolean isOccupying(int x, int y) {
        return false;
    }

    public String toString(){
        return " ";
    }
    
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void Action(Ball ball) {
                
    }

}
