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
 *  This class represents a water particle in the ParticleWorld map.
 *
 *  @author Musaab Elsheikh (melsheikh)
 *  @version (2019.11.05)
 */
public class Water extends Fluid
{
    //~ Fields ................................................................
    
    //~ Constructor ...........................................................
    
    // ----------------------------------------------------------
    /**
     * Creates a new Water object.
     */
    public Water()
    {
        super(Color.cadetBlue, false, 1.0);
    }
    
    //~ Methods ...............................................................
    
}
