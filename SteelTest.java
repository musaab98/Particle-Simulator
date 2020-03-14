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
 *  Tests all methods in the Steel class.
 *
 *  @author Musaab Elsheikh (melsheikh)
 *  @version (2019.11.05)
 */
public class SteelTest extends TestCase
{
    //~ Fields ................................................................
    
    private ParticleWorld world;
    private Steel steel;
    private Steel a;
    private Steel b;
    private Steel c;
    
    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new SteelTest test object.
     */
    public SteelTest()
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
        steel = new Steel();
        a = new Steel();
        b = new Steel();
        c = new Steel();
    }
    
    
    // ----------------------------------------------------------
    
    /**
     * tests steel constructor
     */
    public void testSteelConstructor()
    {
        assertEquals(Color.gray, steel.getColor());
        assertTrue(steel.willDissolve());
        assertEquals(7.87, steel.getDensity(), 0.001);
    }
    
    /**
     * tests isFalling
     */
    public void testIsFalling()
    {
        world.add(steel, 0, 0);
        world.add(a, 10, 249);
        world.add(b, 100, 100);
        world.add(c, 30, 69);
        assertFalse(steel.isFalling());
        assertFalse(a.isFalling());
        assertFalse(b.isFalling());
        assertFalse(c.isFalling());
    }
    
    /**
     * tests dodge
     */
    public void testDodge()
    {
        world.add(steel, 0, 0);
        world.add(a, 10, 249);
        world.add(b, 100, 100);
        world.add(c, 30, 69);
        assertFalse(steel.dodge());
        assertFalse(a.dodge());
        assertFalse(b.dodge());
        assertFalse(c.dodge());
    }
}



























