import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ventana que se muestra antes de comenzar el nivel 2.
 *
 * @author Mau
 */
public class Level2Intro extends World {

    /**
     * Constructor for objects of class Level2Intro.
     */
    public Level2Intro() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        prepare();
    }

    /**
     * El jugador debe presionar 'enter' para continuar.
     */
    public void act() {
        if (Greenfoot.isKeyDown("ENTER")) {
            try {
                MyWorld world = new MyWorld(2);
                Greenfoot.delay(10);
                Greenfoot.setWorld(world);
            } catch (Map.WrongGenerationPercentagesException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        setBackground("LevelImages/level-2.png");
    }
}
