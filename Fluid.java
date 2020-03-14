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
 *  This class represents a fluid particle in the ParticleWorld map.
 *
 *  @author Musaab Elsheikh (melsheikh)
 *  @version (2019.11.05)
 */
public class Fluid extends Particle
{
    //~ Fields ................................................................
    
    //~ Constructor ...........................................................
    
    // ----------------------------------------------------------
    /**
     * Creates a new Fluid object.
     * @param color         color of particle
     * @param willDissolve  determines if particle can dissolve
     * @param density       density of particle
     */
    public Fluid(Color color, boolean willDissolve, double density)
    {
        super(color, willDissolve, density);
    }
    
    //~ Methods ...............................................................
    
    /**
     * Fluid dodges if possible
     * @return boolean      true if dodged successfully, false otherwise
     */
    @Override public boolean dodge()
    {
        // tries particle's dodge method
        if (super.dodge())
        {
            return true;
        }
        // flows left, otherwise flows right, otherwise returns false
        if (swapPlacesIfPossible(getGridX() - 1, getGridY()))
        {
            return true;
        }
        return swapPlacesIfPossible(getGridX() + 1, getGridY());
    }
}


































