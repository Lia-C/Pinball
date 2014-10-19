/**
 * Gadgets are objects on the Pingball board which perform some action
 * when they come into contact with the ball.
 */
public interface Gadget {
    
    /**
     * Checks if a Gadget occupies the location at coordinates (x, y).
     * 
     * @param x 
     *          x-coordinate of location being checked
     * @param y 
     *          y-coordinate of location being checked
     * @return True if Gadget occupies location at (x, y). False otherwise.
     */
    public boolean isOccupying(int x, int y);
    
    /**
     * Checks if a Gadget is an Empty gadget.
     * 
     * @return True if Empty. False otherwise.
     */
    public boolean isEmpty();
    
    @Override
    public String toString();
    
}
