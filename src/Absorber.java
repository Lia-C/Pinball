import physics.*;
import java.util.ArrayList;

/**
 * An immutable class representing an absorber. Absorbs and launches balls.
 * 
 * Bounding box of size kLxmL where k,m are positive integers <=20
 * Trigger: generated whenever the ball hits it
 * Action: shoots out a stored ball
 */
public class Absorber implements Gadget{
    private final int xCor, yCor, height, width;
    private final LineSegment top, left, bottom, right;
    private final Circle topLeft, topRight, bottomLeft, bottomRight;
    private final Geometry.DoublePair storageLoc;
    private ArrayList<Ball> storedBalls = new ArrayList<Ball>();
    
    private final Vect LAUNCH_VELOCITY = new Vect(Angle.DEG_90, 50.0);
    
    /*
     * Rep Invariant:
     *     Absorber must be contained within the board. i.e. xCor, yCor are in the range [0,19]
     *     and xCor + width , yCor + height are in the range [0,20]
     * Abstraction Function:
     *     Represents a Gadget that stops and holds balls and shoots them out.
     */
   
    /**
     * Absorbers move any ball that contact them to their bottom right corner, 
     * and then shoot them out when triggered again. Requires xCor + width <= 20
     * and yCor + height <= 20
     * 
     * @param height 
     *          must be 0<height<=20
     * @param width 
     *          must be 0<width<=20
     * @param xCor 
     *          must be 0<x<=20
     * @param y 
     *          must be 0<y<=20
     */
    public Absorber(int xCor, int yCor, int height, int width) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.height = height;
        this.width = width;
        this.top = new LineSegment(xCor, yCor, xCor+width, yCor);
        this.left = new LineSegment(xCor, yCor, xCor, yCor+height);
        this.bottom = new LineSegment(xCor, yCor+height, xCor+width, yCor+height);
        this.right = new LineSegment(xCor+width, yCor, xCor+width, yCor+height);
        this.topLeft = new Circle(xCor, yCor, 0);
        this.topRight = new Circle(xCor+width, yCor, 0);
        this.bottomLeft = new Circle(xCor, yCor+height, 0);
        this.bottomRight = new Circle(xCor+width, yCor+height, 0);
        this.storageLoc = new Geometry.DoublePair(xCor+width-.25, yCor+height-.25);
        checkRep();
    }
    
    private void checkRep() {
        assert height > 0 && height <= 20;
        assert width > 0 && width <= 20;
        assert xCor >= 0 && xCor <= 19;
        assert yCor >= 0 && yCor <= 19;
        assert xCor+width <= 20 && yCor+height <= 20;
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
    
    public double getMinCollisionTime(Ball ball) {
        LineSegment[] lineSegments = new LineSegment[]{top, left, bottom, right};
        Circle[] circles = new Circle[]{topLeft, topRight, bottomLeft, bottomRight};
        return Util.getMinCollisionTime(circles, lineSegments, ball);
    }
    
    public void Action (Ball ball){
        storedBalls.add(ball);
        ball.setVelocity(new Vect(0,0));
        ball.setPosition(storageLoc);
        if (!storedBalls.isEmpty()) {
            Ball launched = storedBalls.remove(0);
            launched.setVelocity(LAUNCH_VELOCITY);
        }
    }
    
}
