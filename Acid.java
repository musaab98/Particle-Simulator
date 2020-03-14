// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Musaab Elsheikh (melsheikh)

import sofia.micro.*;
import sofia.graphics.Color;

//-------------------------------------------------------------------------
/**
 *  This class represents an acid particle in the ParticleWorld map.
 *
 *  @author Musaab Elsheikh (melsheikh)
 *  @version (2019.11.05)
 */
public class Acid extends Fluid
{
    //~ Fields ................................................................
    
    //~ Constructor ...........................................................
    
    // ----------------------------------------------------------
    /**
     * Creates a new Acid object.
     */
    public Acid()
    {
        super(Color.green, false, 1.0);
    }
    
    //~ Methods ...............................................................
    
    /**
     * Disolves a block at a given coordinate
     * @param x     x-coordinate of other particle
     * @param y     y-coordinate of other particle
     */
    public void dissolveIfPossible(int x, int y)
    {
        // does nothing when checking outside the map
        if (x >= getWorld().getWidth() || x < 0)
        {
            return;
        }
        if (y >= getWorld().getHeight() || y < 0)
        {
            return;
        }
        Particle p2 = getWorld().getOneObjectAt(x, y, Particle.class);
        // does nothing if there's no particle at given coordinate
        if (p2 == null)
        {
            return;
        }
        // disolves particle
        else if (p2.willDissolve())
        {
            p2.weaken();
            this.weaken();
        }
    }
    
    /**
     * Overrides act from fluid
     * Disolves adjacent blocks, then acts normally
     */
    @Override public void act()
    {
        dissolveIfPossible(getGridX() - 1, getGridY());
        // stops acting if acid gets removed
        if (getWorld() == null)
        {
            return;
        }
        dissolveIfPossible(getGridX() + 1, getGridY());
        // stops acting if acid gets removed
        if (getWorld() == null)
        {
            return;
        }
        dissolveIfPossible(getGridX(), getGridY() - 1);
        // stops acting if acid gets removed
        if (getWorld() == null)
        {
            return;
        }
        dissolveIfPossible(getGridX(), getGridY() + 1);
        // stops acting if acid gets removed
        if (getWorld() == null)
        {
            return;
        }
        super.act();
    }
}





















