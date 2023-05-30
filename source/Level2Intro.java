import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level2Intro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level2Intro extends World
{

    /**
     * Constructor for objects of class Level2Intro.
     * 
     */
    public Level2Intro()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        setBackground("LevelImages/level-2.png");
    }

    public void act() {
        if (Greenfoot.isKeyDown("ENTER")) {
            try {
                MyWorld world = new MyWorld(2);
                Greenfoot.delay(10);
                Greenfoot.setWorld(world);
            } catch (WrongGenerationPercentagesException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
