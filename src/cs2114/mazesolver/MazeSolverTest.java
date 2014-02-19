package cs2114.mazesolver;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the MazeSolver class.
 *
 * @author Hang
 * @version 2011.11.1
 */
public class MazeSolverTest
    extends TestCase
{
    private Maze       maze;
    private MazeSolver solver;


    // ----------------------------------------------------------
    /**
     * Create a new MazeSolverTest object.
     */
    public MazeSolverTest()
    {
        solver = new MazeSolver();
        maze = new Maze(6);
    }


    // ----------------------------------------------------------
    /**
     * test the solve() method with a unsolvable and solvable maze.
     */
    public void testSolve()
    {
        maze.loadMazeState("   * *",
                           "* *   ",
                           "  * * ",
                           "******",
                           "    * ",
                           " **** ");
        assertFalse(solver.solve(maze));
        maze.loadMazeState("   * *",
                           "* *   ",
                           "  * * ",
                           " ** * ",
                           "    * ",
                           " **** ");
        assertTrue(solver.solve(maze));
        maze.loadMazeState("      ",
                           "      ",
                           "      ",
                           "      ",
                           "      ",
                           "      ");
        assertTrue(solver.solve(maze));

    }


    // ----------------------------------------------------------
    /**
     * test the getSolution method by testing the string of coordinate solution
     * of a known maze.
     */
    public void testSolution()
    {
        maze.loadMazeState("   * *",
                           "* *   ",
                           "  * * ",
                           "******",
                           "    * ",
                           " **** ");
        solver.solve(maze);
        assertNull(solver.getSolution());

        maze.clearMaze();
        maze.loadMazeState("   * *",
                           "* *   ",
                           "  * * ",
                           " ** * ",
                           "    * ",
                           " **** ");
        solver.solve(maze);
        assertEquals(solver.getSolution(),
            "(0, 0) (0, 1) (1, 1) (2, 1) (2, 0) (3, 0) (4, 0) (4, 1) (4, 2) " +
            "(4, 3) (3, 3) (2, 3) (1, 3) (1, 4) (1, 5) (2, 5) (3, 5) (4, 5) " +
            "(5, 5)");

    }

}
