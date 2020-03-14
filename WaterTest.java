// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Musaab Elsheikh (melsheikh)

import sofia.micro.*;
import sofia.graphics.Color;

// -------------------------------------------------------------------------
/**
 *  Tests all methods in the Water class.
 *
 *  @author Musaab Elsheikh (melsheikh)
 *  @version (2019.11.05)
 */
public class WaterTest extends TestCase
{
    //~ Fields ................................................................
    
    private ParticleWorld world;
    private Water water;
    
    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WaterTest test object.
     */
    public WaterTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        world = new ParticleWorld(0);
        water = new Water();
    }


    // ----------------------------------------------------------
    
    /**
     * Tests Water constructor
     */
    public void testWaterConstructor()
    {
        world.add(water, 100, 100);
        assertEquals(Color.cadetBlue, water.getColor());
        assertFalse(water.willDissolve());
        assertEquals(1, water.getDensity(), 0.001);
    }
}
















