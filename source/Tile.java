import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile extends Actor {
    TileType type;
    boolean collidable;
    
    /**
     * Act - do whatever the Tile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Tile(GreenfootImage image, TileType type) {
        setImage(image);
        this.type = type;
        this.collidable =
                this.type == TileType.BUSH ||
                this.type == TileType.DIRT ||
                this.type == TileType.MUD ||
                this.type == TileType.WATER ||
                this.type == TileType.TREE ||
                this.type == TileType.ROCK;
    }

    public TileType getType() {
        return type;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public enum TileType {BUSH, DIRT, GRASS, MUD, ROCK, TREE, WATER}
}
