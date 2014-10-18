
public class Empty implements Gadget {

    public Empty (){
        
    }
    
    @Override
    public boolean isOccupying() {
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
