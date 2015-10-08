package github.project;

/**
 * This class contains an ordered pair (x,y).
 *
 * @author Jeff Niu
 */
public class Coordinate {

    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate() {
        this(0, 0);
    }

}
