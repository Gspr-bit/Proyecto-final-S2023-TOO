import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstructionsSreen2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class InstructionsSreen2 extends World {

    /**
     * Constructor for objects of class InstructionsSreen2.
     */
    public InstructionsSreen2() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
    }

    public void act() {
        if (Greenfoot.isKeyDown("ENTER")) {
            Level1Intro intro = new Level1Intro();
            Greenfoot.delay(10);
            Greenfoot.setWorld(intro);
        }
    }
}
