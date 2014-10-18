
public class absorber implements Gadget{

    private final int height;
    private final int width;
    private final int x;
    private final int y;
    /**
     * 
     * @param height must be <=20
     * @param width must be <=20
     */
    public absorber(int height, int width,int x,int y){
        this.height=height;
        this.width=width;
        this.x=x;
        this.y=y;
    }
    
    public String toString(){
        return "=";
    }

    @Override
    public boolean isOccupying(int x, int y) {
        if (x>=this.x&&x<=this.x+this.width&&y>=this.y&&y<=this.y+this.height){
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
     
        return false;
    }
    
}
