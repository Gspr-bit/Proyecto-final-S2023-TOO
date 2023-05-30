import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import greenfoot.Greenfoot;

/**
 * Write a description of class Dog here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dog extends Character {
    private final Tile[][] map;
    private final PathFinder pathFinder;

    private static final int movementDuration = 3;
    private static final int idleDuration = 3;
    private int movementStartTime;
    private int movementEndTime;

    private static final int distanceThreshold = 4 * Map.TILE_SIZE;

    private boolean hide;

    public Dog(int x, int y, Tile[][] map) {
        this.map = map;
        this.v = 2;
        this.posX = x;
        this.posY = y;
        this.pathFinder = new PathFinder(this.map);
        this.direction = pathFinder.findPath((this.posX / Map.TILE_SIZE), (this.posY / Map.TILE_SIZE));
        this.movementStartTime = 0;
        this.hide = false;

        this.direction = Direction.RIGHT;
        Direction[] d = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
        String[] f = {"up", "down", "left", "right"};

        this.images = new HashMap<>(4);

        //Establecer las imágenes
        for (int i = 0; i < 4; i++) {
            images.put(d[i], new ArrayList<>(3));

            for (int j = 0; j < 3; j++) {

                images.get(d[i]).add("Dogs/dog-"+ f[i] + "-" + j + ".png");


            }
        }
    }

    public boolean isHidden() {
        return hide;
    }

    /**
     * Act - do whatever the Dog wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        changeDirection();
        move();
        updateImage();

        if (isTouchingThief()) {
            WindowSwitcher.nextLevel(((MyWorld)getWorld()).getLevel());
        }
    }



    @Override
    public void changeDirection() {
        // Solo cambia su dirección cuando está justo en medio de un Tile
        // De otra manera puede suceder un desfase extraño
        if (this.posX % Map.TILE_SIZE != 0 || this.posY % Map.TILE_SIZE != 0)
            return;

        int i = (this.posX / Map.TILE_SIZE);
        int j = (this.posY / Map.TILE_SIZE);
        try {
            this.direction = pathFinder.findDirection(i, j);
        } catch (PathEmptyException | InvalidPointException | EndOfPathException e) {
            this.direction = pathFinder.findPath(i, j);
            System.err.println("Advertencia: El camino ha sido recalculado, esto puede causar problemas de rendimiento");
        }

        // Cambia su dirección solo cuando es momento
        int time = Timer.getTime();
        if (time > this.movementEndTime && time < this.movementStartTime) {
            this.direction = Direction.NONE;
            this.hide = canHide();
            return;
        }

        if (time == this.movementStartTime || isNearToPlayer()) {
            this.movementEndTime = time + movementDuration;
        } else if (time == this.movementEndTime) {
            this.movementStartTime = time + idleDuration;
        }

        this.hide = false;
    }

    /**
     * Función que mueve al ladrón
     *
     * @author Montse
     */
    @Override
    public void move() {
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
            case NONE: {
                // No hacer nada
                break;
            }
        }
    }

    private boolean canHide() {
        int i = (this.posX / Map.TILE_SIZE);
        int j = (this.posY / Map.TILE_SIZE);

        int [] dis = {0, 0, 1, -1};
        int [] djs = {1, -1, 0, 0};

        for (int k = 0; k < 4; k++)
            if (map[i+dis[k]][j+djs[k]].isCollidable())
                return true;

        return false;
    }

    private boolean isNearToPlayer() {
        return !this.getObjectsInRange(distanceThreshold, Player.class).isEmpty();
    }

    private boolean isTouchingThief() {
        return !this.getObjectsInRange(Map.TILE_SIZE, Thief.class).isEmpty();
    }

    /**
     * Cambiar la imagen del jugador para que parezca como si se estuviera moviendo
     * TODO Agregar las animaciones para cuando el jugador está quieto
     * TODO Mover esta función a su superclase
     * @author Gaspar
     */
    public void updateImage() {
        if (this.direction == Direction.NONE) return;

        setImage(images.get(this.direction).get(imageTimer / UPDATE_RATE));

        this.imageTimer++;
        if (this.imageTimer / UPDATE_RATE >= images.get(this.direction).size())
            this.imageTimer = 0;
    }
}
