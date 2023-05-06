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
    private static final int MAP_WIDTH = 40;
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

    /**
     * Constructor for objects of class Background1.
     */
    public Background1() {
        // Create a new world with 640x480 cells with a cell size of 1x1 pixels.
        super(40*TILE_SIZE, 30*TILE_SIZE, 1);

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
        // Hacer que el jugador se muestre sobre el piso
        setPaintOrder(Player.class, Tile.class);

        generateBackground();
    //    drawMap();
    }

    public void act() {
        drawMap();
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

    private void drawMap() {
        List<Tile> tiles = getObjects(Tile.class);
        removeObjects(tiles);

        int startTileX = player.getPosX() / TILE_SIZE;
        int startTileY = player.getPosY() / TILE_SIZE;
        int endTileX = startTileX + getWidth() / TILE_SIZE;
        int endTileY = startTileY + getHeight() / TILE_SIZE;
        int offSetX = player.getPosX() % TILE_SIZE;
        int offSetY = player.getPosY() % TILE_SIZE;

        for (int x = startTileX; x < endTileX; x++) {
            for (int y = startTileY; y < endTileY; y++) {
                if ( x < 0 || x >= MAP_WIDTH || y < 0 || y >= MAP_HEIGHT)
                    continue;

                addObject(mapTiles[x][y], offSetX + x * TILE_SIZE, offSetY + y * TILE_SIZE);
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
