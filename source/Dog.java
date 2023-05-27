import java.util.HashMap;
import java.util.spi.AbstractResourceBundleProvider;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dog here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dog extends Character {
    private Tile[][] map;
    private PathFinder pathFinder;

    public Dog(int x, int y, Tile[][] map) {
        this.v = 1;
        this.posX = x;
        this.posY = y;
        this.pathFinder = new PathFinder(map);
        this.direction = pathFinder.findPath((this.posX / Map.TILE_SIZE), (this.posY / Map.TILE_SIZE));
    }

    /**
     * Act - do whatever the Dog wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        changeDirection();
        move();
    }

    @Override
    public void changeDirection() {
        // Solo cambia su dirección cuando está justo en medio de un Tile
        // De otra manera puede suceder un desfase extraño

        if (this.posX % Map.TILE_SIZE != 0 || this.posY % Map.TILE_SIZE != 0)
            return;

        int j = (this.posY / Map.TILE_SIZE);
        int i = (this.posX / Map.TILE_SIZE);
        try {
            this.direction = pathFinder.findDirection(i, j);
        } catch (PathEmptyException | InvalidPointException | EndOfPathException e) {
            this.direction = pathFinder.findPath(i, j);
            System.err.println("Advertencia: El camino ha sido recalculado, esto puede causar problemas de rendimiento");
        }
    }

    /**
     * Función que mueve al ladrón
     *
     * @author Montse
     */
    private void move() {
        switch (this.direction) {
            case UP: {
                this.posY -= v;
                break;
            }
            case DOWN: {
                this.posY += v;
                break;
            }
            case LEFT: {
                this.posX -= v;
                break;
            }
            case RIGHT: {
                this.posX += v;
                break;
            }
        }
    }

    @Override
    public void updateImage() {
        // TODO
    }
}
