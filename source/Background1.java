import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
// hola
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Background1 extends World {
    private static Player player;
    private final Random random;
    private final Map map;
    private final ArrayList<FixedObject> fixedObjects;

    /**
     * Constructor for objects of class Background1.
     */
    public Background1() {
        // Create a new world with 640x480 cells with a cell size of 1x1 pixels.
        super(40 * Map.TILE_SIZE, 30 * Map.TILE_SIZE, 1);

        this.random = new Random(new Date().getTime());
        this.fixedObjects = new ArrayList<>();
        this.map = new Map(this.random);

        // Agregar el jugador
        player = new Player();

        // Hacer que el jugador y los objetos se muestren sobre el piso
        setPaintOrder(Player.class, FixedObject.class, Tile.class);

        player.setLocation(this.getWidth() / 2, this.getHeight() / 2);
        this.addObject(player, this.getWidth() / 2, this.getHeight() / 2);

        generateItems();
    }
    
    public ArrayList<FixedObject> getFixedObjects() {
        return this.fixedObjects;
    }

    public void act() {
        this.map.drawMap(this, player);
        drawFixedObjects();
    }

    /**
     * Genera los items que serán mostrados en el mapa
     * TODO Hacer que sólo genere objetos sobre el pasto
     * @author Gaspar
     */
    private void generateItems() {
        for (int i = 0; i < 10; i++) {
            fixedObjects.add(new Item(Effect.SLOW, 10, random.nextInt(Map.MAP_WIDTH), random.nextInt(Map.MAP_HEIGHT)));
        }
    }

    private void drawFixedObjects() {
        List<FixedObject> objectsInMap = this.getObjects(FixedObject.class);
        removeObjects(objectsInMap);

        // Pintar los objetos en el mapa
        this.fixedObjects.forEach(object -> {
            int objectPosX = object.getPosX() * Map.TILE_SIZE - player.getPosX();
            int objectPosY = object.getPosY() * Map.TILE_SIZE - player.getPosY();

            if (objectPosX >= 0 && objectPosX < getWidth() && objectPosY >= 0 && objectPosY < getHeight())
                addObject(object, objectPosX, objectPosY);
        });
    }
}
