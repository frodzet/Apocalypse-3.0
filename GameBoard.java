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
    private int numCreatures;
    private int actCount;
    ArrayList<Weapon> weaponList;
    ArrayList<Creature> creatureList = new ArrayList<Creature>();
    
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
       displayPlayerInfo();
       spawnWeaponAtRandomLocation();
       handleCreatures();
    }
    
    /**
     * Initializes the default settings for the GameBoard.
     */
    public void init()
    {
        numCreatures = 3;
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
     * Removes all dead creatures from the currently active GameBoard.
     */
    private void removeCreatures()
    {
        for (Creature creature : this.getObjects(Creature.class)) 
        {   
                
            if (!creature.isAlive() && creature.timeSinceDeath() > 120)
            {
                creatureList.remove(creature);
                this.removeObject(creature);
            }
        }
    }
    
    /**
     * Spawns creatures.
     */
    private void addCreatures()
    {
        for (int i = 0; i < numCreatures; i++)
        {
            int x = 0;
            int y = Greenfoot.getRandomNumber(1000);
            int rand = Greenfoot.getRandomNumber(1000);
            creature = new Creature((20 + Greenfoot.getRandomNumber(75)), Greenfoot.getRandomNumber(20), 1 + Greenfoot.getRandomNumber(2));
            if (rand > 500)
                x = - Greenfoot.getRandomNumber(1000);
            else
                x = WORLD_WIDTH + Greenfoot.getRandomNumber(1000);

            creatureList.add(creature);
            this.addObject(creatureList.get(i), x, -y);
        }
    }
    
    /**
     * Handle's creatures on the board, by either removing them or adding them.
     */
    private void handleCreatures()
    {
        if (creatureList.isEmpty())
        {
            numCreatures++;
            addCreatures();
        }
        else
        {
            removeCreatures();
        }
    }
    
    /**
     * Return the player currently within the GameBoard
     */
    public Player getPlayer()
    {
        return player;
    }
    
    /**
     * Displays all the information about the character; Health, Score, Current Weapon, Ammunition, etc.
     */
    public void displayPlayerInfo()
    {
        // Health and Score
        this.showText("Health: " + player.getHealth(), 50, 20);
        this.showText("Score: " + player.getScore(), 50, 45);
    }
}
