import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SMG here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SMG extends Weapon
{
    private static final int SMG_DAMAGE = 6; // More is more.
    private static final int SMG_FIRE_RATE = 0; // Less is faster.
    private static final int SMG_RELOAD_TIME = 1; // Less is faster.
    private static final int SMG_CLIP_SIZE = 30;
    private GreenfootImage weaponImage;
    
    public SMG()
    {
        this.weaponImage = new GreenfootImage("SMG.png");
        this.setImage(weaponImage);
    }
    
    public int damage()
    {
        return SMG_DAMAGE;
    }
    
    public int fireRate()
    {
        return SMG_FIRE_RATE;
    }
    
    public int reloadTime()
    {
        return SMG_RELOAD_TIME;
    }
    
    public int clipSize()
    {
        return SMG_CLIP_SIZE;
    }
    
    public GreenfootSound sound()
    {
        return new GreenfootSound("SMG.mp3");
    }
}