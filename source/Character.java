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

    public abstract void changeDirection();
    
    public abstract void updateImage();
    
}
