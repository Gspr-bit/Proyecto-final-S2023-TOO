import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WorldObjects.Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    private final TileType type;
    private static final int v = 2;
    private final String imagePath;
    /**
     * Act - do whatever the WorldObjects.Tile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Tile(TileType type, String imagePath) {
        this.type = type;
        this.imagePath = imagePath;
        setImage(imagePath);
    }

    public TileType getType() {
        return type;
    }

    public String getImagePath() {
        return imagePath;
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

    public enum TileType {BUSH, DIRT, GRASS, MUD, ROCK, TREE, WATER}
}