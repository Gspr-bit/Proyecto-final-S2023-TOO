import greenfoot.*;

/**
 * Cuadros por los que está formado el mapa.
 *
 * @author Gaspar
 */
public class Tile extends Actor {
    private final TileType type;
    private final boolean collidable;

    public Tile(GreenfootImage image, TileType type) {
        setImage(image);
        this.type = type;
        // Tipos de tile donde no se puede pisar.
        this.collidable =
                this.type == TileType.BUSH ||
                        this.type == TileType.WATER ||
                        this.type == TileType.TREE ||
                        this.type == TileType.ROCK ||
                        this.type == TileType.WALL ||
                        this.type == TileType.ROOF;
    }

    /**
     * Regresa el tipo de piso.
     * @return Tipo de piso
     */
    public TileType getType() {
        return type;
    }

    /**
     * Regresa si no se puede pisar sobre este Tile.
     * @return 'True' si no se puede pisar en este Tile. 'False' de otro modo
     */
    public boolean isCollidable() {
        return collidable;
    }

    public enum TileType {BUSH, DIRT, GRASS, MUD, ROCK, TREE, WATER, PAVEMENT, WALL, ROOF}
}
