package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 * The class solves the maze inputed by the user.
 *
 * @author Hang Lin (lin1412)
 * @version 2011.10.28
 */
public class MazeSolver
{
    /**
     * Whether the solve() method has been called or not.
     */
    public boolean            solveCalled;
    /**
     * whether the maze is solvable or not.
     */
    public boolean            solvable;

    /**
     * the stack holding the coordinates of the solution if it has any.
     */
    public LinkedStack<Coord> stack;


    // ----------------------------------------------------------
    /**
     * Create a new MazeSolver object.
     */
    public MazeSolver()
    {
        solveCalled = false;
        solvable = false;
        stack = new LinkedStack<Coord>();

    }


    // ----------------------------------------------------------
    /**
     * Test wither if the maze has a solution or not.
     *
     * @param maze
     *            the Maze that needed to be solved.
     * @return true is the maze is solvable, false otherwise.
     */
    public boolean solve(Maze maze)
    {
        solveCalled = true;
        maze.resetPaths();
        stack.push(new Coord(0, 0));
        maze.setCell(0, 0, MazeCell.CURRENT_PATH);

        while (!stack.isEmpty())
        {
            int currentX = stack.top().getRow();
            int currentY = stack.top().getColumn();

            if (stack.top().getRow() == maze.size() - 1
                && stack.top().getColumn() == maze.size() - 1)
            {
                solvable = true;
                return true;
            }

            // if right side is unexplored, go right
            else if (maze.getCell(currentX, currentY + 1)
                == MazeCell.UNEXPLORED)
            {
                currentY++;
                maze.setCell(currentX, currentY, MazeCell.CURRENT_PATH);
                stack.push(new Coord(currentX, currentY));
            }

            // if bottom is unexplored, go down
            else if (maze.getCell(currentX + 1, currentY)
                == MazeCell.UNEXPLORED)
            {
                currentX++;
                maze.setCell(currentX, currentY, MazeCell.CURRENT_PATH);
                stack.push(new Coord(currentX, currentY));
            }

            // if left side is unexplored, go left
            else if (maze.getCell(currentX, currentY - 1)
                == MazeCell.UNEXPLORED)
            {
                currentY--;
                maze.setCell(currentX, currentY, MazeCell.CURRENT_PATH);
                stack.push(new Coord(currentX, currentY));
            }

            // if top is unexplored, go up
            else if (maze.getCell(currentX - 1, currentY)
                == MazeCell.UNEXPLORED)
            {
                currentX--;
                maze.setCell(currentX, currentY, MazeCell.CURRENT_PATH);
                stack.push(new Coord(currentX, currentY));
            }

            // if no neighbor are unexplored, set cell as failed path.
            else
            {
                maze.setCell(currentX, currentY, MazeCell.FAILED_PATH);
                stack.pop();
            }

        }
        solvable = false;
        return false;
    }


    // ----------------------------------------------------------
    /**
     * gets the solution for the maze.
     *
     * @return the solution for the maze.
     */
    public String getSolution()
    {
        if (solvable && solveCalled)
        {

            LinkedStack<Coord> solution = new LinkedStack<Coord>();
            String str = "";

            // puts the coordinates of the solution in order.
            while (!stack.isEmpty())
            {
                solution.push(stack.top());
                stack.pop();
            }

            // adds the coordinates from the solution stack into a string.
            str = solution.top().toString();
            solution.pop();
            while (!solution.isEmpty())
            {
                str += " " + solution.top().toString();
                solution.pop();
            }
            return str;
        }
        return null;

    }

}
