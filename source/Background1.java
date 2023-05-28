import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Background1 extends World {
    int i;
    public static Player player;
    private final Random random;
    private final Map map;
    private final ArrayList<FixedObject> fixedObjects;
    private final ArrayList<Thief> thieves;
    private final ArrayList<Car> cars;
    private final Shadow shadow;
    // Sé que podemos obtener estos valores con getWidth() y getHeight()
    // pero necesito obtenerlos desde otras clases de manera más fácil
    public static final int WORLD_WIDTH = 40 * Map.TILE_SIZE;
    public static final int WORLD_HEIGHT = 30 * Map.TILE_SIZE;

    /**
     * Constructor for objects of class Background1.
     */
    public Background1() throws WrongGenerationPercentagesException {
        // Create a new world with 640x480 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);

        this.random = new Random(new Date().getTime());
        this.fixedObjects = new ArrayList<>();
        this.map = new Map(new Date().getTime());

        // Agregar el jugador
        player = new Player();

        // Hacer que el jugador y los objetos se muestren sobre el piso
        setPaintOrder(Shadow.class, Car.class, Player.class, Thief.class, FixedObject.class, Tile.class);

        player.setLocation(this.getWidth() / 2, this.getHeight() / 2);
        this.addObject(player, this.getWidth() / 2, this.getHeight() / 2);
        
        //Generar rateros
        this.thieves = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
                thieves.add(new Thief(random.nextInt(Map.MAP_WIDTH*16), random.nextInt(Map.MAP_HEIGHT*16)));    
        }
        
        //Generar carros
        this.cars=new ArrayList<>();
        //this.shadowsCars=new ArrayList<>();
        for (int i = 0; i < 25; i++) {
                int x=random.nextInt(Map.MAP_WIDTH*16);
                int y=random.nextInt(Map.MAP_HEIGHT*16);
                Car carro = new Car(x,y);
                carro.xOriginal=x;
                cars.add(carro);    
                
                //shadow car
                //shadowsCars.add(new ShadowCar(x,y));
        }

        // Agrega el objeto sombra sin poner sombra por el momento
        try {
            this.shadow = new Shadow(0);
            this.addObject(this.shadow, this.getWidth() / 2, this.getHeight() / 2);
        } catch (InvalidShadowSizeExceptions e) {
            // No debería entrar aquí.
            throw new RuntimeException(e);
        }

        //map.generateCountryMap();
        map.generateCityMap();
        generateItems();
    }
    
    public ArrayList<FixedObject> getFixedObjects() {
        return this.fixedObjects;
    }
    public ArrayList<Thief> getThiefs() {
        return this.thieves;
    }
    public Shadow getShadow() {
        return shadow;
    }

    public void act() {
        this.map.drawMap(this);
        drawFixedObjects();
        drawThiefs();
        drawCars();
        Timer.update();
    }

    /**
     * Genera los items que serán mostrados en el mapa
     * @author Gaspar
     */
    private void generateItems() {
        int count = 10;

        while (count > 0) {
            int x = random.nextInt(Map.MAP_WIDTH);
            int y = random.nextInt(Map.MAP_HEIGHT);

            if (map.mapTiles[x][y].isCollidable())
                continue;

            fixedObjects.add(new Item(Effect.SLOW, random.nextInt(8)+2, x, y));
            count --;
        }
    }

    private void drawFixedObjects() {
        List<FixedObject> objectsInMap = this.getObjects(FixedObject.class);
        removeObjects(objectsInMap);

        // Pintar los objetos en el mapa
        this.fixedObjects.forEach(object -> {
            int objectPosX = object.getPosX() * Map.TILE_SIZE - player.getPosX();
            int objectPosY = object.getPosY() * Map.TILE_SIZE - player.getPosY();
            int unidad;

            if (objectPosX >= 0 && objectPosX < getWidth() && objectPosY >= 0 && objectPosY < getHeight()){
                addObject(object, objectPosX, objectPosY);    
            }
                
        });
    }
    private void drawCars() {
        List<Car> objectsInMap = this.getObjects(Car.class);
        removeObjects(objectsInMap);
        
        // Pintar los carros en el mapa
        this.cars.forEach(object -> {
            int objectPosX = object.getPosX() - player.getPosX();
            int objectPosY = object.getPosY() - player.getPosY();
                 
       if (objectPosX >= 0 && objectPosX < getWidth() && objectPosY >= 0 && objectPosY < getHeight()){
                addObject(object, objectPosX+Map.TILE_SIZE/2, objectPosY+Map.TILE_SIZE/2);
                if(!object.puedoIniciarAqui()){
                    removeObject(object);
                }
            }
        });
    }
    
    private void drawThiefs() {
        List<Thief> objectsInMap = this.getObjects(Thief.class);
        removeObjects(objectsInMap);

        // Pintar los ladrones en el mapa
        this.thieves.forEach(object -> {
            int objectPosX = object.getPosX() - player.getPosX();
            int objectPosY = object.getPosY() - player.getPosY();

            if (objectPosX >= 0 && objectPosX < getWidth() && objectPosY >= 0 && objectPosY < getHeight())
                addObject(object, objectPosX+Map.TILE_SIZE/2, objectPosY+Map.TILE_SIZE/2);
        });
    }
}
