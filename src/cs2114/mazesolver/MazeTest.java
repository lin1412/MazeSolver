package cs2114.mazesolver;

import student.TestCase;


// -------------------------------------------------------------------------
/**
 * This class tests the methods of the Maze class.
 *
 * @author Hang
 * @version 2011.11.1
 */
public class MazeTest
    extends TestCase
{
    private Maze maze = new Maze(4);

    // ----------------------------------------------------------
    /**
     * Create a new MazeTest object.
     */
    public MazeTest()
    {
        //does not do anything.
    }

    // ----------------------------------------------------------
    /**
     * compares the maze in question with what we expected it to be.
     *
     * @param theMaze
     *            the maze in question.
     * @param expected
     *            what we expect the maze looks like.
     */
    public void assertMaze(Maze theMaze, String... expected)
    {
        Maze expectedMaze = new Maze(expected.length);
        expectedMaze.loadMazeState(expected);
        assertEquals(expectedMaze, theMaze);

    }


    // ----------------------------------------------------------
    /**
     * test the clear method after changing one of the cells.
     */
    public void testClear()
    {
        maze.loadMazeState("xx**", "*xx*", " .**", "*   ");
        maze.clearMaze();
        assertMaze(maze, "    ", "    ", "    ", "    ");
    }

    // ----------------------------------------------------------
    /**
     * test the setCell() methods.
     */
    public void testSetCell()
    {
        maze.loadMazeState("  **", "*  *", "  **", "*   ");

        maze.setCell(0, 0, MazeCell.CURRENT_PATH);
        assertMaze(maze, ". **", "*  *", "  **", "*   ");

        //nothing happens since the end can't be set as wall.
        maze.setCell(3, 3, MazeCell.WALL);
        assertMaze(maze, ". **", "*  *", "  **", "*   ");

    }

    /**
     * test the getCell() method.
     */
    public void testGetCell()
    {
        maze.loadMazeState("  **", "*  *", "  **", "*   ");
        assertEquals(maze.getCell(0, 2), MazeCell.WALL);
        assertNull(maze.getCell(-1, 0));
    }

    // ----------------------------------------------------------
    /**
     * test the resetPaths() method.
     */
    public void testReset()
    {
        maze.loadMazeState("xx**", "*xx*", " .**", "*   ");
        maze.resetPaths();
        assertMaze(maze, "  **", "*  *", "  **", "*   ");
    }

    // ----------------------------------------------------------
    /**
     * tests the size() method.
     */
    public void testSize()
    {
        maze.loadMazeState("xx**", "*xx*", " .**", "*   ");
        assertEquals(maze.size(), 4);
    }

}
