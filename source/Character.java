import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.Function;

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

    protected int imageTimer;

    protected Map<Direction, ArrayList<String>> images;

    protected Direction direction;

    // Update rate of the image FPS/UPDATE_RATE
    protected static final int UPDATE_RATE = 10;  // 6 images per second

    /**
     * Act - do whatever the character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        changeDirection();
        collide();
        updateImage();
    }

    public abstract void changeDirection();

    public abstract void collide();
    
    public abstract void updateImage();
}
