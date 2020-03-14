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
 *  Tests all methods in the Sand class.
 *
 *  @author Musaab Elsheikh (melsheikh)
 *  @version (2019.11.05)
 */
public class SandTest extends TestCase
{
    //~ Fields ................................................................
    
    private ParticleWorld world;
    private Sand sand;
    
    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new SandTest test object.
     */
    public SandTest()
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
        sand = new Sand();
    }


    // ----------------------------------------------------------
    
    /**
     * Tests Sand constructor
     */
    public void testSandConstructor()
    {
        world.add(sand, 100, 100);
        assertEquals(Color.khaki, sand.getColor());
        assertTrue(sand.willDissolve());
        assertEquals(2.5, sand.getDensity(), 0.001);
    }
}





















