import java.util.ArrayList;
import java.util.HashMap;

import greenfoot.Greenfoot;


/**
 * Write a description of class Dog here.
 *
 * @author Gaspar
 */
public class Dog extends Character {
    private static final int movementDuration = 1;
    private static final int idleDuration = 3;
    // Límite de distancia a la que se puede acercar el jugador antes de que el perro corra de nuevo
    private static final int DISTANCE_THRESHOLD = 4 * Map.TILE_SIZE;
    private final Tile[][] map;
    private final PathFinder pathFinder;
    private int movementStartTime;
    private int movementEndTime;
    private boolean hide;

    public Dog(int x, int y, Tile[][] map) {
        this.map = map;
        this.v = 2;
        this.posX = x;
        this.posY = y;
        this.pathFinder = new PathFinder(this.map);
        this.movementStartTime = 0;
        this.hide = false;
        this.direction = Direction.RIGHT;

        prepare();
    }

    /**
     * Hace el resto de cosas necesarias para inicializar la clase.
     */
    private void prepare() {
        Direction[] d = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
        String[] f = {"up", "down", "left", "right"};

        this.images = new HashMap<>(4);

        //Establecer las imágenes
        for (int i = 0; i < 4; i++) {
            images.put(d[i], new ArrayList<>(3));

            for (int j = 0; j < 3; j++) {

                images.get(d[i]).add("Dogs/dog-" + f[i] + "-" + j + ".png");


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
            WindowSwitcher.nextLevel(((MyWorld) getWorld()).getLevel());
        }

        if (isTouchingPlayer()) {
            WinScreen winScreen = new WinScreen();
            Greenfoot.setWorld(winScreen);
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

        if (time == this.movementStartTime || isNearToPlayer()) {
            this.movementEndTime = time + movementDuration;
        } else if (time == this.movementEndTime) {
            this.movementStartTime = time + idleDuration;
        }

        if (time > this.movementEndTime && time < this.movementStartTime) {
            this.direction = Direction.NONE;
            this.hide = canHide();
            return;
        }

        this.hide = false;
    }

    private boolean canHide() {
        int i = (this.posX / Map.TILE_SIZE);
        int j = (this.posY / Map.TILE_SIZE);

        int[] dis = {0, 0, 1, -1};
        int[] djs = {1, -1, 0, 0};

        for (int k = 0; k < 4; k++)
            if (map[i + dis[k]][j + djs[k]].isCollidable())
                return true;

        return false;
    }

    private boolean isNearToPlayer() {
        return !this.getObjectsInRange(DISTANCE_THRESHOLD, Player.class).isEmpty();
    }

    private boolean isTouchingThief() {
        return !this.getObjectsInRange(Map.TILE_SIZE, Thief.class).isEmpty();
    }
}
