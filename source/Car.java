import greenfoot.Greenfoot;

import java.util.*;

/**
 * Write a description of class Car here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Car extends Character {
    public int startPositionY;

    /**
     * Constructor de la clase actor
     *
     * @author Montse
     */
    public Car(int x, int y) {
        this.v = 4;
        this.posX = x;
        this.posY = y;
        this.direction = Direction.DOWN;

        prepare();
    }

    /**
     * Hace el resto de cosas necesarias para inicializar la clase.
     */
    private void prepare() {
        String[] carType = {"a", "b", "c", "d", "e"};
        Random random = new Random();
        String chosenCarType = carType[random.nextInt(carType.length)];
        setImage("Cars/car-" + chosenCarType + ".png");
    }

    /**
     * Act - do whatever the Car wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (this.isTouchingPlayer() || isTouchingDog()) {
            WindowSwitcher.lose();
        }
    }

    /**
     * El carro no necesita cambiar su dirección
     */
    public void changeDirection() {
        if (this.posY >= WorldMap.MAP_HEIGHT * WorldMap.TILE_SIZE) {
            this.posY = startPositionY;
        }
    }
}
