import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shotgun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shotgun extends Weapon
{
    private static final int SHOTGUN_DAMAGE = 15;
    private static final int SHOTGUN_FIRE_RATE = 50;
    private static final int SHOTGUN_RELOAD_TIME = 1;
    private GreenfootImage weaponImage;
    
    public Shotgun()
    {
        this.weaponImage = new GreenfootImage("Shotgun.png");
        this.setImage(weaponImage);
    }
    
    public int damage()
    {
        return SHOTGUN_DAMAGE;
    }
    
    public int fireRate()
    {
        return SHOTGUN_FIRE_RATE;
    }
    
    public int reloadTime()
    {
        return SHOTGUN_RELOAD_TIME;
    }
}
