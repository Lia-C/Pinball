
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
    public boolean isOccupying() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEmpty() {
     
        return false;
    }
    
}
