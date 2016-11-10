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
    private int clipSize;
    private GreenfootSound sound;
    
    public abstract int damage();
    public abstract int fireRate();
    public abstract int reloadTime();
    public abstract int clipSize();
    public abstract GreenfootSound sound();
    
    /**
     * Default constructor.
     */
    public Weapon()
    {
        this.damage = damage();
        this.fireRate = fireRate();
        this.reloadTime = reloadTime();
        this.clipSize = clipSize();
        this.sound = sound();
    }
    
    /**
     * Returns the weapon.
     */
    public Weapon getWeapon()
    {
        return this;
    }
    
}
