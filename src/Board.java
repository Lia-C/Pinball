import javax.management.RuntimeErrorException;

import physics.Geometry;
import physics.Geometry.DoublePair;
import physics.LineSegment;
import physics.Vect;

public class Board {

    private final Ball[] balls;
    private final Gadget[] gadgets;
    private final double GRAVITY = 10;
    private final double MU = .005;
    private final double MU2 = .001;
    private final double BALLMASS = 1;
    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    private final int MAX_X_COORDINATE = WIDTH-1;
    private final int MAX_Y_COORDINATE = HEIGHT-1;
    private OuterWall top = new OuterWall(0, 0, false);
    private OuterWall bottom = new OuterWall(0, MAX_Y_COORDINATE, false);
    private OuterWall left = new OuterWall(0, 0, true);
    private OuterWall right = new OuterWall(MAX_X_COORDINATE, 0, true);
    private final double TIMEBETWEENFRAMES = 1000. / 60.;// Given in
                                                         // milliseconds;

    /**
     * An immutable class representing the board in the game. The board is the
     * uppermost-level class, in that the game is run by calling board.run().
     *
     * Abstraction function: represents a board in the game Rep invariant: width
     * and height of the board are 22
     *
     */

    /**
     * Make a Benchmark Board The 3 available benchmark boards are described at:
     * http://web.mit.edu/6.005/www/fa14/projects/pb1/pingball-phase1-spec.html#
     * benchmark_boards
     * 
     * @param boardName
     *            the name of the benchmark board you want must be one of
     *            "default", "absorber", "flippers"
     */
    public Board(String boardName) {
        // JUST TO MAKE IT COMPILE
        gadgets = null;
        balls = null;
        /*switch (boardName) {
        case "default":
            // TODO
            /*
             * ball= Gadgets[] = Board default = new Board(ball, gadgets);
             /

        case "absorber":
            // TODO
            break;
        case "flippers":
            // TODO
            break;
        default:
            throw new IllegalArgumentException();
            // break;
        }*/
        checkRep();
    }

    /**
     * Make a new board
     * 
     * @param ball
     *            Ball object representing the ball
     * @param gadgets
     *            an array of the gadgets that are on the board (the outer walls need not be on the board).
     */
    public Board(Ball[] balls, Gadget[] gadgets) {
        this.balls = balls;
        this.gadgets = gadgets;
        checkRep();
    }
    
    //RI is that all object on the board are within the width and height of board, and that gravity and friction coefficients are greater than zero..
    private void checkRep() {
        for (Ball ball:balls){
            assert ball.getPosition().d1>=0&&ball.getPosition().d1<=WIDTH-1;
            assert ball.getPosition().d2>=0&&ball.getPosition().d2<=HEIGHT-1;
        }
        for (Gadget gadget:gadgets){
            assert gadget.getPosition().d1>=0&&gadget.getPosition().d1<=WIDTH-1;
            assert gadget.getPosition().d2>=0&&gadget.getPosition().d2<=HEIGHT-1;
        }
        
        assert GRAVITY>0&&MU2>0&&MU>0;
    }

