import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Weapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Weapon extends Actor
{
    private int damage;
    private int fireRate;
    private int reloadTime;
    private int clipSizeLeft;
    private int clipSizeMax;
    
    public abstract int damage();
    public abstract int fireRate();
    public abstract int reloadTime();  
    
    /**
     * Default constructor.
     */
    public Weapon()
    {
        this.damage = damage();
        this.fireRate = fireRate();
        this.reloadTime = reloadTime();
    }
    
    /**
     * Returns the weapon.
     */
    public Weapon getWeapon()
    {
        return this;
    }
    
    public boolean isReloading()
    {
        if (clipSizeLeft == 0)
            return true;
        else
            return false;
    }
}
