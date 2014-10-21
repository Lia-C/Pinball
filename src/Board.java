import javax.management.RuntimeErrorException;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;



public class Board {
    
    private final Ball[] balls;
    private final Gadget[] gadgets;
    private final double GRAVITY=10;
    private final double MU=.005;
    private final double MU2=.001;
    private final int WIDTH = 22;
    private final int HEIGHT = 22;
    private OuterWall top = new OuterWall(HEIGHT, HEIGHT, HEIGHT, false);
    private OuterWall bottom = new OuterWall(HEIGHT, HEIGHT, HEIGHT, false);
    private OuterWall left = new OuterWall(HEIGHT, HEIGHT, HEIGHT, false);
    private OuterWall right = new OuterWall(HEIGHT, HEIGHT, HEIGHT, false);
    
    /**
     * An immutable class representing the board in the game.
     * The board is the uppermost-level class, in that the game is run by calling board.run().
     *
     * Abstraction function: represents a board in the game
     * Rep invariant: width and height of the board are 22
     *
     */
    
    
    /**
     * Make a Benchmark Board
     * The 3 available benchmark boards are described at:
     *  http://web.mit.edu/6.005/www/fa14/projects/pb1/pingball-phase1-spec.html#benchmark_boards
     * @param boardName
     *          the name of the benchmark board you want
     *          must be one of "default", "absorber", "flippers"
     */     
    public Board(String boardName){
        //JUST TO MAKE IT COMPILE
        gadgets=null;
        balls=null;
        switch (boardName) {
        case "default":
            //TODO
                /*ball= 
                Gadgets[] = 
                Board default = new Board(ball, gadgets);
                */
            
        case "absorber":
            //TODO
            break;
        case "flippers":
            //TODO
            break;
        default:
            throw new IllegalArgumentException();
            //break;
        }
        checkRep();
    }
    
    /**
     * Make a new board
     * @param ball
     *          Ball object representing the ball
     * @param gadgets
     *          an array of the gadgets that are on the board, 
     *          NOT including the outer walls
     */
    private Board(Ball ball, Gadget[] gadgets){
        this.ball = ball;
        this.gadgets = gadgets;
        checkRep();
    }    
    
    private void checkRep(){
        assert WIDTH == 22 && HEIGHT == 22;
    }
    
