
public class Absorber implements Gadget{

    private final int height;
    private final int width;
    private final int x;
    private final int y;
    
    //AF Absorber = int:height+int:width+int:x+int:y
    /**
     * Absorbers move any ball that contact them to their bottom right corner, and then shoot them out soon afterwards.
     * 
     * @param height must be 0<height<=20
     * @param width must be 0<width<=20
     * @param x must be be  0<x<=20
     * @param y must be be  0<y<=20
     */
    public Absorber(int height, int width,int x,int y){
        this.height=height;
        this.width=width;
        this.x=x;
        this.y=y;
        checkRep();
    }
    
    private void checkRep() {
        assert this.height>0&&this.height<=20;
        assert this.width>0&&this.width<=20;
        assert this.x>0&&this.x<=20;
        assert this.y>0&&this.y<=20;
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
    
    public void Action (Ball ball){
        
    }
    
}
