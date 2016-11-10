import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class GameBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameBoard extends World
{
    private static final int WORLD_WIDTH = 800;
    private static final int WORLD_HEIGHT = 600;
    private Player player;
    private Creature creature;
    private Weapon weapon;
    private int actCount;
    ArrayList<Weapon> weaponList;
    
    /**
     * Constructor for objects of class GameBoard.
     */
    public GameBoard()
    {    
        // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1, false);
        weaponList = new ArrayList<Weapon>();
        weaponList.add(0, new Pistol());
        weaponList.add(1, new Shotgun());
        weaponList.add(2, new MG());
        weaponList.add(3, new SMG());

        // Load default settings.
        init();
        
    }
    
    /**
     * 
     */
    public void act()
    {
        /*
            this.showText(String.valueOf(player.getScore()), 15, 15);
            if (player.getHealth() == 0)
            {
                this.removeObject(player);
            }
        */

       spawnWeaponAtRandomLocation();
       removeCreatures();

    }
    
    /**
     * Initializes the default settings for the GameBoard.
     */
    public void init()
    {
        player = new Player();
        player.getImage().rotate(player.getRotation()); // Depends on image default rotation.
        this.addObject(player, WORLD_WIDTH/2, WORLD_HEIGHT/2);
    }
    
    private void spawnWeaponAtRandomLocation()
    {        
        int randomX = Greenfoot.getRandomNumber(WORLD_WIDTH);
        int randomY = Greenfoot.getRandomNumber(WORLD_HEIGHT);
        int randomSpawnTimer = Greenfoot.getRandomNumber(2000);

        if (actCount > 500 && actCount < randomSpawnTimer)
        {
            int number = Greenfoot.getRandomNumber(weaponList.size());
            weapon = weaponList.get(number);
            this.addObject(weapon, randomX, randomY);
            actCount = 0;
        }
        actCount++;
    }
        
    private void removeWeapon(Weapon weapon)
    {
        this.removeObject(weapon);
    }
    
    /**
     * Removes all creatures from the currently active GameBoard.
     */
    private void removeCreatures()
    {
        for (Creature creature : this.getObjects(Creature.class)) 
        {
            if (creature.getHealth() <= 0)
            {
                this.removeObject(creature);
            }
        }
    }
    
    /**
     * Return the player currently within the GameBoard
     */
    public Player getPlayer()
    {
        return player;
    }
}
