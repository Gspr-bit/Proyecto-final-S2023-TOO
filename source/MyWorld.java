import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Mundo principal del juego.
 *
 * @author Mau, Montse, Gaspar
 */
public class MyWorld extends World {
    public static final int WORLD_WIDTH = 40 * WorldMap.TILE_SIZE;
    public static final int WORLD_HEIGHT = 30 * WorldMap.TILE_SIZE;
    private final Player player;
    private final Dog dog;
    private final Random random;
    private final WorldMap worldMap;
    private final ArrayList<FixedObject> fixedObjects;
    private final ArrayList<Thief> thieves;
    private final ArrayList<Car> cars;
    private final int level;
    private Shadow shadow;

    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld(int level) throws WorldMap.WrongGenerationPercentagesException {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);

        this.level = level;
        this.random = new Random(new Date().getTime());
        this.fixedObjects = new ArrayList<>();
        this.worldMap = new WorldMap(new Date().getTime(), level);
        this.dog = new Dog(this.getWidth() / 2 + 8, this.getHeight() / 2, worldMap.mapTiles);
        this.player = new Player();
        this.cars = new ArrayList<>();
        this.thieves = new ArrayList<>();

        prepare();
    }

    private void prepare() {
        setPaintOrder(Shadow.class, Car.class, Player.class, Dog.class, Thief.class, FixedObject.class, Tile.class);

        this.player.setLocation(this.getWidth() / 2, this.getHeight() / 2);
        this.addObject(player, this.getWidth() / 2, this.getHeight() / 2);

        try {
            this.shadow = new Shadow(level == 3 ? 14 : 0);
            this.addObject(this.shadow, this.getWidth() / 2, this.getHeight() / 2);
        } catch (InvalidShadowSizeExceptions e) {
            // No debería entrar aquí.
            throw new RuntimeException(e);
        }

        generateItems();
        generateThieves();
        if (this.level == 1)
            generateCars();
    }

    public Player getPlayer() {
        return player;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<FixedObject> getFixedObjects() {
        return this.fixedObjects;
    }

    public Shadow getShadow() {
        return shadow;
    }

    public void act() {
        this.worldMap.drawMap(this);
        drawFixedObjects();
        drawThieves();
        drawDog();
        drawCars();
        Timer.update();
        showTimer();

        // revisar si el perro ya llegó al otro lado
        if (this.dog.getPosX() + this.dog.getImage().getWidth() >= WorldMap.MAP_WIDTH * WorldMap.TILE_SIZE) {
            // reiniciar el nivel
            WindowSwitcher.showLevel(this.level);
        }

        this.cars.forEach(car -> {
            car.move();
            car.changeDirection();
        });
    }

    /**
     * Genera los items que serán mostrados en el mapa
     *
     * @author Gaspar
     */
    private void generateItems() {
        int count = 10;

        while (count > 0) {
            int x = random.nextInt(WorldMap.MAP_WIDTH);
            int y = random.nextInt(WorldMap.MAP_HEIGHT);

            if (worldMap.mapTiles[x][y].isCollidable())
                continue;

            fixedObjects.add(new Item(Effect.randomEffect(), random.nextInt(8) + 2, x, y));
            count--;
        }
    }

    /**
     * Genera a los ladrones
     *
     * @author Montse
     */
    private void generateThieves() {
        int count = 5;

        while (count > 0) {
            int x = random.nextInt(WorldMap.MAP_WIDTH);
            int y = random.nextInt(WorldMap.MAP_HEIGHT);

            if (worldMap.mapTiles[x][y].isCollidable())
                continue;

            thieves.add(new Thief(x * WorldMap.TILE_SIZE, y * WorldMap.TILE_SIZE));
            count--;
        }
    }

    private void generateCars() {
        int numberOfVerticalStreets = WorldMap.MAP_WIDTH / 16 + (WorldMap.MAP_WIDTH % 16 > 0 ? 1 : 0);

        for (int i = 0; i < 5; i++) {
            int randomStreet = random.nextInt(numberOfVerticalStreets);
            int y = random.nextInt(WorldMap.MAP_HEIGHT * WorldMap.TILE_SIZE);
            int x = randomStreet * 16 * WorldMap.TILE_SIZE + 32;

            Car car = new Car(x, y);
            car.startPositionY = 0;

            this.cars.add(car);
        }
    }

    private void drawFixedObjects() {
        List<FixedObject> objectsInMap = this.getObjects(FixedObject.class);
        removeObjects(objectsInMap);

        // Pintar los objetos en el mapa
        this.fixedObjects.forEach(object -> {
            int objectPosX = object.getPosX() * WorldMap.TILE_SIZE - player.getPosX();
            int objectPosY = object.getPosY() * WorldMap.TILE_SIZE - player.getPosY();

            if (objectPosX >= 0 && objectPosX < getWidth() && objectPosY >= 0 && objectPosY < getHeight()) {
                addObject(object, objectPosX + object.getImage().getWidth() / 2, objectPosY + object.getImage().getHeight() / 2);
            }
        });
    }

    private void drawDog() {
        removeObject(dog);

        // No dibujar al perro si está oculto
        if (dog.isHidden()) {
            dog.setImage("Dogs/dog-invisible.png");
        } else {
            dog.setImage("Dogs/dog-right-0.png");
        }

        dog.drawCharacter(this);
    }

    private void drawCars() {
        List<Car> objectsInMap = this.getObjects(Car.class);
        removeObjects(objectsInMap);

        // Pintar los carros en el mapa
        this.cars.forEach(object -> object.drawCharacter(this));
    }

    private void drawThieves() {
        List<Thief> objectsInMap = this.getObjects(Thief.class);
        removeObjects(objectsInMap);

        // Pintar los ladrones en el mapa
        this.thieves.forEach(object -> object.drawCharacter(this));
    }

    public void showTimer() {
        String s = player.getEffect() + " " + (player.getEffectEnd() - Timer.getTime());
        if (player.getEffectEnd() - Timer.getTime() == 0) {
            s = "";
        }
        if (player.getEffect() != Effect.NONE) {
            this.showText(s, MyWorld.WORLD_WIDTH / 2, 60);
        }
        this.showText("Tiempo: " + Timer.getTime(), MyWorld.WORLD_WIDTH / 2, 30);
    }
}
