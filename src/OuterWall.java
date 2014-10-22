import physics.*;
import physics.Geometry.DoublePair;

public class OuterWall implements Gadget{
    private final int x;
    private final int y;
    private final boolean isVertical;
    private final boolean isTransparent; // we don't use transparent walls in this phase of the project, but we put this here for future phases
    private final LineSegment line;

    private static final int LENGTH = 20;
    private static final double COEFFICIENT_OF_REFLECTION = 1.0;
    
    private final int MAX_COORDINATE = LENGTH-1;
    
    /*
     * Rep Invariant:
     *     Defined in the OuterWall constructor preconditions:
     *         x must be 0 or MAX_COORDINATE
     *         y must be 0 or MAX_COORDINATE
     *         x and y cannot both be MAX_COORDINATE
     *         
     * Abstraction Function:
     *     Represents a Gadget that reflects ball if this is not transparent.
     */
    
    /**
     * Make a new non-transparent outer wall. 
     * @param x 
     *          the x-coordinate of the upper-left corner of the outer wall
     *          must be 0 or MAX_COORDINATE
     * @param y 
     *          the y-coordinate of the upper-left corner of the outer wall
     *          must be 0 or MAX_COORDINATE
     * @param isVertical determines whether the wall is horizontal or vertical
     * 
     *  x and y cannot both be MAX_COORDINATE
     * 
     */
    public OuterWall(int x, int y,boolean isVertical){
        this.x=x;
        this.y=y;
        this.isVertical=isVertical;
        this.isTransparent=false;
        if (isVertical){
            this.line = new LineSegment(x, y, x, y+LENGTH);
        }
        else{
            this.line = new LineSegment(x, y, x+LENGTH, y);
        }
        checkRep();
    }
    
    private void checkRep() {
        assert this.x==0 || this.x==MAX_COORDINATE; // x must be 0 or MAX_COORDINATE
        assert this.y==0 || this.y==MAX_COORDINATE; // y must be 0 or MAX_COORDINATE
        assert ! (this.x==MAX_COORDINATE && this.y==MAX_COORDINATE); // x and y cannot both be MAX_COORDINATE
    }
    
    
    @Override
    public String toString(){
        return "."; 
    }

    
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Meant to be used to determine which, if any, Gadget that a ball would hit, and when.
     * 
     * @param ball One of the balls moving around the map
     * @return The least time it would take for the ball to collide with any of the Geometry objects in this Gadget.
     */
    public double getMinCollisionTime(Ball ball){
        Circle[] circles = new Circle[0];
        LineSegment[] lineSegments = new LineSegment[]{line};
        return Util.getMinCollisionTime(circles, lineSegments, ball);
    }
    
    /**
     * Mutates the ball's velocity when it hits this OuterWall
     * 
     * @param ball An instance of Ball that is moving around the Board.
     * 
     */
    @Override
    public void Action(Ball ball) {
        Vect newVelocity = Geometry.reflectWall(line, ball.getVelocity(), COEFFICIENT_OF_REFLECTION);
        ball.setVelocity(newVelocity);
    }

    @Override
    public boolean isOccupying(int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public DoublePair getPosition() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
