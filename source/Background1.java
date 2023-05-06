import java.util.Date;
import java.util.Random;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Background1 extends World
{
    private static final int TILE_SIZE = 16;
    private final Random random;
    // Tipos que puede haber
    private final String [] tileTypes = {"bush", "dirt", "grass", "mud", "rock", "tree", "water"};
    // Total de imágenes de cada tipo
    private final int [] tileAmounts  = {     1,      1,      11,     1,      1,      1,       1};
    // Porcentaje total de apariciones de cada tipo. Deben sumar 100. Si no suman 100 no dibuja el fondo.
    private final int [] percentages  = {    20,      2,      70,     2,      2,      2,       2};

    private Player player;
    /**
     * Constructor for objects of class Background1.
     * 
     */
    public Background1()
    {    
        // Create a new world with 640x480 cells with a cell size of 1x1 pixels.
        super(640, 480, 1);
        this.random = new Random(new Date().getTime());

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

        // Agregar el jugador
        this.player = new Player();
        player.setLocation(this.getWidth() / 2, this.getHeight() / 2);
        this.addObject(this.player, this.getWidth() / 2, this.getHeight() / 2);
        // Hacer que el jugador se muestre sobre el piso
        setPaintOrder(Player.class, Tile.class);

        generateBackground();
    }

    /**
     * Genera un fondo aleatorio
     */
    private void generateBackground() {
        // Llenar el mapa
        for (int y = 0; y <= getHeight(); y += TILE_SIZE) {
            for (int x = 0; x <= getWidth(); x+= TILE_SIZE) {
                int imageType = getRandomType();
                int imageIndex = random.nextInt(tileAmounts[imageType]);

                GreenfootImage image = new GreenfootImage("MapTiles/" + tileTypes[imageType] + "-" + imageIndex + ".png");

                addObject(new Tile(image), x, y);
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
}
