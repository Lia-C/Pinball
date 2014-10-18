
public class Empty implements Gadget {

    public Empty (){
        
    }
    
    @Override
    public boolean isOccupying(int x, int y) {
        return false;
    }

    @Override 
    public String toString(){
        return " ";
    }
    
    @Override
    public boolean isEmpty() {
        return true;
    }

}
