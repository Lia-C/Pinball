import physics.*;

public class OuterWall implements Gadget{
    private final int length;
    private final int x;
    private final int y;
    private final boolean isVertical;
    private final boolean isTransparent;
    private final LineSegment line;
    
    /*
     * Rep Invariant:
     *     Defined in the OuterWall constructor preconditions
     * Abstraction Function:
     *     Represents a Gadget that reflects ball if this is not transparent.
     */
    /**
     * 
     * @param length Must be 21 for a 20x20 board
     * @param x must be 0 or 22
     * @param y must be 0 or 22
     * @param isVertical determines whether the wall is horizontal or vertical
     */
    public OuterWall(int length,int x, int y,boolean isVertical){
        this.length=length;
        this.x=x;
        this.y=y;
        this.isVertical=isVertical;
        this.isTransparent=false;
        if (isVertical){
            this.line = new LineSegment(x, y, x, y+length);
        }
        else{
            this.line = new LineSegment(x, y, x+length, y);
        }
        checkRep();
    }
    
    private void checkRep() {
        assert this.length==21;
        assert this.x==0 || this.x==22;
        assert this.y==0 || this.y==22;
    }
    
    
    @Override
    public String toString(){
        return "."; 
    }
    
    @Override
    public boolean isOccupying(int x, int y) {
        if (this.isTransparent){
            return false;
        }
        else{
          //IMPERFECT. NEEDS TO MAKE SURE WALL IS IN PROPER PLACE
            if (this.isVertical){
                
                if (x==0||x==length){
                    return true;
                }
                return false;
            }
            else{
                if (y==0||y==length){
                    return true;
                }
                return false;
            }
        }
    }

    
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void Action(Ball ball) {
        Vect vel=ball.getVelocity();
        Vect newVel;
        if (this.isVertical){
            newVel=new Vect(-vel.x(),vel.y());
        }
        else{
            newVel=new Vect(vel.x(),-vel.y());
        }
        ball.setVelocity(newVel);
    }
    
}
