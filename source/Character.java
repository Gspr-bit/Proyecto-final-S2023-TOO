import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.Function;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clase padre abstracta para todos los personajes móviles del juego
 *
 * @author Gaspar
 */
public abstract class Character extends Actor {
    // Velocidad del personaje
    protected int v;

    protected int imageTimer;

    protected Map<Direction, ArrayList<String>> images;

    protected Direction direction;

    // Update rate of the image FPS/UPDATE_RATE
    protected static final int UPDATE_RATE = 10;  // 6 images per second
    
    // Posición del character en el mapa
    protected int posX;
    protected int posY;
    
    //TIEMPO Q LE VAMOS A DAR AL NIVEL
    protected int tiempoTotal=3600;//esta en minitos

    /**
     * Act - do whatever the character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        //changeDirection();
        //updateImage();
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public abstract void changeDirection();
    
    public abstract void updateImage();

    /**
     *Método para que los carros corran siempre de derecha izquierda
     *@Montse
     */
    public void move(){
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
     * Método para saber si el jugador puede moverse hacia la posición dada.
     * @author Mauricio, Gaspar, Montse
     * @param direction Dirección hacia donde se quiere mover el jugador.
     * @return true si el jugador se puede mover hacia allá
     */
    protected boolean canMoveTowards(Direction direction) {
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
}
