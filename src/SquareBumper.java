import physics.*;


public class SquareBumper implements Gadget{
    private final LineSegment top, bottom, left, right;
    private final Circle topLeftCorner, topRightCorner, bottomLeftCorner, bottomRightCorner;
    private final int xCor, yCor;
    private final Gadget[] gadgetsThisTriggers;
    
    private static final double COEFFICIENT_OF_REFLECTION = 1.0;
    
    /**
     * An immutable class representing a square bumper.
     * 
     * Abstraction function: represents a square bumper with a specific location on the board
     * Rep invariant: xCor and yCor are in [0,19]
     * 
     * Size and shape: a square shape with edge length 1L
     * Orientation: not applicable (symmetric to 90 degree rotations)
     * Coefficient of reflection: 1.0
     * Trigger: generated whenever the ball hits it
     * Action: none, just adjusts the ball's velocity
     * 
     */
    
    /**
     * Make new square bumper
     * @param xCor
     *          x-coordinate of the desired upper-left of the bumper's bounding box
     *          has to be in the range [0,19] (needs to be in the playing area)
     * @param yCor
     *          y-coordinate of the desired upper-left of the bumper's bounding box
     *          has to be in the range [0,19] (needs to be in the playing area)
     */
    public SquareBumper(int xCor, int yCor, Gadget[] gadgetsThisTriggers){
        this.xCor = xCor;
        this.yCor = yCor;
        this.top = new LineSegment(xCor, yCor, xCor+1, yCor);
        this.bottom = new LineSegment(xCor, yCor+1, xCor+1, yCor+1);
        this.left = new LineSegment(xCor, yCor, xCor, yCor+1);
        this.right = new LineSegment(xCor+1, yCor, xCor+1, yCor+1);
        this.topLeftCorner = new Circle(xCor, yCor, 0);
        this.topRightCorner = new Circle(xCor + 1, yCor, 0);
        this.bottomLeftCorner = new Circle(xCor, yCor+1, 0);
        this.bottomRightCorner = new Circle(xCor+1, yCor+1, 0);
        this.gadgetsThisTriggers = gadgetsThisTriggers;
        checkRep();
    }
    

    @Override
    public double getMinCollisionTime(Ball ball){
        Circle[] circles = new Circle[] {topLeftCorner, topRightCorner, bottomLeftCorner, bottomRightCorner};
        LineSegment[] lineSegments = new LineSegment[] {top, bottom, left, right};
        return Util.getMinCollisionTime(circles, lineSegments, ball);
    }
    
    /**
     * Mutates the ball's velocity when the ball hits the bumper.
     * 
     * @param ball
     *          the ball which hit the bumper
     */
    @Override
    public void interactWithBall(Ball ball){
        Vect newVelocity = ball.getVelocity(); //just a throwaway initialization value
        
        Circle[] circles = new Circle[] {topLeftCorner, topRightCorner, bottomLeftCorner, bottomRightCorner};
        LineSegment[] lineSegments = new LineSegment[] {top, bottom, left, right};
        Object ballWillCollideWith = Util.getPartOfGadgetThatBallWillCollideWith(circles, lineSegments, ball);
        
        if (ballWillCollideWith instanceof LineSegment){
            //reflectWall(LineSegment line, Vect velocity, double reflectionCoeff)
            newVelocity = Geometry.reflectWall((LineSegment) ballWillCollideWith, ball.getVelocity(), COEFFICIENT_OF_REFLECTION);
        }
        else if (ballWillCollideWith instanceof Circle){
            newVelocity = Geometry.reflectCircle(((Circle) ballWillCollideWith).getCenter(), ball.getCircle().getCenter(), ball.getVelocity(), COEFFICIENT_OF_REFLECTION);
        }
        
        ball.setVelocity(newVelocity);
    }
    
    @Override
    public Gadget[] trigger(){
        return gadgetsThisTriggers;
    }
    
    @Override
    public void Action(){
    }
    
    @Override
    public void setTime(double time){
    }
    
    private void checkRep(){
        assert xCor >= 0 && xCor <= 19 && yCor >= 0 && yCor <= 19;
    }
    
    @Override
    public String toString(){
        return "#";
    }


    @Override
    public boolean isOccupying(int x, int y) {
        return x == xCor && y == yCor;
    }


    @Override
    public Geometry.DoublePair getPosition() {
        return new Geometry.DoublePair((double) xCor, (double) yCor);
    }
    
}
