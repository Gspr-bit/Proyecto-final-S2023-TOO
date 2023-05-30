import java.util.ArrayList;
import java.util.Map;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase padre abstracta para todos los personajes móviles del juego
 *
 * @author Gaspar
 */
public abstract class Character extends Actor {
    // Update rate of the image FPS/UPDATE_RATE
    protected static final int UPDATE_RATE = 10;  // 6 images per second
    // Velocidad del personaje
    protected int v;
    protected int imageTimer;
    protected Map<Direction, ArrayList<String>> images;
    protected Direction direction;
    // Posición del character en el mapa
    protected int posX;
    protected int posY;

    /**
     * Act - do whatever the character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public abstract void act();

    /**
     * Regresa la posición en píxeles en X del personaje.
     * La posición (x, y) comienza en (0, 0). X se incrementa hacia la derecha.
     * Esta no es la posición en la ventana, sino en el mapa.
     *
     * @return La posición en X del personaje
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Regresa la posición en píxeles en Y del personaje.
     * La posición (x, y) comienza en (0, 0). Y se incrementa hacia abajo.
     * Esta no es la posición en la ventana, sino en el mapa.
     *
     * @return La posición en Y del personaje
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Cambia la dirección del personaje
     */
    public abstract void changeDirection();

    /**
     * Mueve al personaje de acuerdo a su dirección y velocidad.
     *
     * @author Montse, Gaspar
     */
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
            case NONE:
                break;
        }
    }

    /**
     * Método para saber si el personaje puede moverse hacia la posición dada.
     *
     * @param direction Dirección hacia donde se quiere mover el jugador.
     * @return true si el jugador se puede mover hacia allá
     * @author Mauricio, Gaspar, Montse
     */
    protected boolean canMoveTowards(Direction direction) {
        // Siempre se puede quedar donde ya está
        if (direction == Direction.NONE) return true;

        int dx = this.getImage().getWidth() / 2 + v;
        int dy = this.getImage().getHeight() / 2 + v;

        // UP, DOWN, LEFT, RIGHT
        int[] dxs = {0, 0, -dx, dx};
        int[] dys = {-dy, dy, 0, 0};

        Tile nextTile = (Tile) this.getOneObjectAtOffset(dxs[direction.ordinal()],
                dys[direction.ordinal()], Tile.class);

        Character nextCharacter = (Character) this.getOneObjectAtOffset(dxs[direction.ordinal()],
                dys[direction.ordinal()], Character.class);

        return nextTile != null && nextCharacter == null && !nextTile.isCollidable();
    }

    /**
     * Cambiar la imagen del jugador para que parezca como si se estuviera moviendo
     *
     * @author Gaspar
     */
    public void updateImage() {
        if (this.direction == Direction.NONE) return;

        setImage(images.get(this.direction).get(imageTimer / UPDATE_RATE));

        this.imageTimer++;
        if (this.imageTimer / UPDATE_RATE >= images.get(this.direction).size())
            this.imageTimer = 0;
    }

    /**
     *
     * @return si el ladrón está tocando al jugador.
     */
    public boolean isTouchingPlayer() {
        return this.getOneIntersectingObject(Player.class) != null;
    }

    /**
     *
     * @return si el ladrón está tocando al perro.
     */
    public boolean isTouchingDog() {
        return this.getOneIntersectingObject(Dog.class) != null;
    }

    public void drawCharacter(MyWorld world) {
        int objectPosX = this.getPosX() - world.getPlayer().getPosX();
        int objectPosY = this.getPosY() - world.getPlayer().getPosY();

        if (objectPosX >= 0 && objectPosX < world.getWidth() && objectPosY >= 0 && objectPosY < world.getHeight()) {
            world.addObject(this, objectPosX + WorldMap.TILE_SIZE / 2, objectPosY + WorldMap.TILE_SIZE / 2);
        }
    }
}
