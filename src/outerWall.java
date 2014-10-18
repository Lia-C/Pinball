
public class OuterWall implements Gadget{
    private final int length;
    private final int x;
    private final int y;
    private final boolean isVertical;
    public final boolean isTransparent;
    public OuterWall(int length,int x, int y,boolean isVertical){
        this.length=length;
        this.x=x;
        this.y=y;
        this.isVertical=isVertical;
        this.isTransparent=false;
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

}
