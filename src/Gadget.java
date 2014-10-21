/**
 * Gadgets are objects on the Pingball board which perform some action
 * when they come into contact with the ball.
 */
public interface Gadget {
    
    /**
     * Meant to be used to determine which, if any, Gadget that a ball would hit, and when.
     * 
     * @param ball One of the balls moving around the map
     * @return The least time it would take for the ball to collide with any of the Geometry objects in this Gadget.
     */
    public double getMinCollisionTime(Ball ball);
    
    /**
     * Checks if a Gadget is an Empty gadget.
     * 
     * @return True if Empty. False otherwise.
     */
    public boolean isEmpty();
    
    /**
     * @return A string rep of this Gadget for a single cell in the Board.
     */
    @Override
    public String toString();
    
    /**
     * Modifies this gadget if need be, and mutates the Ball (typically the velocity)
     * 
     * @param ball An instance of Ball that is moving around the Board.
     * 
     */
    public void Action(Ball ball);
}
