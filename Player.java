import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int speed;
    private int health;
    private int armor;
    private int score;
    private Weapon startWeapon;
    private GreenfootImage startImage;
    
    private boolean isAlive;
    private Weapon currentWeapon;
    
    private int actCount;
    
    /**
     * Default constructor for Player.
     */
    public Player()
    {
        startImage = new GreenfootImage("Player.png");
        init();
    }
    
    /**
     * Initializes the default settings for the player.
     */
    private void init()
    {
       speed = 2;
       health = 100;
       armor = 0;
       score = 0;
       startWeapon = new Pistol();
       currentWeapon = startWeapon;
       this.setImage(startImage);
       isAlive = true;
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        switchImage();
        move();
        turnTowardsMouse();
        pickupWeapon();
        shoot();
        die();
    }
    
    /**
     * Gets the movement speed of the player.
     */
    public int getSpeed()
    {
        return this.speed;
    }
    
    /**
     * Sets the movement speed of the player.
     */
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    
    /**
     * Gets the player's health.
     */
    public int getHealth()
    {
        return this.health;
    }
    
    /**
     * Sets the player's health.
     */
    public void setHealth(int health)
    {
        this.health = health;
    }
    
    /**
     * Gets the player's armor.
     */
    public int getArmor()
    {
        return this.armor;
    }
    
    /**
     * Sets the player's armor.
     */
    public void setArmor(int armor)
    {
        this.armor = armor;
    }
    
    /**
     * Get's the player's current gamescore.
     */
    public int getScore()
    {
        return this.score;
    }
    
    /**
     * Sets the player's score
     */
    public void setScore(int amount)
    {
        this.score = amount;
    }
    
    /**
     * Move the character in a direction using the arrow keys; left, right, up and down, or by using; W, A, S, D
     */
    public void move()
    {
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a"))
        {
            if (this.getX() > this.getImage().getWidth()/2)
                this.setLocation(this.getX() - speed, this.getY());
        }
        
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d"))
        {
            if (this.getX() < this.getWorld().getWidth() - this.getImage().getWidth()/2)
                this.setLocation(this.getX() + speed, this.getY());
        }
        
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))
        {
            if (this.getY() > this.getImage().getHeight()/2)
                this.setLocation(this.getX(), this.getY() - speed);
        }
        
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s"))
        {
            if (this.getY() < this.getWorld().getHeight() - this.getImage().getHeight()/2)
                this.setLocation(this.getX(), this.getY() + speed);
        }
    }
    
    /**
     * Turns the character (player) towards the mouse.
     */
    public void turnTowardsMouse()
    {
        if (Greenfoot.getMouseInfo() == null)
            return;
        
        this.turnTowards(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
    }
    
    /**
     * Get's whether or not the player is alive.
     */
    public boolean isAlive()
    {
        return this.isAlive;
    }
        
    /**
     * Picks up a weapon on the ground.
     */
    public void pickupWeapon()
    {
        Actor weapon = this.getOneIntersectingObject(Weapon.class);
        if (weapon != null)
        {   
            currentWeapon = (Weapon) weapon;
            this.getWorld().removeObject(weapon);
            switchImage();
        }        
    }
    
    /**
     * Gets the current weapon the player is using.
     */
    public Weapon getCurrentWeapon()
    {
        return currentWeapon;
    }
    
    /**
     * Shoot a bullet.
     */
    public void shoot()
    {
        // Set's the fire rate of the currently equipped weapon.
        if (actCount > 5 + currentWeapon.fireRate())
        {
            // Set space as the default key for shooting.
            if (Greenfoot.isKeyDown("space"))
            {
                if (currentWeapon instanceof Shotgun)
                {
                    for (int i = 0; i < 5; i++)
                    {
                        Bullet bullet = new Bullet();
                        bullet.setRotation(this.getRotation());
                        switch (i)
                        {
                            case 0:
                                bullet.turn(-10);
                                break;
                            case 1:
                                bullet.turn(-5);
                                break;
                            case 2:
                                bullet.turn(0);
                                break;
                            case 3:
                                bullet.turn(5);
                                break;
                            case 4:
                                bullet.turn(10);
                                break;
                            default:
                                break;
                        }
                        this.getWorld().addObject(bullet, getX(), getY());
                    }
                }
                else
                {
                    Bullet bullet = new Bullet();
                    bullet.setRotation(this.getRotation());
                    this.getWorld().addObject(bullet, getX(), getY());
                }

                actCount = 0;
                if (currentWeapon != null && !currentWeapon.sound().isPlaying())
                    currentWeapon.sound().play();
            }
        }
        actCount++;
    }
    
    /**
     * Switch image.
     */
    public void switchImage()
    {
        // Detects the instance of the weapon class that has been assigned to the currentWeapon
        // ... then an appropriate new image is assigned.
        if (currentWeapon instanceof Pistol)
        {
            this.setImage(new GreenfootImage("Player.png"));
        }
        if (currentWeapon instanceof Shotgun)
        {
            this.setImage(new GreenfootImage("Player2.png"));
        }
        if (currentWeapon instanceof MG)
        {
            this.setImage(new GreenfootImage("Player3.png"));
        }
        if (currentWeapon instanceof SMG)
        {
            this.setImage(new GreenfootImage("Player4.png"));
        }
    }
    
    public void die()
    {
        if (this.health <= 0)
        {
            Greenfoot.stop();
        }
    }
}
