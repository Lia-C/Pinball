import physics.*;

/**
 * An immutable class representing a triangle bumper.
 * 
 * Bounding box of size 1Lx1L
 * Default orientation (0 degrees) places corners in the northeast, 
 * northwest, and southwest.
 * Coefficient of reflection: 1.0
 * Triggered when hit by the ball.
 */

public class TriangleBumper implements Gadget {
    private final int xCor, yCor;
    private final double COEFFICIENT_OF_REFLECTION = 1.0;
}
