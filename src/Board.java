import javax.management.RuntimeErrorException;


public class Board {

    
    public Board(){
        
    }
    
    public Board(String boardName){
        
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
    private Board makeBenchmarkBoard(String boardName){
        switch (boardName) {
        case "default":
                break;
        case "absorber":
            break;
        case "flippers":
            break;
        default:
            throw new RuntimeErrorException();
            break;
        }
        if boardName.equals("default"){
            
        }
    }
}
