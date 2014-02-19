package cs2114.mazesolver;

import android.view.View;
import android.widget.CheckBox;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

// -------------------------------------------------------------------------
/**
 * The Maze Solver application. Where the user draws a maze and it tries to
 * solves it, or returning "unsolvable" if it does not have a solution.
 *
 * @author Hang Lin (lin1412)
 * @version 2011.10.25
 */
public class MazeSolverActivity
    extends Activity
{
    // ~ Instance/static variables .............................................
    private Maze       maze;
    private MazeSolver solver;
    private MazeView   mazeView;
    private TextView   statusLabel;
    private CheckBox   checkBox;


    // ~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState
     *            previous state saved by the last run of the activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        // create a status bar, and enter the message.
        statusLabel = (TextView)findViewById(R.id.statusLabel);
        statusLabel.setText("Please create maze");

        // create a check box for draw or erasing walls.
        checkBox = (CheckBox)findViewById(R.id.drawEraseMode);

        // create a GUI for the maze.
        mazeView = (MazeView)findViewById(R.id.mazeView);

        maze = new Maze(7);
        mazeView.setMaze(maze);

        solver = new MazeSolver();
    }


    // ----------------------------------------------------------
    /**
     * The current maze on the view.
     *
     * @return the maze thats in use.
     */
    public Maze getMaze()
    {
        return maze;
    }


    // ----------------------------------------------------------
    /**
     * When the check box is checked, draw walls. When the box is unchecked,
     * erase walls.
     * @param view the GUI of the app.
     */
    public void boxClicked(View view)
    {
        if (checkBox.isChecked())
        {
            mazeView.drawWall(true);
        }
        else
        {
            mazeView.drawWall(false);
        }
    }


    // ----------------------------------------------------------
    /**
     * Clear the maze when the button is clicked.
     *
     * @param view
     *            the GUI of the app.
     */
    public void clearButtonClicked(View view)
    {
        maze.clearMaze();
        statusLabel.setText("Please create maze");
    }


    // ----------------------------------------------------------
    /**
     * solve the maze when the button is clicked.
     *
     * @param view
     *            the GUI of the app.
     */
    public void solveButtonClicked(View view)
    {
        if (solver.solve(maze))
        {
            statusLabel.setText(solver.getSolution());
        }
        else
        {
            statusLabel.setText("No Solution");
        }
    }
}
