package sandbox;

import java.util.Objects;

/**
 * A point representing a location on a matrix (x, y)
 * 
 * @author Wes McKean
 */
public class Point {
    private int x;
    private int y;
    
    /**
     * Default constructor.  Creates a point a (0,0)
     */
    public Point() {
    }
    
    /**
     * Constructs a point with the given x, y coordinates
     * 
     * @param x location on the x-axis
     * @param y location on the y-axis
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter for the x coordinate
     * 
     * @return the location of this point on the x-axis
     */
    public int getX() {
        return x;
    }

    /**
     * setter for the x coordinate
     * 
     * @param x sets the location of this point on the x-axis
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * getter for the y coordinate
     * 
     * @return the location of this point on the y-axis
     */
    public int getY() {
        return y;
    }

    /**
     * setter for the y coordinate
     * 
     * @param y  set the location of this point on the y-axis
     */
    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public boolean equals(Object p) {
        if(p instanceof Point pt) {
            return pt.getX() == x && pt.getY() == y;
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(').append(x).append(',').append(y).append(')');
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
    
    /**
     * Checks to see if this point lies on a line defined by the starting
     * and ending points supplied as parameters.  The line could be along
     * the x or y axis.  The line has to be along an axis.  Either (x)
     * or (y) in p1 and p2 would need to be equal.
     * 
     * @param p1 the starting point of the line
     * @param p2 the ending point of the line
     * @return  true if this <code>Point</code> lays on the line represented
     * by p1 and p2. otherwise, false.
     */
    public boolean isOnLine(Point p1, Point p2) {
        boolean result = false;
        
        if(!Objects.isNull(p1) && !Objects.isNull(p2)) {
            if((p1.getY() == p2.getY() && p1.getY() == getY())) {
                if(p1.getX() < p2.getX()) {
                    result = (getX() >= p1.getX() && getX() <= p2.getX());
                }
                else {                 
                    result = (getX() >= p2.getX() && getX() <= p1.getX());
                }
            }
            else if(p1.getX() == p2.getX() && p1.getX() == getX()) {
                if(p1.getY() < p2.getY()) {
                    result = (getY() >= p1.getY() && getY() <= p2.getY());
                }
                else {
                    result = (getY() >= p2.getY() && getY() <= p1.getY());
                }
            }
        }
        
        return result;
    }
}
