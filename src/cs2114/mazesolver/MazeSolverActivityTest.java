package cs2114.mazesolver;

import android.widget.CheckBox;
import android.widget.Button;
import android.widget.TextView;

// -------------------------------------------------------------------------
/**
 * Test the MazeSolverActivity class in a android emulator.
 *
 * @author Hang
 * @version Nov 2, 2011
 */
public class MazeSolverActivityTest
    extends student.AndroidTestCase<MazeSolverActivity>
{

    private MazeView mazeView;
    private Button   clear;
    private Button   solve;
    private CheckBox checkBox;
    private TextView statusLabel;
    private float    squareSize;


    // ----------------------------------------------------------
    /**
     * Create a new MazeSolverActivityTest object.
     */
    public MazeSolverActivityTest()
    {
        super(MazeSolverActivity.class);

    }


    /**
     * set up the GUI of the emulator.
     */
    public void setUp()
    {
        mazeView = getView(MazeView.class, R.id.mazeView);
        clear = getView(Button.class, R.id.clearMaze);
        solve = getView(Button.class, R.id.solveMaze);
        checkBox = getView(CheckBox.class, R.id.drawEraseMode);
        statusLabel = getView(TextView.class, R.id.statusLabel);
        getActivity().getMaze().clearMaze();
        squareSize = mazeView.getWidth() / getActivity().getMaze().size();

    }


    // ----------------------------------------------------------
    /**
     * test the draws wall and erase wall functions.
     */
    public void testDrawWall()
    {
        setUp();
        // when the checkBox is selected.
        click(checkBox);
        // click on (1, 1)
        click(mazeView, (float)(squareSize * 1.5), (float)(squareSize * 1.5));

        assertEquals(getActivity().getMaze().getCell(1, 1), MazeCell.WALL);

        click(checkBox);
        // click on (2, 3), nothing happens
        click(mazeView, (float)(squareSize * 2.5), (float)(squareSize * 3.5));
        assertEquals(getActivity().getMaze().getCell(2, 3),
            MazeCell.UNEXPLORED);

        // click on the wall in (1,1)
        click(mazeView, (float)(squareSize * 1.5), (float)(squareSize * 1.5));
        assertEquals(getActivity().getMaze().getCell(1, 1),
            MazeCell.UNEXPLORED);
    }


    // ----------------------------------------------------------
    /**
     * test the clear button.
     */
    public void testClearButton()
    {
        setUp();
        click(checkBox);
        click(mazeView, (float)(squareSize * 1.5), (float)(squareSize * 1.5));
        click(mazeView, (float)(squareSize * 2.5), (float)(squareSize * 1.5));
        click(mazeView, (float)(squareSize * 3.5), (float)(squareSize * 1.5));
        click(mazeView, (float)(squareSize * 4.5), (float)(squareSize * 1.5));

        click(clear);
        assertEquals(getActivity().getMaze().getCell(1, 1),
            MazeCell.UNEXPLORED);
        assertEquals(getActivity().getMaze().getCell(4, 1),
            MazeCell.UNEXPLORED);

    }


    // ----------------------------------------------------------
    /**
     * test the solve button.
     */
    public void testSolveButton()
    {
        setUp();
        click(checkBox);
        click(mazeView, (float)(squareSize * .5), (float)(squareSize * 3.5));
        click(mazeView, (float)(squareSize * 1.5), (float)(squareSize * 3.5));
        click(mazeView, (float)(squareSize * 2.5), (float)(squareSize * 3.5));
        click(mazeView, (float)(squareSize * 3.5), (float)(squareSize * 3.5));
        click(mazeView, (float)(squareSize * 4.5), (float)(squareSize * 3.5));
        click(mazeView, (float)(squareSize * 5.5), (float)(squareSize * 3.5));
        click(mazeView, (float)(squareSize * 6.5), (float)(squareSize * 3.5));
        click(solve);
        assertEquals("No Solution", statusLabel.getText());

        click(mazeView, (float)(squareSize * 6.5), (float)(squareSize * 5.5));
        click(mazeView, (float)(squareSize * 6.5), (float)(squareSize * 4.5));
        click(checkBox);
        click(mazeView, (float)(squareSize * 6.5), (float)(squareSize * 3.5));
        click(mazeView, (float)(squareSize * 5.5), (float)(squareSize * 3.5));
        click(solve);
        assertEquals(
            "(0, 0) (0, 1) (0, 2) (0, 3) (0, 4) (0, 5) (0, 6) (1, 6) "
                + "(2, 6) (3, 6) (3, 5) (4, 5) (5, 5) (6, 5) (6, 6)",
            statusLabel.getText());
    }


    // ----------------------------------------------------------
    /**
     * Test the action move functions.
     */
    public void testDrag()
    {
        setUp();
        click(checkBox);
        drag(
            mazeView,
            (float)(squareSize * 4.5),
            (float)(squareSize * .5),
            (float)(squareSize * 4.5),
            (float)(squareSize * 6.5));
        click(checkBox);
        drag(
            mazeView,
            (float)(squareSize * 4.5),
            (float)(squareSize * .5),
            (float)(squareSize * 4.5),
            (float)(squareSize * 6.5));

        assertEquals(getActivity().getMaze().getCell(4, 2),
            MazeCell.UNEXPLORED);

    }

}
