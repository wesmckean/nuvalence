package sandbox;

import java.util.Optional;

/**
 * A rectangle represents an area in a matrix specified by the upper left
 * corner of the rectangle and the lower right corner of the rectangle.
 * 
 * @author Wes McKean
 */
public class Rectangle {
    /**
     * the upper left coordinate of the rectangle
     */
    private Point upperLeft;
    /**
     * the lower right coordinate of the rectangle
     */
    private Point lowerRight;
    
    /**
     * Default constructor.  creates an empty rectangle
     */
    public Rectangle() {  
        upperLeft = new Point(0,0);
        lowerRight = new Point(0,0);
    }
    
    /**
     * Constructs a rectangle out of the coordinates represented by
     * x, y, x1, and y1.  x and y is the upper left corner.  x1 and
     * y1 is the lower right corner.
     * 
     * required:
     *   x &lt; x1
     *   y &lt; y1
     * 
     * @param x  the coordinate of the upper left corner on the x-axis
     * @param y  the coordinate of the upper left corner on the y-axis
     * @param x1 the coordinate of the lower right corner on the x-axis
     * @param y1 the coordinate of the lower right corner on the y-axis
     *
     * @throws IllegalArgumentException if any of the coordinates are 
     * outside the required bounds.
     */
    public Rectangle(int x, int y, int x1, int y1) {
        this(new Point(x, y), new Point(x1, y1));
    }
    
