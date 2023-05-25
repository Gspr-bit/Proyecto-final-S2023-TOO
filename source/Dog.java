import java.util.HashMap;

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
        this.v = 2;
        this.direction = Direction.RIGHT;
        this.posX = x;
        this.posY = y;
        this.pathFinder = new PathFinder(map);
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
        int j = (this.posY / Map.TILE_SIZE);
        int i = (this.posX / Map.TILE_SIZE);
        try {
            this.direction = pathFinder.findDirection(i, j);
        } catch (PathEmptyException | InvalidPointException | EndOfPathException e) {
            this.direction = pathFinder.findPath(i, j);
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
