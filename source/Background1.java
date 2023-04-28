import java.util.Date;
import java.util.Random;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Background1 extends World
{

    /**
     * Constructor for objects of class Background1.
     * 
     */
    public Background1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        generateBackground();
    }

    private void generateBackground() {
        Random random = new Random(new Date().getTime());

        int TILE_SIZE = 16;
        int TILE_AMOUNT = 13;

        for (int y = 0; y < getHeight(); y += TILE_SIZE) {
            for (int x = 0; x < getWidth(); x+= TILE_SIZE) {
                int imageIndex = random.nextInt(TILE_AMOUNT);
                GreenfootImage image = new GreenfootImage("MapTiles/grass-" + imageIndex + ".png");

                addObject(new Tile(image), x, y);
            }
        }
    }
}
