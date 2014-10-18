import physics.*;

/**
 * An immutable class representing a circle bumper.
 */
public class CircleBumper {
    private final int xCor, yCor;
    private final Circle circle;
    
    private final double RADIUS = 0.5;
    private final double COEFFICIENT_OF_REFLECTION = 1.0;

    
    public CircleBumper(int xCor, int yCor){
        this.xCor = xCor;
        this.yCor = yCor;
        this.circle = new Circle(xCor+0.5, yCor+0.5, RADIUS)
    }
    
    @Override
    public String toString(){
        return "O";
    }
    
    
}
