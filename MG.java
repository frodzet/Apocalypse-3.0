import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MG here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MG extends Weapon
{
    private static final int MG_DAMAGE = 15;
    private static final int MG_FIRE_RATE = 5;
    private static final int MG_RELOAD_TIME = 1;
    private static final int MG_CLIP_SIZE = 45;
    private GreenfootImage weaponImage;
    
    public MG()
    {
        this.weaponImage = new GreenfootImage("Machine Gun.png");
        this.setImage(weaponImage);
    }
    
    public int damage()
    {
        return MG_DAMAGE;
    }
    
    public int fireRate()
    {
        return MG_FIRE_RATE;
    }
    
    public int reloadTime()
    {
        return MG_RELOAD_TIME;
    }
    
    public int clipSize()
    {
        return MG_CLIP_SIZE;
    }
    
    public GreenfootSound sound()
    {
        return new GreenfootSound("MG.mp3");
    }
}
