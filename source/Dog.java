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

    private static final int distanceThreshold = 30;

    private boolean hide;

    public Dog(int x, int y, Tile[][] map) {
        this.map = map;
        this.v = 1;
        this.posX = x;
        this.posY = y;
        this.pathFinder = new PathFinder(this.map);
        this.direction = pathFinder.findPath((this.posX / Map.TILE_SIZE), (this.posY / Map.TILE_SIZE));
        this.movementStartTime = 0;
        this.hide = false;
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

        if (time == this.movementStartTime || distanceToPlayer() < distanceThreshold) {
            this.movementEndTime = time + movementDuration;
            System.out.printf("Distance to player = %d\n", distanceToPlayer());
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

    private int distanceToPlayer() {
        int pixelsDistance = Math.abs(this.posX - Background1.player.getPosX()) + Math.abs(this.posY - Background1.player.getPosY());
        return pixelsDistance / Map.TILE_SIZE; // Tiles distance
    }

    @Override
    public void updateImage() {
        // TODO
    }
}
