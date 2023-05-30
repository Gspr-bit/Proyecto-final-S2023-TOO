import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level3Intro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level3Intro extends World
{

    /**
     * Constructor for objects of class Level3Intro.
     * 
     */
    public Level3Intro()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        setBackground("LevelImages/level-3.png");
    }

    public void act() {
        if (Greenfoot.isKeyDown("ENTER")) {
            try {
                MyWorld world = new MyWorld(3);
                Greenfoot.delay(10);
                Greenfoot.setWorld(world);
            } catch (WrongGenerationPercentagesException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
