import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ventana que se muestra antes de comenzar el nivel 3.
 *
 * @author Mau
 */
public class Level3Intro extends World {

    /**
     * Constructor for objects of class Level3Intro.
     */
    public Level3Intro() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        prepare();
    }

    public void act() {
        if (Greenfoot.isKeyDown("ENTER")) {
            try {
                MyWorld world = new MyWorld(3);
                Greenfoot.delay(10);
                Greenfoot.setWorld(world);
            } catch (WorldMap.WrongGenerationPercentagesException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        setBackground("LevelImages/level-3.png");
    }
}
