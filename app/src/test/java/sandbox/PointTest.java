package sandbox;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * @author Wes McKean
 */
public class PointTest {
    @Test
    public void testNotOnLine() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0, 10);
        Point p3 = new Point(5, 5);
        Point p4 = new Point(0, 15);
        
        assertFalse(p3.isOnLine(p1, p2));
        assertFalse(p4.isOnLine(p1, p2));
    }

    @Test
    public void testOnLineYAxis() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0, 10);
        Point p3 = new Point(0, 5);
        
        assertTrue(p3.isOnLine(p1, p2));
    }

    @Test
    public void testOnLineXAxis() {
        Point p1 = new Point(0,10);
        Point p2 = new Point(10, 10);
        Point p3 = new Point(5, 10);
        
        assertTrue(p3.isOnLine(p1, p2));
    }
}
