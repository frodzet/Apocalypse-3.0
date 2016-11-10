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
    GameBoard gameBoard;
    
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
        gameBoard = (GameBoard) this.getWorld();
        // Add your action code here.
        if (gameBoard != null)
        {
            moveBullet();
            removeBullet();
        }
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
        if (intersectingCreature != null && intersectingCreature.canBeShot())
        {
            int creatureHealth = intersectingCreature.getHealth();
            intersectingCreature.setHealth(creatureHealth - getDamage());
            if (!intersectingCreature.isAlive())
                gameBoard.getPlayer().setScore(gameBoard.getPlayer().getScore() + 15 + creatureHealth);
            return true;            
        }
        else
        {
            return false;
        }
    }
}
