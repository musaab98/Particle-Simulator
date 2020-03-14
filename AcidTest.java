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
 *  Tests all methods in the Acid class.
 *
 *  @author Musaab Elsheikh (melsheikh)
 *  @version (2019.11.05)
 */
public class AcidTest extends TestCase
{
    //~ Fields ................................................................
    
    private ParticleWorld world;
    private Acid acid;
    private Steel steel;
    private Steel a;
    private Steel b;
    private Steel c;
    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new AcidTest test object.
     */
    public AcidTest()
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
        acid = new Acid();
        steel = new Steel();
        a = new Steel();
        b = new Steel();
        c = new Steel();
    }


    // ----------------------------------------------------------
    
    /**
     * Tests Acid Constructor
     */
    public void testAcidConstructor()
    {
        world.add(acid, 100, 100);
        assertEquals(Color.green, acid.getColor());
        assertFalse(acid.willDissolve());
        assertEquals(1, acid.getDensity(), 0.001);
    }
    
    /**
     * Tests Dissolve If Possible method
     * checks if acid does nothing when checking out of bounds
     */
    public void testDissolveIfPossible1()
    {
        world.add(acid, 100, 249);
        acid.dissolveIfPossible(300, 200);
        assertEquals(100, acid.getStrength());
        acid.dissolveIfPossible(-5, 200);
        assertEquals(100, acid.getStrength());
        acid.dissolveIfPossible(100, 300);
        assertEquals(100, acid.getStrength());
        acid.dissolveIfPossible(100, -2);
        assertEquals(100, acid.getStrength());
        acid.dissolveIfPossible(100, 100);
        assertEquals(100, acid.getStrength());
    }
    /**
     * Tests Dissolve If Possible method
     * checks if acid does nothing when square is empty
     * or can't be dissolved
     */
    public void testDissolveIfPossible2()
    {
        Water water = new Water();
        world.add(acid, 100, 249);
        world.add(water, 0, 249);
        acid.dissolveIfPossible(100, 100);
        assertEquals(100, acid.getStrength());
        acid.dissolveIfPossible(0, 249);
        assertEquals(100, acid.getStrength());
        assertEquals(100, water.getStrength());
    }
    /**
     * Tests Dissolve If Possible method
     * checks if acid can disolve dissolvable blocks
     */
    public void testDissolveIfPossible3()
    {
        world.add(acid, 100, 249);
        world.add(steel, 99, 249);
        acid.dissolveIfPossible(99, 249);
        assertEquals(99, acid.getStrength());
        assertEquals(99, steel.getStrength());
        for (int i = 0; i < 98; i++)
        {
            acid.dissolveIfPossible(99, 249);
        }
        assertEquals(1, acid.getStrength());
        assertEquals(1, steel.getStrength());
        acid.dissolveIfPossible(99, 249);
        assertNull(acid.getWorld());
        assertNull(steel.getWorld());
    }
    
    /**
     * Tests act
     * checks if acid dissolves nothing
     */
    public void testAct1()
    {
        world.add(acid, 100, 249);
        acid.act();
        assertEquals(100, acid.getStrength());
        assertEquals(99, acid.getGridX());
        assertEquals(249, acid.getGridY());
    }
    /**
     * Tests act
     * checks if acid dissolves neighboring steel
     */
    public void testAct2()
    {
        world.add(acid, 100, 249);
        world.add(steel, 99, 249);
        world.add(a, 101, 249);
        world.add(b, 100, 248);
        acid.act();
        assertEquals(97, acid.getStrength());
        assertEquals(99, steel.getStrength());
        assertEquals(99, a.getStrength());
        assertEquals(99, b.getStrength());
    }
    /**
     * Tests act
     * checks if acid stops acting after dissolving on left particle
     */
    public void testAct3()
    {
        world.add(acid, 0, 249);
        world.add(steel, 1, 249);
        for (int i = 0; i < 99; i++)
        {
            acid.act();
        }
        assertEquals(1, acid.getStrength());
        assertEquals(1, steel.getStrength());
        acid.setGridLocation(100, 100);
        steel.setGridLocation(99, 100);
        world.add(a, 100, 99);
        world.add(b, 101, 100);
        world.add(c, 100, 101);
        acid.act();
        assertNull(acid.getWorld());
        assertNull(steel.getWorld());
        assertEquals(100, a.getStrength());
        assertEquals(100, b.getStrength());
        assertEquals(100, c.getStrength());
    }
    /**
     * Tests act
     * checks if acid stops acting after dissolving on right particle
     */
    public void testAct4()
    {
        world.add(acid, 0, 249);
        world.add(steel, 1, 249);
        for (int i = 0; i < 98; i++)
        {
            acid.act();
        }
        assertEquals(2, acid.getStrength());
        assertEquals(2, steel.getStrength());
        acid.setGridLocation(100, 100);
        steel.setGridLocation(99, 100);
        world.add(a, 100, 99);
        world.add(b, 101, 100);
        world.add(c, 100, 101);
        acid.act();
        assertNull(acid.getWorld());
        assertEquals(1, steel.getStrength());
        assertEquals(100, a.getStrength());
        assertEquals(99, b.getStrength());
        assertEquals(100, c.getStrength());
    }
    /**
     * Tests act
     * checks if acid stops acting after dissolving on above particle
     */
    public void testAct5()
    {
        world.add(acid, 0, 249);
        world.add(steel, 1, 249);
        for (int i = 0; i < 97; i++)
        {
            acid.act();
        }
        assertEquals(3, acid.getStrength());
        assertEquals(3, steel.getStrength());
        acid.setGridLocation(100, 100);
        steel.setGridLocation(99, 100);
        world.add(a, 100, 99);
        world.add(b, 101, 100);
        world.add(c, 100, 101);
        acid.act();
        assertNull(acid.getWorld());
        assertEquals(2, steel.getStrength());
        assertEquals(99, a.getStrength());
        assertEquals(99, b.getStrength());
        assertEquals(100, c.getStrength());
    }
    /**
     * Tests act
     * checks if acid stops acting after dissolving on below particle
     */
    public void testAct6()
    {
        world.add(acid, 0, 249);
        world.add(steel, 1, 249);
        for (int i = 0; i < 96; i++)
        {
            acid.act();
        }
        assertEquals(4, acid.getStrength());
        assertEquals(4, steel.getStrength());
        acid.setGridLocation(100, 100);
        steel.setGridLocation(99, 100);
        world.add(a, 100, 99);
        world.add(b, 101, 100);
        world.add(c, 100, 101);
        acid.act();
        assertNull(acid.getWorld());
        assertEquals(3, steel.getStrength());
        assertEquals(99, a.getStrength());
        assertEquals(99, b.getStrength());
        assertEquals(99, c.getStrength());
    }
    
}





































