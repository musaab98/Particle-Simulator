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
 *  Tests all methods in the Particle class.
 *
 *  @author Musaab Elsheikh (melsheikh)
 *  @version (2019.11.05)
 */
public class ParticleTest extends TestCase
{
    //~ Fields ................................................................
    
    private ParticleWorld world;
    private Particle a;
    private Particle b;
    private Particle c;
    private Particle d;
    private Particle e;
    
    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new ParticleTest test object.
     */
    public ParticleTest()
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
        a = new Particle(Color.red, false, 1);
        b = new Particle(Color.orange, false, 1);
        c = new Particle(Color.yellow, false, 2);
        d = new Particle(Color.green, false, 3);
        e = new Particle(Color.purple, false, 4);
    }


    // ----------------------------------------------------------
    
    /**
     * Tests Particle constructor
     */
    public void testParticleConstructor()
    {
        assertEquals(Color.red, a.getColor());
        assertFalse(a.willDissolve());
        assertEquals(1, a.getDensity(), 0.001);
        assertEquals(0, a.getVelocity(), 0.001);
        assertEquals(1, a.getAcceleration(), 0.001);
        assertEquals(100, a.getStrength());
    }
    /**
     * Tests isFalling method
     */
    public void testIsFalling1()
    {
        world.add(a, 100, 100);
        assertTrue(a.isFalling());
    }
    /**
     * Tests isFalling method
     */
    public void testIsFalling2()
    {
        world.add(a, 30, 69);
        assertFalse(a.isFalling());
    }
    /**
     * Tests isFalling method
     */
    public void testIsFalling3()
    {
        world.add(a, 30, 249);
        assertFalse(a.isFalling());
    }
    
    /**
     * Tests Weaken method
     */
    public void testWeaken()
    {
        world.add(a, 100, 100);
        a.weaken();
        assertEquals(99, a.getStrength());
        for (int i = 0; i < 98; i++)
        {
            a.weaken();
        }
        assertEquals(1, a.getStrength());
        a.weaken();
        assertEquals(0, a.getStrength());
        assertNull(a.getWorld());
    }
    
    /**
     * Tests Fall method
     */
    public void testFall()
    {
        world.add(a, 50, 225);
        assertEquals(0, a.getVelocity(), 0.001);
        a.fall();
        assertEquals(1, a.getVelocity(), 0.001);
        assertEquals(226, a.getGridY(), 0.001);
        a.fall();
        assertEquals(2, a.getVelocity(), 0.001);
        assertEquals(228, a.getGridY(), 0.001);
        a.fall();
        a.fall();
        a.fall();
        a.fall();
        assertEquals(6, a.getVelocity(), 0.001);
        assertEquals(246, a.getGridY(), 0.001);
        a.fall();
        assertEquals(0, a.getVelocity(), 0.001);
        assertEquals(249, a.getGridY(), 0.001);
    }
    
    /**
     * Tests Swap Places if Possible method
     * checks if it returns false for out of bounds conditions
     */
    public void testSwapPlacesIfPossible1()
    {
        world.add(a, 50, 200);
        assertFalse(a.swapPlacesIfPossible(-5, 0));
        assertFalse(a.swapPlacesIfPossible(300, 0));
        assertFalse(a.swapPlacesIfPossible(100, -5));
        assertFalse(a.swapPlacesIfPossible(100, 300));
    }
    /**
     * Tests Swap Places if Possible method
     * checks if particle moves to empty square
     */
    public void testSwapPlacesIfPossible2()
    {
        world.add(a, 50, 200);
        assertTrue(a.swapPlacesIfPossible(0, 0));
        assertEquals(0, a.getGridX());
        assertEquals(0, a.getGridY());
        assertNull(world.getOneObjectAt(5, 200, Particle.class));
    }
    /**
     * Tests Swap Places if Possible method
     * checks if particles only swap when their density is
     * higher than the second particle
     */
    public void testSwapPlacesIfPossible3()
    {
        world.add(a, 0, 0);
        world.add(b, 0, 2);
        world.add(c, 0, 3);
        assertFalse(a.swapPlacesIfPossible(0, 2));
        assertFalse(b.swapPlacesIfPossible(0, 0));
        assertFalse(a.swapPlacesIfPossible(0, 3));
        assertTrue(c.swapPlacesIfPossible(0, 0));
    }
    
    /**
     * Tests Dodge method
     * checks if particle dodges straight down
     */
    public void testDodge1()
    {
        world.add(c, 100, 248);
        world.add(b, 100, 249);
        assertTrue(c.dodge());
        assertEquals(100, c.getGridX());
        assertEquals(249, c.getGridY());
        assertEquals(100, b.getGridX());
        assertEquals(248, b.getGridY());
    }
    /**
     * Tests Dodge method
     * checks if particle dodges leftwards
     */
    public void testDodge2()
    {
        world.add(c, 100, 248);
        world.add(d, 100, 249);
        world.add(b, 99, 249);
        assertTrue(c.dodge());
        assertEquals(100, b.getGridX());
        assertEquals(248, b.getGridY());
        assertEquals(99, c.getGridX());
        assertEquals(249, c.getGridY());
    }
    /**
     * Tests Dodge method
     * checks if particle dodges rightwards
     */
    public void testDodge3()
    {
        world.add(c, 100, 248);
        world.add(d, 100, 249);
        world.add(e, 99, 249);
        world.add(a, 101, 249);
        assertTrue(c.dodge());
        assertEquals(101, c.getGridX());
        assertEquals(249, c.getGridY());
        assertEquals(100, a.getGridX());
        assertEquals(248, a.getGridY());
    }
    /**
     * Tests Dodge method
     * checks if particle fails to dodge
     */
    public void testDodge4()
    {
        world.add(a, 100, 248);
        world.add(b, 100, 249);
        world.add(c, 99, 249);
        world.add(d, 101, 249);
        assertFalse(a.dodge());
        assertEquals(100, a.getGridX());
        assertEquals(248, a.getGridY());
    }
    
    /**
     * Tests Act method
     */
    public void testAct1()
    {
        world.add(a, 50, 225);
        a.act();
        assertEquals(1, a.getVelocity(), 0.001);
        assertEquals(50, a.getGridX(), 0.001);
        assertEquals(226, a.getGridY(), 0.001);
    }
    /**
     * Tests Act method
     */
    public void testAct2()
    {
        world.add(c, 100, 248);
        world.add(b, 100, 249);
        c.act();
        assertEquals(100, b.getGridX());
        assertEquals(100, c.getGridX());
        assertEquals(248, b.getGridY());
        assertEquals(249, c.getGridY());
    }
    /**
     * Tests Act method
     */
    public void testAct3()
    {
        world.add(a, 31, 69);
        a.act();
        assertEquals(31, a.getGridX());
        assertEquals(69, a.getGridY());
    }
}






























