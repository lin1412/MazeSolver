package cs2114.mazesolver;

import java.util.EmptyStackException;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This class tests the methods of the LinkedStack class that holds the Coord
 * class.
 *
 * @author Hang
 * @version 2011.11.1
 */
public class LinkedStackTest
    extends TestCase
{

    private LinkedStack<Coord> stack;
    private Coord              coord1;
    private Coord              coord2;
    private Coord              coord3;


    // ----------------------------------------------------------
    /**
     * Create a new LinkedStackTest object, and a few Coord objects.
     */
    public LinkedStackTest()
    {
        coord1 = new Coord(0, 0);
        coord2 = new Coord(2, 4);
        coord3 = new Coord(4, 5);
        stack = new LinkedStack<Coord>();
    }


    // ----------------------------------------------------------
    /**
     * test if the stack is empty.
     */
    public void testIsEmpty()
    {
        assertTrue(stack.isEmpty());
        stack.push(coord1);
        assertFalse(stack.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * test if the pop() methods.
     */
    public void testPop()
    {
        Exception occurred = null;
        try
        {
            stack.pop();
        }
        catch (EmptyStackException exception)
        {
            occurred = exception;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof EmptyStackException);

        stack.push(coord1);
        stack.push(coord2);
        stack.pop();
        assertEquals(coord1, stack.top());
    }


    // ----------------------------------------------------------
    /**
     * test the push() and size() method.
     */
    public void testPushAndSize()
    {
        stack.push(coord1);
        stack.push(coord2);
        stack.push(coord3);
        assertEquals(stack.size(), 3);
    }


    // ----------------------------------------------------------
    /**
     * test the top() method.
     */
    public void testTop()
    {
        Exception occurred = null;
        try
        {
            stack.top();
        }
        catch (EmptyStackException exception)
        {
            occurred = exception;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof EmptyStackException);

        stack.push(coord1);
        stack.push(coord3);
        stack.push(coord2);
        assertEquals(stack.top(), coord2);
    }


    // ----------------------------------------------------------
    /**
     * test the getRow and getColumn method of the Coord class.
     */
    public void testGetRowAndColumn()
    {
        assertEquals(coord1.getRow(), 0);
        assertEquals(coord2.getColumn(), 4);
    }


    // ----------------------------------------------------------
    /**
     * test the toString method of the Coord class.
     */
    public void testToString()
    {
        String str = "";
        stack.push(coord1);
        stack.push(coord2);
        while (!stack.isEmpty())
        {
            str += stack.top().toString();
            stack.pop();
        }
        assertEquals(str, "(2, 4)(0, 0)");
    }

}
