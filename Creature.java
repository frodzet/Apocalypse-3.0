import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Creatures here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Creature extends Actor
{
    private int speed;
    private int health;
    private int damage;
    private boolean isAlive;
    private int deathTimer;
    private boolean canKill;
    private int timeSinceLastAttack;
    private GameBoard gameBoard;        
    private int imageCounter;
    
    /**
     * The default constructor for the creature class.
     */
    public Creature(int health, int damage, int speed)
    {        
        this.health = health;
        this.damage = damage;
        this.speed = speed;
    }    
    
    /**
     * Act - do whatever the Creatures wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {   
        gameBoard = (GameBoard) this.getWorld();
        // Just making absolutely sure the gameWorld actually exist.
        if (gameBoard != null)
        {
            if (isAlive())
            {
                turnTowardsPlayer();
                move();
                attack();
                kill();

            }
            timeSinceDeath();
        }
    }
    
    /**
     * Gets the movement speed of the creature.
     */
    public int getSpeed()
    {
        return this.speed;
    }
    
    /**
     * Sets the movement speed of the creature.
     */
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    
    /**
     * Gets the creature's health.
     */
    public int getHealth()
    {
        return this.health;
    }
    
    /**
     * Sets the creature's health.
     */
    public void setHealth(int health)
    {
        this.health = health;
    }
    
    /**
     * Move the creature towards the player's position.
     */
    public void move()
    {   
        if (this.isTouching(Player.class) || this.isPuking)
            return;
        // Follow the player on the x-axis    
        if (this.getX() > gameBoard.getPlayer().getX())
        {
            this.setLocation(this.getX() - speed, this.getY());
        }
        else
        {
            this.setLocation(this.getX() + speed, this.getY());
        }
        
        // Follow the player on the y-axis
        if (this.getY() > gameBoard.getPlayer().getY())
        {
            this.setLocation(this.getX(), this.getY() - speed);
        }
        else
        {
            this.setLocation(this.getX(), this.getY() + speed);
        }
    }
    
    /**
     * Rotate the creature to always be facing the player.
     */
    public void turnTowardsPlayer()
    {
        if (this.isTouching(Player.class))
            return;
            
        this.turnTowards(gameBoard.getPlayer().getX(), gameBoard.getPlayer().getY());
    }
    
    /**
     * Kill the creature.
     */
    public void kill()
    {
        if (!this.isAlive)
        {
            this.setImage("boy1.png");
        }
    }
    
    public void attack()
    {
        Actor intersectingObject = this.getOneIntersectingObject(Player.class);
        if (intersectingObject != null && timeSinceLastAttack > 70)
        {
            puke();
            gameBoard.getPlayer().setHealth(gameBoard.getPlayer().getHealth() - this.damage);
            timeSinceLastAttack = 0;
        }
        timeSinceLastAttack++;
        imageCounter++;
    }
    private boolean isPuking;
    /**
     * Simulate an attack by wiggling.
     */
    public void puke()
    {
        if (imageCounter < 100)
        {
            this.setImage("Player.png");
        }
            
        if (imageCounter > 100)
        {
            this.setImage("boy1.png");
            isPuking = true;
        }
        
        if (imageCounter > 200)
        {
            imageCounter = 0;
            isPuking = false;
        }
    }
    
    public boolean isAlive()
    {
        if (this.health > 0)
            isAlive = true;
        else
            isAlive = false;

        return this.isAlive;
    }
    
    public boolean canBeShot()
    {
        if (this.isAlive())
            canKill = true;
        else
            canKill = false;
            
        return canKill;
    }
    
    public int timeSinceDeath()
    {
        if (!this.isAlive())
        {
            deathTimer++;
        }

        return deathTimer;
    }
}
