
public class OuterWall implements Gadget{
    private final int boardWidth;
    private final int boardHeight;
    public OuterWall(int boardWidth, int boardHeight){
        this.boardWidth=boardWidth;
        this.boardHeight=boardHeight;
    }
    @Override
    public String toString(){
        return "."; 
    }
    @Override
    public boolean isOccupying(int x, int y) {
        if (x==0||x==(this.boardWidth-1)||y==0||y==(this.boardHeight-1)){
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
