import javax.management.RuntimeErrorException;

import physics.LineSegment;
import warmup.Ball;


public class Board {
    private final Ball ball;
    private OuterWall top = new OuterWall(//TODO);
    private OuterWall bottom = new OuterWall(//TODO);
    private OuterWall left = new OuterWall(//TODO);
    private OuterWall right = new OuterWall(//TODO);
    
    public Board(){
        
    }
    
    private addGadget(){
        
    }
    
    
    /**
     * Make a Benchmark Board
     * The 3 available benchmark boards are described at:
     *  http://web.mit.edu/6.005/www/fa14/projects/pb1/pingball-phase1-spec.html#benchmark_boards
     * @param boardName
     *          the name of the benchmark board you want
     *          must be one of "default", "absorber", "flippers"
     * @return
     *          a new Board representing the benchmark board
     */     
    public Board Board(String boardName){
        switch (boardName) {
        case "default":
            //TODO
                Board default = new Board();
                return default;
        case "absorber":
            //TODO
            break;
        case "flippers":
            //TODO
            break;
        default:
            throw new IllegalArgumentException();
            break;
        }
    }
    
    @Override
    public String toString(){
        String[][] 
    }
}
