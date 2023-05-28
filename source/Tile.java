import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 *
 * @author Gaspar
 */
public class Tile extends Actor {
    private final TileType type;
    private final boolean collidable;

    public Tile(GreenfootImage image, TileType type) {
        setImage(image);
        this.type = type;
        this.collidable =
                this.type == TileType.BUSH ||
                        this.type == TileType.WATER ||
                        this.type == TileType.TREE ||
                        this.type == TileType.ROCK ||
                        this.type == TileType.WALL ||
                        this.type == TileType.ROOF;
    }

    public TileType getType() {
        return type;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public enum TileType {BUSH, DIRT, GRASS, MUD, ROCK, TREE, WATER, PAVEMENT, WALL, ROOF}
}
