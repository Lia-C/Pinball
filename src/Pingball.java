
public class Pingball {

    
    public static void main(String[] args){
        String boardName;
        
        if (args.length == 0) boardName = "default";
        else boardName = args[0];
        
        Board board = new Board(boardName);
        board.run();
    }
}
