import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pantalla 2 de instrucciones.
 *
 * @author Mau
 */
public class InstructionsScreen2 extends World {

    /**
     * Constructor for objects of class InstructionsScreen2.
     */
    public InstructionsScreen2() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        prepare();
    }

    public void act() {
        if (Greenfoot.isKeyDown("ENTER")) {
            Level1Intro intro = new Level1Intro();
            Greenfoot.delay(10);
            Greenfoot.setWorld(intro);
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        setBackground("Screens/instructions-2.png");
    }
}
