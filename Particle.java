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
 *  This class represents a particle in the ParticleWorld map. Each
 *  particle has a density, velocity, acceleration, strength, color,
 *  and dissolvability.
 *
 *  @author Musaab Elsheikh (melsheikh)
 *  @version (2019.11.05)
 */
public class Particle extends ParticleBase
{
    //~ Fields ................................................................
    private boolean willDissolve;
    private double density;
    private double velocity;
    private double acceleration;
    private int strength;
    
    //~ Constructor ...........................................................
    
    // ------------------------------------------------------------------------
    /**
     * Creates a new Particle object
     * @param color         color of particle
     * @param willDissolve  determines if particle can dissolve
     * @param density       density of particle
     */
    public Particle(Color color, boolean willDissolve, double density)
    {
        super(color);
        this.willDissolve = willDissolve;
        this.density = density;
        this.velocity = 0;
        this.acceleration = 1;
        this.strength = 100;
        this.turn(90);
    }
    
    
    //~ Methods ...............................................................
    
    // ------------------------------------------------------------------------
    /**
     * Density getter method
     * @return density      returns density of particle
     */
    public double getDensity()
    {
        return density;
    }
    
    /**
     * Velocity getter method
     * @return velocity     returns velocity of particle
     */
    public double getVelocity()
    {
        return velocity;
    }
    
    /**
     * Acceleration getter method
     * @return acceleration     returns acceleration of particle
     */
    public double getAcceleration()
    {
        return acceleration;
    }
    
    /**
     * Strength getter method
     * @return strength     returns strength of particle
     */
    public int getStrength()
    {
        return strength;
    }
    
    /**
     * Will Dissolve getter method
     * @return willDissolve      returns if particle will dissolve
     */
    public boolean willDissolve()
    {
        return willDissolve;
    }
    
    // ------------------------------------------------------------------------
    /**
     * Weakens particle, and removes it if strength falls to 0
     */
    public void weaken()
    {
        strength--;
        if (strength <= 0)
        {
            this.remove();
        }
    }
    
    /**
     * Is Falling Method
     * @return falling      returns true if falling, false otherwise
     */
    public boolean isFalling()
    {
        int x = this.getGridX();
        int y = this.getGridY();
        if (y == getWorld().getHeight() - 1)
        {
            return false;
        }
        Particle floor = getWorld().getOneObjectAt(x, y + 1, Particle.class);
        return (floor == null);
    }
    
    /**
     * Makes particle fall
     */
    public void fall()
    {
        velocity += acceleration;
        // falls number of pixels equal to velocity (unless blocked)
        for (int i = 0; i < (int)velocity; i++)
        {
            if (isFalling())
            {
                move(1);
            }
        }
        // sets velocity to 0 once particle stops falling
        if (!isFalling())
        {
            velocity = 0;
        }
    }
    
    /**
     * Swaps particle with another particle at given coordinates
     * @param x             x-coordinate of other particle
     * @param y             y-coordinate of other particle
     * @return boolean      true if swapped successfully, false otherwise
     */
    public boolean swapPlacesIfPossible(int x, int y)
    {
        // returns false if checking outside the map
        if (x >= getWorld().getWidth() || x < 0)
        {
            return false;
        }
        if (y >= getWorld().getHeight() || y < 0)
        {
            return false;
        }
        // coordinates of this particle
        int xPos = getGridX();
        int yPos = getGridY();
        // swaps if there's no particle at given x, y coordinates
        Particle p2 = getWorld().getOneObjectAt(x, y, Particle.class);
        if (p2 == null)
        {
            this.setGridLocation(x, y);
            return true;
        }
        // swaps when given particle has a lower density
        if (density > p2.getDensity())
        {
            this.setGridLocation(x, y);
            p2.setGridLocation(xPos, yPos);
            return true;
        }
        return false;
    }
    
    /**
     * Tries swapping with particle below, then tries swapping with
     * southwest particle, then southeast particle.
     * @return boolean      true if dodged successfully, false otherwise
     */
    public boolean dodge()
    {
        if (swapPlacesIfPossible(getGridX(), getGridY() + 1))
        {
            return true;
        }
        if (swapPlacesIfPossible(getGridX() - 1, getGridY() + 1))
        {
            return true;
        }
        return swapPlacesIfPossible(getGridX() + 1, getGridY() + 1);
    }
    
    /**
     * Executes 1 turn (falls if airborn, otherwise dodges)
     */
    public void act()
    {
        if (isFalling())
        {
            fall();
        }
        else
        {
            dodge();
        }
    }
}


































