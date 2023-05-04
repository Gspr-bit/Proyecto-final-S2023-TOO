import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    private int v = 2;
    /**
     * Act - do whatever the Tile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Tile(GreenfootImage image) {
        setImage(image);
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) {
            move(-v);
        }
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) {
            move(v);
        }
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W")) {
            setLocation(this.getX(), this.getY() + v);
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S")) {
            setLocation(this.getX(), this.getY() - v);
        }
    }
}
