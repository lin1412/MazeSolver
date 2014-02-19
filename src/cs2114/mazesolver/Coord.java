package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 *  This class contains the coordinates of the maze.
 *
 *  @author  Hang Lin (lin1412)
 *  @version 2011.10.29
 */
public class Coord
{
    /**
     * the row of the coordinate.
     */
    public int row;
    /**
     * the column of the coordinate.
     */
    public int col;
    // ----------------------------------------------------------
    /**
     * Create a new Coordinates object.
     * @param x the row of the maze.
     * @param y the column of the maze.
     */
    public Coord ( int x, int y)
    {
        row = x;
        col = y;
    }

    // ----------------------------------------------------------
    /**
     * gets the row of the coordinate.
     * @return the row of the coordinate.
     */
    public int getRow()
    {
        return row;
    }

    // ----------------------------------------------------------
    /**
     * the column of the coordinate.
     * @return the column of the coordinate.
     */
    public int getColumn()
    {
        return col;
    }

    /**
     * gets both the coordinates.
     * @return the string representing the coordinates.
     */
    public String toString()
    {
        return "(" + getRow() + ", " + getColumn() + ")";
    }
}
