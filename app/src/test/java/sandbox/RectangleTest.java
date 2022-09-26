
package sandbox;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Wes McKean
 */
public class RectangleTest {
    
    @Test
    public void testInvalidCoordinates() {
        assertThrows(IllegalArgumentException.class, () ->{Rectangle r1 = new Rectangle(10, 10, 4, 4);});
        assertThrows(IllegalArgumentException.class, () ->{Rectangle r1 = new Rectangle(null, null);});        
        assertThrows(IllegalArgumentException.class, () ->{Rectangle r1 = new Rectangle(new Point(0,0), null);});        
        assertThrows(IllegalArgumentException.class, () ->{Rectangle r1 = new Rectangle(null, new Point(0,0));});        
        assertThrows(IllegalArgumentException.class, () ->{Rectangle r1 = new Rectangle(new Point(10,10), new Point(5,5));}); 
        
        final Rectangle r1 = new Rectangle(10, 10, 20, 20);        
        assertThrows(IllegalArgumentException.class, () ->{r1.setUpperLeft(null);}); 
        assertThrows(IllegalArgumentException.class, () ->{r1.setUpperLeft(null);}); 

        assertThrows(IllegalArgumentException.class, () ->{r1.setLowerRight(new Point(5, 20));}); 
        assertThrows(IllegalArgumentException.class, () ->{r1.setLowerRight(new Point(20, 5));}); 
    }
    
    @Test
    public void testNonOverlapping() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(11, 11, 21, 21);
        
        assertFalse(r1.intersects(r2));
        assertFalse(r2.intersects(r1));
    }

    @Test
    public void testOverlapping() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(5, 5, 8, 12);
        // contains
        Rectangle r3 = new Rectangle(1, 1, 2,2);
        
        assertTrue(r1.intersects(r2));
        assertTrue(r2.intersects(r1));
        
        assertFalse(r1.intersects(r3));
        assertFalse(r3.intersects(r1));
    }
    
    @Test
    public void testContains() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r3 = new Rectangle(1, 1, 2,2);
        
        assertTrue(r1.contains(r3));
        assertFalse(r3.contains(r1));
    }
    
    @Test
    public void testAdjacentSublineSide() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(10, 3, 20, 6);
        
        assertTrue(r1.adjacent(r2));
        assertTrue(r2.adjacent(r1));
    }

    @Test
    public void testAdjacentProperSide() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(10, 0, 20, 10);
        
        assertTrue(r1.adjacent(r2));
        assertTrue(r2.adjacent(r1));
    }

    @Test
    public void testAdjacentPartialSide() {
        Rectangle r1 = new Rectangle(0, 3, 10, 10);
        Rectangle r2 = new Rectangle(10, 0, 20, 10);
        
        assertTrue(r1.adjacent(r2));
        assertTrue(r2.adjacent(r1));
    }

    @Test
    public void testNotAdjacentSide() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(12, 12, 20, 20);
        
        assertFalse(r1.adjacent(r2));
        assertFalse(r2.adjacent(r1));
    }
    
    @Test
    public void testAdjacentSublineTopBottom() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(3, 10, 8, 16);
        
        assertTrue(r1.adjacent(r2));
        assertTrue(r2.adjacent(r1));
    }
    
    @Test
    public void testAdjacentProperTopBottom() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(0, 10, 10, 20);
        
        assertTrue(r1.adjacent(r2));
        assertTrue(r2.adjacent(r1));
    }
    
    @Test
    public void testAdjacentPartialTopBottom() {
        Rectangle r1 = new Rectangle(0, 0, 10, 10);
        Rectangle r2 = new Rectangle(5, 10, 15, 15);
        
        assertTrue(r1.adjacent(r2));
        assertTrue(r2.adjacent(r1));
    }    
    
    @Test
    public void testIntersection() {
        Rectangle r1 = new Rectangle(0, 0, 4, 4);
        Rectangle r2 = new Rectangle(2, 2, 6, 6);
        
        assertTrue(r1.intersects(r2));
        Optional<Rectangle> optional = r1.intersection(r2);
        assertTrue(optional.isPresent());
        
        Rectangle r3 = optional.get();
        assertEquals(r3.getUpperLeft().getX(), 2);
        assertEquals(r3.getUpperLeft().getY(), 2);
        assertEquals(r3.getLowerRight().getX(), 4);
        assertEquals(r3.getLowerRight().getY(), 4);
    }

    @Test
    public void testIntersectionFail() {
        Rectangle r1 = new Rectangle(0, 0, 4, 4);
        Rectangle r2 = new Rectangle(5, 5, 9, 9);
        
        assertFalse(r1.intersects(r2));
        Optional<Rectangle> optional = r1.intersection(r2);
        assertFalse(optional.isPresent());
    }
}
