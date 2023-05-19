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


    // Dimensiones de cada Tile en pixeles
    private static final int TILE_SIZE = 16;
    // Dimensiones del mapa en Tiles
    private static final int MAP_WIDTH = 160;
    private static final int MAP_HEIGHT = 30;
    private static Player player;
    private final Random random;
    private final Tile[][] mapTiles;
    private final Tile.TileType[] tileTypes = {Tile.TileType.BUSH, Tile.TileType.DIRT, Tile.TileType.GRASS,
            Tile.TileType.MUD, Tile.TileType.ROCK, Tile.TileType.TREE, Tile.TileType.WATER};
    // Tipos que puede haber
    private final String[] tilePaths = {"bush", "dirt", "grass", "mud", "rock", "tree", "water"};
    // Total de imágenes de cada tipo
    private final int[] tileAmounts = {1, 1, 11, 1, 1, 1, 1};
    // Porcentaje total de apariciones de cada tipo. Deben sumar 100. Si no suman 100 no dibuja el fondo.
    private final int[] percentages = {20, 2, 70, 2, 2, 2, 2};
    private ArrayList<FixedObject> fixedObjects;
    private ArrayList<Thief> thiefs;

    /**
     * Constructor for objects of class Background1.
     */
    public Background1() {
        // Create a new world with 640x480 cells with a cell size of 1x1 pixels.
        super(40 * TILE_SIZE, 30 * TILE_SIZE, 1);

        this.random = new Random(new Date().getTime());
        this.mapTiles = new Tile[MAP_WIDTH][MAP_HEIGHT];

        // Calcular la sumatoria de los porcentajes
        for (int i = 0; i < percentages.length - 1; i++)
            percentages[i + 1] += percentages[i];

        // Los porcentajes deben sumar 100
        // JVM deshabilita los assert y no hay manera de habilitarlos en greenfoot !&#*"#
        // Entonces en lugar de detener el programa simplemente no dibujará ningún fondo
        // Si no está dibujando el fondo deberías checar cuánto suman los porcentajes
        // assert (percentages[percentages.length -1] == 100);
        if (percentages[percentages.length - 1] != 100)
            return;

        // Agregar el jugador
        player = new Player();
        player.setLocation(this.getWidth() / 2, this.getHeight() / 2);
        this.addObject(player, this.getWidth() / 2, this.getHeight() / 2);
        // Hacer que el jugador Y OBJETOSse muestre sobre el piso
        setPaintOrder(Player.class, Thief.class, FixedObject.class, Tile.class);

        generateBackground();

        fixedObjects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
                fixedObjects.add(new Item(Effect.SLOW, 10, random.nextInt(MAP_WIDTH), random.nextInt(MAP_HEIGHT)));    
        }
        thiefs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
                thiefs.add(new Thief(random.nextInt(MAP_WIDTH), random.nextInt(MAP_HEIGHT)));    
        }
    }
    
    public ArrayList<FixedObject> getFixedObjects() {
        return this.fixedObjects;
    }
    public ArrayList<Thief> getThiefs() {
        return this.thiefs;
    }

    public void act() {
        drawMap();
        drawFixedObjects();
        drawThiefs();
    }

    /**
     * Genera un fondo aleatorio
     */
    private void generateBackground() {
        // Llenar el mapa
        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                int imageType = getRandomType();
                int imageIndex = random.nextInt(tileAmounts[imageType]);

                GreenfootImage image = new GreenfootImage("MapTiles/" + tilePaths[imageType] + "-" + imageIndex + ".png");

                mapTiles[x][y] = new Tile(image, tileTypes[imageType]);
                
            }
        }
    }

    private void drawFixedObjects() {
        List<FixedObject> objectsInMap = this.getObjects(FixedObject.class);
        removeObjects(objectsInMap);

        // Pintar los objetos en el mapa
        this.fixedObjects.forEach(object -> {
            int objectPosX = object.getPosX() * TILE_SIZE - player.getPosX();
            int objectPosY = object.getPosY() * TILE_SIZE - player.getPosY();

            if (objectPosX >= 0 && objectPosX < getWidth() && objectPosY >= 0 && objectPosY < getHeight()){
                
                //if(object.puedoIrAqui()){ le puse un FIXME a ese metodo, x eso lo comento
                    addObject(object, objectPosX, objectPosY);    
                //}
            }
                
        });
    }
    
    private void drawThiefs() {
        List<Thief> objectsInMap = this.getObjects(Thief.class);
        removeObjects(objectsInMap);

        // Pintar los ladrones en el mapa
        this.thiefs.forEach(object -> {
            int objectPosX = object.getPosX() * TILE_SIZE - player.getPosX();
            int objectPosY = object.getPosY() * TILE_SIZE - player.getPosY();

            if (objectPosX >= 0 && objectPosX < getWidth() && objectPosY >= 0 && objectPosY < getHeight())
                addObject(object, objectPosX, objectPosY);
        });
    }

    private void drawMap() {
        // Quita todos los anteriores porque los va a volver a dibujar

        // Guarda todos los objetos del tipo Tile en una lista
        List<Tile> tiles = getObjects(Tile.class);
        // Quita todos los tiles del mapa
        removeObjects(tiles);

        // Calcula el índice de la matriz a partir de donde comenzará a dibujar los tiles
        // La posición del jugador está dada en pixeles, entonces debemos dividir entre el tamaño
        // en pixeles de cada Tile para saber la posición de la matriz.

        // Esto es, si por ejemplo el jugador se encuentra en (20, 40)
        // Dividimos entre el tamaño de cada Tile (16) y queda que deberemos comenzar a dibujar
        // a partir del Tile (1, 2)
        int startTileX = player.getPosX() / TILE_SIZE;
        int startTileY = player.getPosY() / TILE_SIZE;

        // Para calcular en qué tile de la matriz vamos a terminar de dibujar
        // simplemente calculamos cuántos tiles caben a lo largo y lo ancho de la ventana
        // obteniendo su tamaño en pixeles y dividiendo entre el tamaño de cada tile.
        int endTileX = startTileX + getWidth() / TILE_SIZE;
        int endTileY = startTileY + getHeight() / TILE_SIZE;

        // Volviendo a startTileX y startTileY. Podemos mirar que en el ejemplo sobran 4 y 8 pixeles
        // Debemos tomarlos en cuenta porque si no se va mirar como si fuera saltando cada 16 pixeles
        // en lugar de como si se fuera recorriendo
        int offSetX = player.getPosX() % TILE_SIZE;
        int offSetY = player.getPosY() % TILE_SIZE;

        // Variables donde vamos a guardar dónde debe ir cada tile
        // Su posición debe ir guardada en pixeles.
        // Sus posiciones las vamos ir moviendo junto a 'x' y 'y'
        // Comenzamos con TILE_SIZE - 8 porque si comenzamos con 0 se tapa la mitad del tile
        int tilePosX = TILE_SIZE / 2;
        int tilePosY;

        // Iteramos por la matriz desde startTileX hasta endTileX (Renglones)
        for (int x = startTileX; x < endTileX; tilePosX += TILE_SIZE, x++) {
            tilePosY = TILE_SIZE / 2;
            // Iteramos por la matriz desde startTileY hasta endTileY (Columnas)
            for (int y = startTileY; y < endTileY; tilePosY += TILE_SIZE, y++) {
                // Checamos que x y y se encuentren dentro de los límites de la matriz
                if (x < 0 || x >= MAP_WIDTH || y < 0 || y >= MAP_HEIGHT)
                    continue;

                // Agregamos al tile en la posición correspondiente
                // Aquí se puede observar cómo le restamos el residuo que calculamos anteriormente
                // (No me preguntes por qué hay que restarlo en lugar de sumarlo)
                addObject(mapTiles[x][y], tilePosX - offSetX, tilePosY - offSetY);
            }
        }
    }


    /**
     * Regresa un tipo de piso de acuerdo a los porcentajes de probabilidad dados
     *
     * @return int que indica qué tipo de piso deberá ser usado.
     */
    private int getRandomType() {
        int randomPercentage = random.nextInt(100);

        for (int i = 0; i < percentages.length; i++) {
            if (randomPercentage <= percentages[i]) {
                return i;
            }
        }

        // Should not get here
        return -1;
    }
}
