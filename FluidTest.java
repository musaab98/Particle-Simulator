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
 *  Tests all methods in the Fluid class.
 *
 *  @author Musaab Elsheikh (melsheikh)
 *  @version (2019.11.05)
 */
public class FluidTest extends TestCase
{
    //~ Fields ................................................................
    
    private ParticleWorld world;
    private Fluid a;
    private Particle b;
    private Particle c;
    
    
    //~ Constructor ...........................................................
    
    // ----------------------------------------------------------
    /**
     * Creates a new FluidTest test object.
     */
    public FluidTest()
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
        a = new Fluid(Color.red, false, 1);
        b = new Particle(Color.orange, false, 2);
        c = new Particle(Color.green, false, 3);
    }


    // ----------------------------------------------------------
    
    /**
     * tests fluid constructor
     */
    public void testFluidConstructor()
    {
        assertEquals(Color.red, a.getColor());
        assertFalse(a.willDissolve());
        assertEquals(1, a.getDensity(), 0.001);
    }
    
    /**
     * checks if fluid dodges left
     */
    public void testDodge1()
    {
        world.add(a, 50, 249);
        a.dodge();
        assertEquals(49, a.getGridX(), 0.001);
        assertEquals(249, a.getGridY(), 0.001);
    }
    /**
     * checks if fluid dodges right
     */
    public void testDodge2()
    {
        world.add(a, 50, 249);
        world.add(b, 49, 249);
        a.dodge();
        assertEquals(51, a.getGridX(), 0.001);
        assertEquals(249, a.getGridY(), 0.001);
    }
    /**
     * checks if fluid doesn't dodge
     */
    public void testDodge3()
    {
        world.add(a, 50, 249);
        world.add(b, 49, 249);
        world.add(c, 51, 249);
        a.dodge();
        assertEquals(50, a.getGridX(), 0.001);
        assertEquals(249, a.getGridY(), 0.001);
    }
    /**
     * checks if fluid follows Particle class's dodge
     */
    public void testDodge4()
    {
        world.add(a, 100, 248);
        world.add(b, 100, 249);
        world.add(c, 99, 249);
        assertTrue(a.dodge());
        assertEquals(101, a.getGridX());
        assertEquals(249, a.getGridY());
    }
}























