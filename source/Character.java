import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class character here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Character extends Actor {
    // Velocidad del personaje
    protected int v;

    /**
     * Act - do whatever the character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        changeDirection();
        collide();
    }

    public abstract void changeDirection();

    public abstract void collide();
}
