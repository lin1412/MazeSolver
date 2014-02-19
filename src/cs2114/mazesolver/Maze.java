package cs2114.mazesolver;


// -------------------------------------------------------------------------
/**
 *  The maze with each cell containing a status about itself.
 *
 *  @author  Hang Lin (lin1412)
 *  @version 2011.10.25
 */
public class Maze extends MazeBase
{
    /**
     *  An double array containing each cell's status.
     */
    public MazeCell [][] grid;
    /**
     * the size of the maze.
     */
    public int size;
    // ----------------------------------------------------------
    /**
     * Create a new Maze object.
     * @param length the size of the maze.
     */
    public Maze(int length)
    {
        size = length;
        grid = new MazeCell[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                grid[i][j] = MazeCell.UNEXPLORED;
            }
        }

    }

    /**
     * clear the maze by setting everything to UNEXPLORED.
     */
    public void clearMaze()
    {
        for (int i = 0; i < size(); i++)
        {
            for (int j = 0; j < size(); j++)
            {
                grid[i][j] = MazeCell.UNEXPLORED;
            }
        }

        setChanged();
        notifyObservers();
    }

    /**
     * get the cell's status
     * @param row the row of the intended cell.
     * @param col the column of the intended cell.
     * @return the status of the cell.
     */
    public MazeCell getCell(int row, int col)
    {
        if ( row >= 0 && row < size() && col >= 0 && col < size())
        {
            return grid[row][col];
        }
        return null;
    }


    /**
     * set all CURRENT_PATH and FAILED_PATH to UNEXPLORED
     */
    public void resetPaths()
    {
        for (int i = 0; i < size(); i++)
        {
            for (int j = 0; j < size(); j++)
            {
                if ( grid[i][j] == MazeCell.CURRENT_PATH ||
                    grid[i][j] == MazeCell.FAILED_PATH)
                {
                    grid[i][j] = MazeCell.UNEXPLORED;
                }
            }
        }
        setChanged();
        notifyObservers();
    }

    /**
     * @param row the row of the intended cell.
     * @param col the column of the intended cell.
     * @param status the new status for the cell.
     */
    public void setCell(int row, int col, MazeCell status)
    {
        if ( row == 0 && col == 0 && status == MazeCell.WALL ||
            row == size() - 1 && col == size() - 1 && status == MazeCell.WALL)
        {
            return;
        }
        grid[row][col] = status;
        setChanged();
        notifyObservers();

    }

    /**
     * get the size of the maze.
     * @return the size of the maze.
     */
    public int size()
    {
        return size;
    }

}