    /**
     * Constructs a rectangle out of the two coordinates specified
     * by the <code>Point</code>s upperLeft and lowerRight.
     * 
     * @param upperLeft  the upper left corner of the rectangle
     * @param lowerRight the lower right corner of the rectangle
     */
    public Rectangle(Point upperLeft, Point lowerRight) {
        if(upperLeft == null) {
            throw new IllegalArgumentException("upperLeft may not be null");
        }
        
        if(lowerRight == null) {
            throw new IllegalArgumentException("lowerRight may not be null");
        }
        
        if(lowerRight.getX() <= upperLeft.getX()) {
            throw new IllegalArgumentException("lower right coordinates must be greater than upper right coordinates.");
        }

        if(lowerRight.getY() <= upperLeft.getY()) {
            throw new IllegalArgumentException("lower right coordinates must be greater than upper right coordinates.");
        }

        this.upperLeft = upperLeft;
        this.lowerRight = lowerRight;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Rectangle r) {
            return r.upperLeft.equals(upperLeft) && r.lowerRight.equals(lowerRight);
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(')
                .append(upperLeft.getX()).append(',')
                .append(upperLeft.getY()).append(',')
                .append(lowerRight.getX()).append(',')
                .append(lowerRight.getY())
                .append(')');
        
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
    
    /**
     * returns the width of the rectangle
     * 
     * @return the width of the rectangle
     */
    public int getWidth() {
        return lowerRight.getX() - upperLeft.getX() + 1;
    }
    
    /**
     * returns the height of the rectangle.
     * 
     * @return the height of the rectangle
     */
    public int getHeight() {
        return lowerRight.getY() - upperLeft.getY() + 1;
    }
    
    /**
     * getter for the <code>Point</code> representing the upper left corner
     * of the rectangle.
     * 
     * @return the <code>Point</code> of the upper left corner of the
     * rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * setter for the <code>Point</code> that represents the upper left
     * corner of the rectangle.
     * 
     * @param upperLeft the <code>Point</code> representing the upper left corner 
     * of the rectangle.
     * 
     * @throws IllegalArgumentException the upper left parameter may not 
     * be null.
     */
    public void setUpperLeft(Point upperLeft) {
        if(upperLeft == null) {
            throw new IllegalArgumentException("upperLeft may not be null");
        }
        this.upperLeft = upperLeft;
    }

    /**
     * getter for the <code>Point</code> representing the lower right corner of
     * this rectangle
     * 
     * @return the <code>Point</code> of the lower right corner of the rectangle
     */
    public Point getLowerRight() {
        return lowerRight;
    }

    /**
     * setter for the <code>Point</code> that represents the lower right corner
     * of this rectangle.
     * 
     * @param lowerRight the <code>Point</code> representing the lower right
     * corner of the rectangle
     * 
     * @throws IllegalArgumentException the lower right coordinates represented
     * by <code>Point</code> must not be null, and the coordinates must be 
     * greater than the upper left coordinates.
     * 
     */
    public void setLowerRight(Point lowerRight) {
        if(lowerRight == null) {
            throw new IllegalArgumentException("lowerRight may not be null");
        }

        if(lowerRight.getX() <= upperLeft.getX()) {
            throw new IllegalArgumentException("lower right coordinates must be greater than upper right coordinates.");
        }

        if(lowerRight.getY() <= upperLeft.getY()) {
            throw new IllegalArgumentException("lower right coordinates must be greater than upper right coordinates.");
        }
        
        this.lowerRight = lowerRight;
    }    
    
    /**
     * Determines if this rectangle is intersected by another rectangle.
     * 
     * @param rectangle  the <code>Rectangle</code> to test for intersection.
     * @return returns true if the rectangle parameter intersects this rectangle.
     * otherwise it returns false.
     */
    public boolean intersects(Rectangle rectangle) {
        boolean result = true;
        
        if(contains(rectangle) || rectangle.contains(this)) {
            return false;
        }
        
        if(lowerRight.getY() < rectangle.getUpperLeft().getY() || upperLeft.getY() > rectangle.getLowerRight().getY()) {
            result = false;
        }
        
        if (lowerRight.getX() < rectangle.getUpperLeft().getX() || upperLeft.getX() > rectangle.getLowerRight().getX()) {
            return false;
        }    
        
        return result;
    }
    
    /**
     * If two <code>Rectangle</code>s intersect, this method returns a rectangle 
     * representing the area the <code>Rectangle</code>s share in common.
     * 
     * @param rectangle the <code>Rectangle</code> to test for and return the
     * shared common area.  If the rectangles do not intersection, then an
     * empty <code>Optional</code> is returned.
     * 
     * @return an <code>Optional</code> with s <code>Rectangle</code> representing
     * the intersection, if the rectangles intersect, or an empty 
     * <code>Optionals</code> if not.
     */
    public Optional<Rectangle> intersection(Rectangle rectangle) {
        Rectangle result = null;
        
        if(intersects(rectangle)) {
            int leftX   = Math.max( upperLeft.getX(), rectangle.getUpperLeft().getX());
            int rightX  = Math.min( upperLeft.getX() + getWidth(), rectangle.getUpperLeft().getX() + rectangle.getWidth());
            int topY    = Math.max( upperLeft.getY(), rectangle.getUpperLeft().getY() );
            int bottomY = Math.min( upperLeft.getY() + getHeight(), rectangle.getUpperLeft().getY() + rectangle.getHeight());

            result = Rectangle.build( leftX, topY, rightX-leftX, bottomY-topY );            
        }
        
        return Optional.ofNullable(result);
    }
    
    /**
     * Determines if a rectangle is wholly contained or enclosed by this
     * rectangle.
     * 
     * @param rectangle the <code>Rectangle</code> we want to test for
     * containment.
     * 
     * @return true if the passed in <code>Rectangle</code> is contained by
     * this rectangle, false if it is not.
     */
    public boolean contains(Rectangle rectangle) {
        return contains(rectangle.getUpperLeft()) && contains(rectangle.getLowerRight());
    }
    
    /**
     * Determines if a <code>Point</code> falls within the area of this rectangle.
     * 
     * @param pt the <code>Point</code> (coordinate) we want to use to determine 
     * if it lies within the area of this rectangle.
     * 
     * @return true if the <code>Point</code> is within  the area of this
     * rectangle, otherwise, false.
     */
    public boolean contains(Point pt) {
        return pt.getX() >= upperLeft.getX() && pt.getX() <= lowerRight.getX() &&
                pt.getY() >= upperLeft.getY() && pt.getY() <= lowerRight.getY();
    }
    
    /**
     * Determines if another <code>Rectangle</code> is adjacent to this rectangle.
     * Adjacent meaning any side of the rectangle shares the same line
     * as this rectangle, either in part or in full.
     * 
     * for example:
     * 
     * ....................
     * .AAABBB.............
     * .A.B..B.............
     * .A.B..B.............
     * .AAABBB.............
     * ....................
     * ....................
     * 
     * <code>Rectangle</code> A shares its right side with <code>Rectangle</code>
     * B's left side.
     * 
     * @param rectangle the <code>Rectangle</code> to test to see if it shares
     * a side with this <code>Rectangle</code>.
     * 
     * @return true if this <code>Rectangle</code> shares a side with the passed
     * in <code>Rectangle</code>, false if it does not.
     * 
     */
    public boolean adjacent(Rectangle rectangle) {
        boolean result = false;
        
        Point ul1 = upperLeft;
        Point ul2 = rectangle.getUpperLeft();
        Point ur1 = new Point(lowerRight.getX(), ul1.getY());
        Point ur2 = new Point(rectangle.getLowerRight().getX(), ul2.getY());
        Point lr1 = lowerRight;
        Point lr2 = rectangle.getLowerRight();
        Point ll1 = new Point(upperLeft.getX(), lowerRight.getY());
        Point ll2 = new Point(rectangle.getUpperLeft().getX(), rectangle.getLowerRight().getY());

        // square 1 bottom to square 2 top
        if(ll1.getY() == ul2.getY()) {
            result = adjacent(ll1, lr1, ul2, ur2);
        }
        // square 1 top to square 2 bottom
        else if(ll2.getY() == ul1.getY()) {
            result = adjacent(ll2, lr2, ul1, ur1);
        }
        // square 1 right to square 2 left
        else if(ur1.getX() == ul2.getX()) {
            result = adjacent(ur1, lr1, ul2, ll2);
        }
        // square 1 left to square 2 right
        else if(ul1.getX() == ur2.getX()) {
            result = adjacent(ul1, ll1, ur2, lr2);
        }
        
        return result;
    }
    
    private boolean adjacent(Point line1StartPoint, Point line1EndPoint, Point line2StartPoint, Point line2EndPoint) {
        return line2StartPoint.isOnLine(line1StartPoint, line1EndPoint) ||
                line2EndPoint.isOnLine(line1StartPoint, line1EndPoint) ||
                line1StartPoint.isOnLine(line2StartPoint, line2EndPoint) ||
                line1EndPoint.isOnLine(line2StartPoint, line2EndPoint);
        
    }
    
    /**
     * A utility function to build a Rectangle using the coordinates of the 
     * upper left corner along with the width and the height.
     * 
     * @param x      the upper left coordinate on the x-axis
     * @param y      the upper left coordinate on the y-axis
     * @param width  the width of the newly created rectangle
     * @param height the height of the newly created rectangle
     * @return       the newly created <code>Rectangle</code>
     * 
     * @throws IllegalArgumentException if width or height is less than or equal to zero
     */
    public static Rectangle build(int x, int y, int width, int height) {
        if(width <= 0) {
            throw new IllegalArgumentException("width must be greater than zero");
        }
        
        if(height <= 0) {
            throw new IllegalArgumentException("height must be greater than zero");
        }
        
        return new Rectangle(x, y, x + width - 1, y + height - 1);
    }
}
