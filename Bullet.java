import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    private int rotation;
    private boolean hasBulletCollidedWithCreature;
    
    /**
     * Default bullet constructor.
     */
    public Bullet()
    {
        this.setImage(new GreenfootImage("Bullet.png"));
        setDirection();
    }
    
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        moveBullet();
        removeBullet();
    }
    
    /**
     * Move's the bullet.
     */
    public void moveBullet()
    {
        move(12);
    }
    
    /**
     * The direction of the bullet.
     */
    public void setDirection()
    {
        if (Greenfoot.getMouseInfo() == null)
            return;
        
        this.turnTowards(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
    }
    
    /**
     * Gets the damage from the currently equipped weapon.
     */
    public int getDamage()
    {
        GameBoard gameBoard = (GameBoard) this.getWorld();
        if (gameBoard != null)
        {
            Weapon weapon = gameBoard.getPlayer().getCurrentWeapon();
            return weapon.damage();
        }
        
        return 0;
    }
    
    /**
     * Removes the bullet from the world.
     */
    public void removeBullet()
    {
        if (this != null && this.isAtEdge() || hasBulletCollidedWithCreature())
        {
            this.getWorld().removeObject(this);
        }
    }
    
    public boolean hasBulletCollidedWithCreature()
    {
        Creature intersectingCreature = (Creature) this.getOneIntersectingObject(Creature.class);
        if (intersectingCreature != null)
        {
            intersectingCreature.setHealth(intersectingCreature.getHealth() - getDamage());
            return true;            
        }
        else
        {
            return false;
        }
    }
}
