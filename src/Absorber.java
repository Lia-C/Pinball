
public class Absorber implements Gadget{

    private final int height;
    private final int width;
    private final int x;
    private final int y;
    
    /*
     * Rep Invariant:
     *     Defined in the Absorber constructor preconditions
     * Abstraction Function:
     *     Represents a Gadget that stops balls and shoots them out.
     */
    /**
     * Absorbers move any ball that contact them to their bottom right corner, and then shoot them out soon afterwards.
     * 
     * @param height must be 0<height<=20
     * @param width must be 0<width<=20
     * @param xCor must be be  0<x<=20
     * @param y must be be  0<y<=20
     */
    public Absorber(int height, int width,int x,int y) {
        this.height=height;
        this.width=width;
        this.x=x;
        this.y=y;
        checkRep();
    }
    
    private void checkRep() {
        assert this.height>0 && this.height<=20;
        assert this.width>0 && this.width<=20;
        assert this.x>=0 && this.x<=20;
        assert this.y>0 && this.y<=20;
    }
    
    public String toString(){
        return "=";
    }

//    public boolean isOccupying(int x, int y) {
//        if (x>=this.x&&x<=this.x+this.width&&y>=this.y&&y<=this.y+this.height){
//            return true;
//        }
//        return false;
//    }

    public boolean isEmpty() {
        return false;
    }
    
    public void Action (Ball ball){
        
    }
    
}
