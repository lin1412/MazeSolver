package cs2114.mazesolver;

import java.util.EmptyStackException;
import cs2114.link.Link;

// -------------------------------------------------------------------------
/**
 * A stack that would hold the data with the location of the cells in the maze.
 *
 * @author Hang Lin (lin1412)
 * @version 2011.10.28
 * @param <Item>
 *            the type of data the stack would hold.
 */
public class LinkedStack<Item>
    implements SimpleStack<Item>
{
    /**
     * the number of data the stack contains.
     */
    public int        size;
    /**
     * the pointer for the top of the stack.
     */
    public Link<Item> head;
    /**
     * the pointer for the bottom of the stack.
     */
    public Link<Item> tail;


    // ----------------------------------------------------------
    /**
     * Create a new LinkedStack object.
     */
    public LinkedStack()
    {
        size = 0;
        head = new Link<Item>(null);
        tail = new Link<Item>(null);
        head.join(tail);
    }


    /**
     * find out if the stack contain any data.
     *
     * @return false if the size is 0, true otherwise.
     */
    public boolean isEmpty()
    {
        if (size == 0)
        {
            return true;
        }
        return false;
    }


    /**
     * removes the item at top of the stack.
     */
    public void pop()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }

        Link<Item> temp = head.next();
        Link<Item> other = temp.split();
        head.split();
        head.join(other);
        size--;

    }


    /**
     * insert an item to the top of the stack.
     *
     * @param data
     *            the data to be added into
     */
    public void push(Item data)
    {
        Link<Item> newLink = new Link<Item>(data);
        Link<Item> temp = head.split();
        head.join(newLink);
        newLink.join(temp);
        size++;
    }


    /**
     * gets the size of the stack.
     *
     * @return the size of the stack.
     */
    public int size()
    {
        return size;
    }


    /**
     * gets the data from the top of the stack.
     *
     * @return the data at the top of the stack.
     */
    public Item top()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }
        return head.next().data();
    }

}
