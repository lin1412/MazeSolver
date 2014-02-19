package cs2114.mazesolver;

import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Color;
import android.view.MotionEvent;
import java.util.Observable;
import java.util.Observer;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.content.Context;
import android.view.View;

// -------------------------------------------------------------------------
/**
 * The GUI for the maze solver.
 *
 * @author Hang Lin (lin1412)
 * @version 2011.10.30
 */
public class MazeView
    extends View
{
    private Maze    maze = new Maze(7);
    private boolean drawWall = false;


    // ----------------------------------------------------------
    /**
     * Create a new MazeView object.
     *
     * @param context
     *            the Context that the view is running in
     * @param attrs
     *            the attributes of the XML tag that is inflating the view
     */
    public MazeView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }


    // ----------------------------------------------------------
    /**
     * Overridden to force the view to be square (have the same width and
     * height).
     *
     * @param widthMeasureSpec
     *            the desired width as determined by the layout
     * @param heightMeasureSpec
     *            the desired height as determined by the layout
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // Choose the smallest of the two dimensions to use for both.
        int measureSpec = Math.min(widthMeasureSpec, heightMeasureSpec);

        // Call the superclass implementation but pass it our modified width
        // and height instead of the incoming ones.
        super.onMeasure(measureSpec, measureSpec);
    }


    // ----------------------------------------------------------
    /**
     * Sets the maze that will be rendered by this view. Calling this method
     * also adds the view as an observer for the maze.
     *
     * @param m
     *            the maze thats going to be used.
     */
    public void setMaze(Maze m)
    {
        this.maze = m;
        maze.addObserver(new MazeObserver());
    }


    // ----------------------------------------------------------
    /**
     * Draw the grid lines.
     *
     * @param canvas
     *            the view where the grid will be drawn.
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        // Grid-lines = BLUE
        // UNEXPLORED = BLACK
        // WALL = WHITE
        // CURRENT_PATH = GREEN
        // FAILED_PATH = RED

        Paint outline = new Paint();
        outline.setColor(Color.CYAN);
        outline.setStyle(Style.STROKE);

        Paint unex = new Paint();
        unex.setColor(Color.BLACK);
        unex.setStyle(Style.FILL);

        Paint wall = new Paint();
        wall.setColor(Color.WHITE);
        wall.setStyle(Style.FILL);

        Paint current = new Paint();
        current.setColor(Color.GREEN);
        current.setStyle(Style.FILL);

        Paint failed = new Paint();
        failed.setColor(Color.RED);
        failed.setStyle(Style.FILL);

        float squareSize = getWidth() / maze.size();
        // these does not reset each row.
        float startY = 0;
        float endY = squareSize;

        for (int i = 0; i < maze.size(); i++)
        {
            // these reset for each row.
            float startX = 0;
            float endX = squareSize;

            for (int j = 0; j < maze.size(); j++)
            {
                if (maze.getCell(i, j).equals(MazeCell.UNEXPLORED))
                {
                    canvas.drawRect(startX, startY, endX, endY, unex);
                }
                else if (maze.getCell(i, j).equals(MazeCell.WALL))
                {
                    canvas.drawRect(startX, startY, endX, endY, wall);
                }
                else if (maze.getCell(i, j).equals(MazeCell.CURRENT_PATH))
                {
                    canvas.drawRect(startX, startY, endX, endY, current);
                }
                else
                {
                    canvas.drawRect(startX, startY, endX, endY, failed);
                }
                canvas.drawRect(startX, startY, endX, endY, outline);
                startX += squareSize;
                endX += squareSize;
            }
            // next row's dimension
            startY += squareSize;
            endY += squareSize;
        }

    }


    // ----------------------------------------------------------
    /**
     * Called when a touch event occurs on the view; either pressing the finger
     * down for the first time, moving it on the screen, or lifting it back up.
     *
     * @param e
     *            a MotionEvent object that describes the touch event.
     * @return true if this method handled the touch, or false if it did not.
     */
    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        maze.resetPaths();
        float squareSize = getWidth() / maze.size();

        if (e.getAction() == MotionEvent.ACTION_DOWN)
        {
            if (drawWall)
            {
                maze.setCell(
                    (int)(e.getY() / squareSize),
                    (int)(e.getX() / squareSize),
                    MazeCell.WALL);
                return true;
            }
            maze.setCell(
                (int)(e.getY() / squareSize),
                (int)(e.getX() / squareSize),
                MazeCell.UNEXPLORED);
            return true;
        }
        else if (e.getAction() == MotionEvent.ACTION_MOVE)
        {
            if (drawWall)
            {
                maze.setCell(
                    (int)(e.getY() / squareSize),
                    (int)(e.getX() / squareSize),
                    MazeCell.WALL);
                return true;
            }
            maze.setCell(
                (int)(e.getY() / squareSize),
                (int)(e.getX() / squareSize),
                MazeCell.UNEXPLORED);
            return true;
        }
        else
        {
            // For any other touch event, ignore it.
            return false;
        }
    }


    // ----------------------------------------------------------
    /**
     * see when to draw wall and when to erase wall.
     *
     * @param yes
     *            the boolean entered.
     * @return whether to draw wall or erase wall.
     */
    public boolean drawWall(boolean yes)
    {
        if (yes)
        {
            drawWall = true;
            return true;
        }
        drawWall = false;
        return false;
    }


    // ----------------------------------------------------------
    /**
     * An observer that listens for changes made to the Maze. This is a nested
     * class inside the view so that it can still access methods that belong to
     * the surrounding view.
     */
    private class MazeObserver
        implements Observer
    {
        // ~ Methods ...........................................................

        // ----------------------------------------------------------
        /**
         * Called when the maze is changed (for example, when the user drags a
         * finger around the view).
         *
         * @param observable
         *            the Observable object that was changed
         * @param data
         *            extra data about the notification; unused here
         */
        public void update(Observable observable, Object data)
        {
            // The invalidate() method is used to force a view to be repainted
            // at the earliest opportunity (which in most cases is essentially
            // immediately, but may not always be). Note that this is a method
            // on the View class, not the Observer.

            invalidate();
        }
    }
}
