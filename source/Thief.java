import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.Random;


/**
 * Write a description of class Thief here.
 * 
 * @author Montse
 */
public class Thief extends Character
{
    private char thiefType;
    // Momento en que el ladrón comenzó a moverse hacia una dirección
    private int movementStart;
    // Tiempo en que el ladrón continúa moviendose hacia la misma dirección
    // antes de cambiar de dirección.
    private final static int movementDelay = 2;
    private Random random;

    /**
    *Constructor clase Thief
    *@author Montse
    */
    public Thief(int x, int y){
        this.movementStart = 0;
        this.v = 1;
        this.direction = Direction.RIGHT;
        this.posX=x;
        this.posY=y;
        this.random = new Random((long) x*y);
        
        Direction[] d = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
        String[] f = {"up", "down", "left", "right"};

        this.images = new HashMap<>(4);
        
        //Para q haya varios tipos de ladrones, ahorita puse esos 2 unicamente como ejemplo
        String [] type = {"a", "b"};
        
        Random random = new Random();
        if(random.nextInt(2)==0){
            this.thiefType='a';
        }else{
            this.thiefType='b';
        }
        
        //Establecer las imágenes
        for (int i = 0; i < 4; i++) {
            images.put(d[i], new ArrayList<>(2));
            
            String chosenTipo = type[random.nextInt(type.length)];
            for (int j = 0; j < 2; j++) {
                
                images.get(d[i]).add("Thief/"+ chosenTipo +"-"+ f[i] + "-" + j + ".png");
                
                
            }
        }

        this.imageTimer = 0;
        
    }
    
    /**
     * Act - do whatever the Thief wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (isTouchingPlayer()) {
            dropItem();
        }

        updateImage();
        changeDirection();
        move();
    }
    
    
    /**
     * Cambiar la imagen del jugador para que parezca como si se estuviera moviendo
     * TODO Agregar las animaciones para cuando el jugador está quieto
     * TODO Mover esta función a su superclase
     * @author Gaspar
     */
    public void updateImage() {
        setImage(images.get(this.direction).get(imageTimer / UPDATE_RATE));

        this.imageTimer++;
        if (this.imageTimer / UPDATE_RATE >= images.get(this.direction).size())
            this.imageTimer = 0;
    }

    /**
     * Función que mueve al ladrón
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

    /**
     * Función que cambia la dirección del ladrón aleatoriamente cada 2 segundos
     * o cuando choque con un objeto.
     *
     * @author Montse
     */
    @Override
    public void changeDirection() {
        // Determinar si el momento en que debe de cambiar su dirección ha llegado.
        // O si está a punto de chocar
        if (Timer.getTime() >= this.movementStart + movementDelay ||
            !canMoveTowards(this.direction))
        {
            this.movementStart = Timer.getTime();
            this.direction = Direction.values()[random.nextInt(4)];
        }
    }

    private boolean isTouchingPlayer() {
        return !this.getObjectsInRange(Map.TILE_SIZE, Player.class).isEmpty();
    }

    private void dropItem() {
        Item item = new Item(Effect.randomEffect(), 3, (this.posX + this.getImage().getWidth()) / Map.TILE_SIZE, (this.posY + this.getImage().getHeight()) / Map.TILE_SIZE);
        ((MyWorld)(this.getWorld())).getFixedObjects().add(item);
    }
}
