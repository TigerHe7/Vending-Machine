package github.project;

/**
 * This class contains an ordered pair (x,y).
 *
 * @author Jeff Niu
 */
@SuppressWarnings("PublicField")
public class Coordinate {

    /**
     * The x coordinate.
     */
    public int x;
    /**
     * The y coordinate.
     */
    public int y;

    /**
     * Create a coordinate at a specific x and y point.
     *
     * @param x
     * @param y
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Create an empty coordinate.
     */
    public Coordinate() {
        this(0, 0);
    }

}
