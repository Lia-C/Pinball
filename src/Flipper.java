import physics.*;

/**
 * An immutable class representing a flipper.
 * 
 * Bounding box of size 2Lx2L
 * For a left flipper, the pivot point is in the northwest corner, and its
 * initial rotation is counterclockwise for the default orientation (0 degrees).
 * For a right flipper, the pivot point is in the northeast corner, and its
 * initial rotation is clockwise for the default orientation (0 degrees).
 * Requires that orientation is 0 or 90.
 * Requires that type is "left" or "right".
 * Coefficient of reflection: 0.95
 * Triggered when hit by the ball. Rotates 90 degrees.
 */
public class Flipper implements Gadget {
    private final int xCor, yCor, orientation; //orientation must be 0 or 90
    private final String type; //must be "left" or "right"
    
    private final double COEFFICIENT_OF_REFLECTION = 0.95;
    
    public Flipper(int xCor, int yCor, String type) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.type = type;
        this.orientation = 0;
    }
    
    public Flipper(int xCor, int yCor, String type, int orientation) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.type = type;
        this.orientation = orientation;
    }
    
    public boolean isOccupying(int x, int y) {
        if (this.type.equalsIgnoreCase("left")) {
            if (orientation == 0) {
                if (x == xCor && y >= yCor && y <= yCor+1) { return true; } 
                else { return false; }
            } else if (orientation == 90) {
                if (x >= xCor && x <= xCor+1 && y == yCor) { return true; } 
                else { return false; }
            } else {
                throw new RuntimeException("Flipper type must be \"left\" or \"right\"");
            }
        } else if (type.equalsIgnoreCase("right")) {
            if (orientation == 0) {
                if (x == xCor+1 && y >= yCor && y <= yCor+1) { return true; } 
                else { return false; }
            } else if (orientation == 90) {
                if (x >= xCor && x <= xCor+1 && y == yCor) { return true; } 
                else { return false; }
            } else {
                throw new RuntimeException("Flipper type must be \"left\" or \"right\"");
            }
        }
        return true; //this should not be reached
    }
    
    public boolean isEmpty() {
        return false;
    }
    
}
