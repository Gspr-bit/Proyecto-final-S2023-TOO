import java.util.*;

import java.util.Random;


/**
 * Ladrón
 * 
 * @author Montse
 */
public class Thief extends Character
{
    // Momento en que el ladrón comenzó a moverse hacia una dirección
    private int movementStart;
    // Tiempo en que el ladrón continúa moviéndose hacia la misma dirección
    // antes de cambiar de dirección.
    private final static int movementDelay = 2;
    private final Random random;

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
        
        prepare();
    }

    /**
     * Prepara las imágenes del ladrón.
     */
    private void prepare() {
        Direction[] d = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
        String[] f = {"up", "down", "left", "right"};

        this.images = new HashMap<>(4);

        // Tipos de ladrones
        String [] type = {"a", "b"};

        Random random = new Random();

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

    /**
     * Suelta un item aleatorio en su posición actual.
     */
    private void dropItem() {
        Item item = new Item(Effect.randomEffect(), 3,
                (this.posX + this.getImage().getWidth()) / WorldMap.TILE_SIZE,
                (this.posY + this.getImage().getHeight()) / WorldMap.TILE_SIZE);
        ((MyWorld)(this.getWorld())).getFixedObjects().add(item);
    }
}
