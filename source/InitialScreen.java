import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntialScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InitialScreen extends World
{

    /**
     * Constructor for objects of class InitialScreen.
     * 
     */
     public InitialScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600,400,1); 
    }
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            //Background1 world = new Background1();
            //Greenfoot.setWorld(world);
        }
    }
}