    /**
     * Start playing the game.
     */
    public void run(){
        long deltaT = (long) (1000./20.);
        while(true){
            System.out.println(this);   
            translate(deltaT); 
            try {
                Thread.sleep((long) (1000./20.));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
    /**
     * Update the ball's position and velocity, according to how it moved in the time period specified
     * 
     * @param deltaT
     *      the time in milliseconds to update the ball's movement over
     */
    /*
    //////////////////////////////////////////////////////////////
    //CURRENTLY THIS METHOD DOESN'T TAKE GADGETS INTO ACCOUNT///////
    //IT ONLY ACCOUNTS FOR WALLS////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    //TODO: add gravity, friction
    //TODO: add hitting non-wall gadgets
    public void translate(long deltaT) {
        double deltaX = this.ball.getVelocity().x() * deltaT/1000;
        double deltaY = this.ball.getVelocity().y() * deltaT/1000;
        double newX = this.ball.getPosition().d1 + deltaX;
        double newY = this.ball.getPosition().d2 + deltaY;
        double xOver = Math.abs(newX - WIDTH);
        double yOver = Math.abs(newY - HEIGHT);
        Geometry.DoublePair newLoc = new Geometry.DoublePair(newX, newY); 
        LineSegment collisionWall = new LineSegment(0,0,0,0);
        /*
        if (newX >= WIDTH && newY <= HEIGHT && newY >= 0){
            collisionWall = right.getLineSegment();
        } else if (newX <= 0 && newY <= HEIGHT && newY >= 0){
            collisionWall = left.getLineSegment();
        } else if (newY >= HEIGHT && newX <= WIDTH && newX >= 0){
            collisionWall = bottom.getLineSegment();
        } else if (newY <= 0 && newX <= WIDTH && newX >= 0){
            collisionWall = top.getLineSegment();
        } else if (newX >= WIDTH && newY >= HEIGHT){
            if (xOver > yOver){ collisionWall = right.getLineSegment(); }
            else { collisionWall = bottom.getLineSegment(); }
        } else if (newX >= WIDTH && newY <= 0){
            if (xOver > yOver){ collisionWall = right.getLineSegment(); }
            else { collisionWall = top.getLineSegment(); }
        } else if (newX <= 0 && newY >= HEIGHT){ 
            if (xOver > yOver){ collisionWall = left.getLineSegment(); }
            else { collisionWall = bottom.getLineSegment(); }
        } else if (newX <= 0 && newY <= 0){
            if (xOver > yOver){ collisionWall = left.getLineSegment(); }
            else { collisionWall = top.getLineSegment(); }
        }
        
        if (newX > 0 && newX < WIDTH && newY > 0 && newY < WIDTH){
            moveBallWithoutGadgetCollision(newLoc);
        } else {
            moveBallWithGadgetCollision(newLoc, collisionWall, deltaT);
        }
    }
    
    /**
     * Update the ball's position and velocity, 
     * Call this method only if the ball did not collide with any gadget (including walls)
     * @param newLoc
     *      //TODO
     
    private void moveBallWithoutGadgetCollision(Geometry.DoublePair newLoc){
        //TODO: update this for ALL gadgets; right now this only works with hitting walls
        this.ball.setPosition(newLoc);
        double x=this.ball.getPosition().d1;
        double y=this.ball.getPosition().d2;
        if ((int)x<=0){
            x=1;
            this.ball.setVelocity(new Vect(-this.ball.getVelocity().x(),this.ball.getVelocity().y()));
        }
        if ((int)x>=WIDTH-1){
            x=WIDTH-2;
            this.ball.setVelocity(new Vect(-this.ball.getVelocity().x(),this.ball.getVelocity().y()));
        }
        if ((int)y<=0){
            y=1;
            this.ball.setVelocity(new Vect(this.ball.getVelocity().x(),-this.ball.getVelocity().y()));
        }
        if ((int)y>=HEIGHT-1){
            y=HEIGHT-2;
            this.ball.setVelocity(new Vect(this.ball.getVelocity().x(),-this.ball.getVelocity().y()));
        }
        this.ball.setPosition( new Geometry.DoublePair(x, y));
    }
  
    /**
     * Update the ball's position and velocity,
     * Call this method only if the ball DID collide with a wall.
     * @param newLoc
     *      //TODO
     * @param wall
     *      //TODO
     * @param deltaT
     *      //TODO
     
    private void moveBallWithGadgetCollision(Geometry.DoublePair newLoc, LineSegment wall, long deltaT){
        //TODO: update this for ALL gadgets; right now this only works with hitting walls
        double n = Geometry.timeUntilWallCollision(wall, this.ball.getCircle(), this.ball.getVelocity());
        if (n != Double.POSITIVE_INFINITY){
            long timeUntilCollision = (long) n;
            this.translate(timeUntilCollision);
            this.ball.setVelocity(Geometry.reflectWall(wall, this.ball.getVelocity()));
            this.translate(deltaT - timeUntilCollision);
        }
    }
    */
    /**
     * Responsible for moving all balls
     * @param timeDelta The time interval during which the balls are moving.
     */
    public void translate(Ball ball,double timeDelta){
        
    }
    
    /**
     * 
     * @param ball One of the balls traversing the map
     * @param timeDelta The amount of time the ball will be moving.
     * @return The Gadget with the least collision time with Ball. Returns an Empty Gadget if none will be collided with in the timeDelta.
     */
    private Gadget getGadgetWithMinCollisionTime(Ball ball,double timeDelta){
        Gadget returnedGadget=new Empty();
        double minTime=Double.POSITIVE_INFINITY;
        for (Gadget gadget:gadgets){
            double collisionTime=gadget.getMinCollisionTime(ball);
            if (collisionTime<minTime){
                minTime=collisionTime;
                returnedGadget=gadget;
            }
        }
        if (minTime<timeDelta){
            return returnedGadget; 
        }
        else{
            return new Empty();
        }
    }
    
    /**
     * Takes in a ball and discretely updates its velocity in accordance with the acceleration caused by friction and gr
     * 
     * @param ball One of the Balls traversing the map
     * @param timeDelta The amount of time the ball will be moving.
     */
    private void updateVelWithAccel(Ball ball,double timeDelta){
        //Updating with Friction
        double magnitude=ball.getVelocity().length();
        magnitude=magnitude*(1-this.MU*timeDelta-this.MU2*magnitude*timeDelta);
        Vect intermediateVel=new Vect(ball.getVelocity().angle(), magnitude);
        //Updating using Gravity
        Vect withGrav=new Vect(intermediateVel.x(),intermediateVel.y()+this.GRAVITY*timeDelta);
        ball.setVelocity(withGrav);
    }
    
    /**
     * 
     * @param ball One of the Balls traversing the map
     * @param timeDelta The amount of time the ball will be moving.
     * @return The Ball with the least collision time with ball. Returns ball if none will be collided with in the timeDelta.
     */
    private Ball getBallWithMinCollisionTime(Ball ball,double timeDelta){
        double minTime=Double.POSITIVE_INFINITY;
        Ball otherBall=ball;
        for (Ball other:balls){
            //This the other ball is not ball.
            if (!other.getPosition().equals(ball.getPosition())){
                double collisionTime=Geometry.timeUntilBallBallCollision(ball.getCircle(), ball.getVelocity(), other.getCircle(), other.getVelocity());
                if (collisionTime<minTime){
                    minTime=collisionTime;
                    otherBall=other;
                }
            }
        }
        if (minTime<timeDelta){
            return otherBall; 
        }
        else{
            return ball;
        }
    }
    /**
     * 
     * @return A String representation of the board
     */
    @Override
    public String toString(){
        String[][] board = new String[20][20];
        //fill with empty strings
        //for each tile x,y
        //      for each gadget in Gadgets[]
        //          if gadget.isOccupying(x,y)
        //              board[x][y] = gadget.toString()
        return null;
        
        // add ball
    }
}
