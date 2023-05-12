import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile extends Actor {
    TileType type;
    boolean chocable;
    
    /**
     * Act - do whatever the Tile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Tile(GreenfootImage image, TileType type) {
        setImage(image);
        this.type = type;
        if(this.type == Tile.TileType.BUSH || this.type == Tile.TileType.DIRT || this.type == Tile.TileType.MUD  ||this.type == Tile.TileType.WATER ||this.type == Tile.TileType.TREE ||this.type == Tile.TileType.ROCK){
            this.chocable = true;
        }
        else{
            this.chocable = false;
        }
    }

    public TileType getType() {
        return type;
    }

    public enum TileType {BUSH, DIRT, GRASS, MUD, ROCK, TREE, WATER}
}
