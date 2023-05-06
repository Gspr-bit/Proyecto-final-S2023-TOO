import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile extends Actor {
    private static final int v = 2;
    TileType type;

    /**
     * Act - do whatever the Tile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Tile(GreenfootImage image, TileType type) {
        setImage(image);
        this.type = type;
    }

//    public void act() {
//        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) {
//            move(-v);
//        }
//        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) {
//            move(v);
//        }
//        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W")) {
//            setLocation(this.getX(), this.getY() + v);
//        }
//        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S")) {
//            setLocation(this.getX(), this.getY() - v);
//        }
//
//        // Eliminar los objetos cuando están en el borde
//        if (isAtEdge()) {
//            this.getWorld().removeObject(this);
//        }
//    }

    public enum TileType {BUSH, DIRT, GRASS, MUD, ROCK, TREE, WATER}
}
