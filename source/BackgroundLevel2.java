import java.util.Date;
import java.util.Random;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WorldObjects.BackgroundLevel2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BackgroundLevel2 extends World
{
    private static final int TILE_SIZE = 16;

    private final Random random;

    private final static int MAP_WIDTH = 1000;
    private final static int MAP_HEIGHT = 1000;
    private Tile [][] mapInfo;

    private Player player;

    private final Tile.TileType [] tileTypes = {Tile.TileType.BUSH,
            Tile.TileType.DIRT,
            Tile.TileType.GRASS,
            Tile.TileType.MUD,
            Tile.TileType.ROCK,
            Tile.TileType.TREE,
            Tile.TileType.WATER};
    // Tipos que puede haber
    private final String [] tilePaths = {"bush", "dirt", "grass", "mud", "rock", "tree", "water"};
    // Total de imágenes de cada tipo
    private final int [] tileAmounts  = {     1,      1,      11,     1,      1,      1,       1};
    // Porcentaje total de apariciones de cada tipo. Deben sumar 100. Si no suman 100 no dibuja el fondo.
    private final int [] percentages  = {    20,      2,      70,     2,      2,      2,       2};

    /**
     * Constructor for objects of class WorldObjects.BackgroundLevel2.
     * 
     */
    public BackgroundLevel2()
    {    
        // Create a new world with 640x480 cells with a cell size of 1x1 pixels.
        super(640, 480, 1);
        this.player = new Player();
        this.player.setLocation(getWidth() / 2, getHeight() / 2);
        setPaintOrder(Player.class, Tile.class);
        
        addObject(player, getWidth() / 2, getHeight() / 2);

        this.random = new Random(new Date().getTime());
        this.mapInfo = new Tile[MAP_WIDTH][MAP_HEIGHT];


        // Calcular la sumatoria de los porcentajes
        for (int i = 0; i < percentages.length - 1; i++)
            percentages[i+1] += percentages[i];

        // Los porcentajes deben sumar 100
        // JVM deshabilita los assert y no hay manera de habilitarlos en greenfoot
        // Entonces en lugar de detener el programa simplemente no dibujará ningún fondo
        // Si no está dibujando el fondo deberías checar cuánto suman los porcentajes
        // assert (percentages[percentages.length -1] == 100);
        if (percentages[percentages.length -1] != 100)
            return;

        generateBackground();
        prepare();
    }

    /**
     * Genera un fondo aleatorio
     */
    private void generateBackground() {
        // Llenar el mapa
        for (int y = 0; y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                int imageType = getRandomType();
                int imageIndex = random.nextInt(tileAmounts[imageType]);

                mapInfo[x][y] = new Tile(tileTypes[imageType],
                    "MapTiles/" + tilePaths[imageType] + "-" + imageIndex + ".png");
            }
        }
    }

    public void act() {
        int x = this.player.getPosX();
        int y = this.player.getPosX();

        // Dibuja el mapa a partir de x y
        for (int i = 0; i < getHeight() / TILE_SIZE; i++, x++) {
            for (int j = 0; j < getWidth() / TILE_SIZE; j++, y++) {
                addObject(mapInfo[x / TILE_SIZE][y / TILE_SIZE], i*TILE_SIZE, j*TILE_SIZE);
            }
        }
    }

    /**
     * Regresa un tipo de piso de acuerdo a los porcentajes de probabilidad dados
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
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}
