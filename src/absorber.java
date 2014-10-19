
public class Absorber implements Gadget{

    private final int height;
    private final int width;
    private final int x;
    private final int y;
    private int balls;
    /**
     * 
     * @param height must be <=20
     * @param width must be <=20
     */
    public Absorber(int height, int width,int x,int y,int balls){
        this.height=height;
        this.width=width;
        this.x=x;
        this.y=y;
        this.balls=balls;
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
    
    public void Action
    
}
