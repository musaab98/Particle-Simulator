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
 *  This class represents a steel particle in the ParticleWorld map.
 *
 *  @author Musaab Elsheikh (melsheikh)
 *  @version (2019.11.05)
 */
public class Steel extends Particle
{
    //~ Fields ................................................................
    
    //~ Constructor ...........................................................
    
    // ----------------------------------------------------------
    /**
     * Creates a new Steel object.
     */
    public Steel()
    {
        super(Color.gray, true, 7.87);
    }
    
    //~ Methods ...............................................................
    
    /**
     * Overrides isFalling so that steel never falls
     * @return boolean      returns false
     */
    @Override public boolean isFalling()
    {
        return false;
    }
    
    /**
     * Overrides dodge so that steel never dodges anything
     * @return boolean      returns false
     */
    @Override public boolean dodge()
    {
        return false;
    }
}




































