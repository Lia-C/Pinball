import physics.*;

public class OuterWall implements Gadget{
    private final int length;
    private final int x;
    private final int y;
    private final boolean isVertical;
    private final boolean isTransparent;
    private final LineSegment line;
    
    /**
     * 
     * @param length
     * @param x
     * @param y
     * @param isVertical
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
        
    }
    
    /**
     * 
     * @return
     */
    public LineSegment getLineSegment(){
        return line;
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
          //FIX THIS BASED ON IMPLEMENTATION of GRAPH
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
