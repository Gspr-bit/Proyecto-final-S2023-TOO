import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pantalla 1 de instrucciones.
 *
 * @author Mau
 */
public class InstructionsScreen1 extends World {
    /**
     * Constructor for objects of class InstructionsScreen1.
     */
    public InstructionsScreen1() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        prepare();
    }

    public void act() {

        if (Greenfoot.isKeyDown("ENTER")) {
            InstructionsScreen2 world1 = new InstructionsScreen2();
            Greenfoot.delay(10);
            Greenfoot.setWorld(world1);
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        setBackground("Screens/instructions-1.png");
    }
}