    /**
     * Start playing the game.
     */
    public void run() {

        while (true) {
            System.out.println(this);
            // updateGraph takes in seconds.
            this.updateBoard(this.TIMEBETWEENFRAMES * 1000);
            try {
                Thread.sleep((long) (this.TIMEBETWEENFRAMES));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * If a ball doesn't collide with anything in timeDelta, it will be moved
     * the full motion and then it's internal time will be set to 0. If it does,
     * it will move to the point where it collides, trigger an action, and then
     * be fed back into translate with the remaining time.
     * 
     * @param timeDelta
     *            The time interval in seconds during which the balls are
     *            moving.
     */
    private void translate(Ball ball, double timeDelta) {
   
        /*
         * The point of indexing into the arrays rather than, for example, using
         * Ball ball=this.getBallWithMinCollisionTime(ball, timeDelta) is that
         * we want the elements of the array to mutate if need be.
         */
        int gadgetIndex = this.getGadgetWithMinCollisionTime(ball, timeDelta);
        int ballIndex = this.getBallWithMinCollisionTime(ball, timeDelta);
        double gadgetTime = this.gadgets[gadgetIndex].getMinCollisionTime(ball);
        double ballTime = Geometry.timeUntilBallBallCollision(ball.getCircle(),
                ball.getVelocity(), this.balls[ballIndex].getCircle(),
                this.balls[ballIndex].getVelocity());
        //If any gadgets are triggered, they will be kept track of with this and then their actions called.
        Gadget[] triggeredGadgets = new Gadget[] {};
        // If the ball won't collide with a Gadget within timeDelta.
        if (gadgetIndex == this.gadgets.length) {
            // If the ball won't collide with a Ball within timeDelta.
            if (this.balls.length == ballIndex) {
                this.moveWithoutCollision(ball, timeDelta);
            }
            // If the ball will collide with a Ball within timeDelta.
            else {
                this.moveWithoutCollision(ball, ballTime);
                this.moveWithoutCollision(this.balls[ballIndex], ballTime);
                this.makeBallsCollide(ball, this.balls[ballIndex]);
            }
        }
        // If the ball won't collide with a Gadget within timeDelta.
        else {
            // If the ball won't collide with a Ball within timeDelta.
            if (this.balls.length == ballIndex) {
                this.moveWithoutCollision(ball, timeDelta);
            }
            // If the ball will possibly collide with both a ball and a Gadget.
            else {

                // For now avoiding the case that a ball will hit a ball and
                // gadget at the same time.
                if (gadgetTime > ballTime) {
                    this.moveWithoutCollision(ball, gadgetTime);
                    this.gadgets[gadgetIndex].interactWithBall(ball);
                    triggeredGadgets=this.gadgets[gadgetIndex].trigger();

                } else {
                    this.moveWithoutCollision(ball, ballTime);
                    this.moveWithoutCollision(this.balls[ballIndex], ballTime);
                    this.makeBallsCollide(ball, this.balls[ballIndex]);
                }
            }
        }
        // If the ball isn't done moving, make sure it keeps moving
        if (ball.getTime() != 0) {
            this.translate(ball, ball.getTime());
        }
    }

    /**
     * Updates the velocities of colliding balls.
     * 
     * @param ball1
     *            A ball that will be colliding with another.
     * @param ball2
     *            A ball that will be colliding with another.
     */
    private void makeBallsCollide(Ball ball1, Ball ball2) {
        Vect pos1 = new Vect(ball1.getPosition().d1, ball1.getPosition().d2);
        Vect pos2 = new Vect(ball2.getPosition().d1, ball2.getPosition().d2);
        Geometry.VectPair newVels = Geometry.reflectBalls(pos1, this.BALLMASS,
                ball1.getVelocity(), pos2, this.BALLMASS, ball2.getVelocity());
        ball1.setVelocity(newVels.v1);
        ball2.setVelocity(newVels.v2);
    }

    /**
     * Translates the ball over a timeDelta in accordance with it's velocity,
     * and updates the balls remaining time.
     * 
     * @param ball
     *            One of the balls traversing the map
     * @param timeDelta
     *            The amount of time in seconds the ball will be moving.
     */
    private void moveWithoutCollision(Ball ball, double timeDelta) {
        DoublePair priorPos = ball.getPosition();
        Vect vel = ball.getVelocity();
        double newX = priorPos.d1 + vel.x() * timeDelta;
        double newY = priorPos.d2 + vel.y() * timeDelta;
        ball.setPosition(new DoublePair(newX, newY));
        ball.setTime(ball.getTime() - timeDelta);
    }

    /**
     * 
     * @param ball
     *            One of the balls traversing the map
     * @param timeDelta
     *            The amount of time in seconds the ball will be moving.
     * @return The index of the Gadget with the least collision time with Ball.
     *         Returns the length of gadgets if none will be collided with in
     *         the timeDelta.
     */
    private int getGadgetWithMinCollisionTime(Ball ball, double timeDelta) {
        double minTime = Double.POSITIVE_INFINITY;
        int index = this.gadgets.length;
        for (int i = 0; i < gadgets.length; i++) {
            double collisionTime = gadgets[i].getMinCollisionTime(ball);
            if (collisionTime < minTime) {
                minTime = collisionTime;
                index = i;
            }
        }
        if (minTime > timeDelta) {
            return this.gadgets.length;
        } else {
            return index;
        }
    }

    /**
     * 
     * @param ball
     *            One of the Balls traversing the map
     * @param timeDelta
     *            The amount of time in seconds the ball will be moving.
     * @return The index of the Ball with the least collision time with ball.
     *         Returns balls.length if none will be collided with in the
     *         timeDelta.
     */
    private int getBallWithMinCollisionTime(Ball ball, double timeDelta) {
        double minTime = Double.POSITIVE_INFINITY;
        int index = this.balls.length;
        for (int i = 0; i < balls.length; i++) {
            // This the other ball is not ball.
            Ball other = balls[i];
            if (!other.getPosition().equals(ball.getPosition())) {
                double collisionTime = Geometry.timeUntilBallBallCollision(
                        ball.getCircle(), ball.getVelocity(),
                        other.getCircle(), other.getVelocity());
                if (collisionTime < minTime) {
                    minTime = collisionTime;
                    index = i;
                }
            }
        }
        if (minTime > timeDelta) {
            return this.balls.length;
        } else {
            return index;
        }
    }

    /**
     * Takes in a ball and discretely updates its velocity in accordance with
     * the acceleration caused by friction and gravity unless the ball is held
     * (for example by the absorber).
     * 
     * @param ball
     *            One of the Balls traversing the map
     * @param timeDelta
     *            The amount of time in seconds the ball will be moving.
     */
    private void updateVelWithAccel(Ball ball, double timeDelta) {
        if (!ball.isHeld()) {
            // Updating with Friction
            double magnitude = ball.getVelocity().length();
            magnitude = magnitude
                    * (1 - this.MU * timeDelta - this.MU2 * magnitude
                            * timeDelta);
            Vect intermediateVel = new Vect(ball.getVelocity().angle(),
                    magnitude);
            // Updating using Gravity
            Vect withGrav = new Vect(intermediateVel.x(), intermediateVel.y()
                    + this.GRAVITY * timeDelta);
            ball.setVelocity(withGrav);
        }
    }

    /**
     * Updates the board to account for the balls moving and gadgets possibly
     * changing.
     * 
     * @param timeDelta
     *            The amount of time in seconds the balls will each be moving.
     */
    private void updateBoard(double timeDelta) {
        // Initialize timeDeltas for all gadgets
        for (Gadget gadget:gadgets){
            gadget.setTime(timeDelta);
        }
        // Initialize timeDeltas for all balls
        for (Ball ball : this.balls) {
            ball.setTime(timeDelta);
        }
        // Translate all balls
        for (Ball ball : this.balls) {
            double ballTime = ball.getTime();
            if (ballTime != 0) {
                this.translate(ball, ballTime);
            }
        }
        // Update all of the balls' velocities to account for acceleration.
        for (Ball ball : this.balls) {
            this.updateVelWithAccel(ball, timeDelta);
        }
        checkRep();
    }

    /**
     * 
     * @return A String representation of the board
     */
    @Override
    public String toString() {
        //The + 2 is to account for drawing the walls.
        String[][] board = new String[this.HEIGHT+2][this.WIDTH+2];
        for (int i=0; i<board.length;i++){
            for (int j=0; j<board[i].length;j++){
                if (i==0||j==0||i==board.length-1||j==board[i].length-1){
                    board[j][i]=".";
                }
                else{
                    boolean isOccupied=false;
                    for (Gadget gadget:gadgets){
                        if (gadget.isOccupying(j-1, i-1)){
                            board[j][i]=gadget.toString();
                            isOccupied=true;
                        }   
                    }
                    for (Ball ball:balls){
                        if ((int)ball.getPosition().d1==i-1&&(int)ball.getPosition().d2==j-1){
                            board[j][i]="*";
                            isOccupied=true;
                        }
                    }
                    if (!isOccupied){
                        board[j][i]=" ";
                    }
                }
            }
        }
        StringBuilder string = new StringBuilder();
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[i].length;j++){
                string.append(board[i][j]);
            }
            string.append("\n");
        }
        return string.toString();
        
    }
}
