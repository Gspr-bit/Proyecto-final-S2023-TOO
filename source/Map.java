import java.util.List;
import java.util.Random;

import greenfoot.GreenfootImage;

/**
 * Representación del mapa del juego.
 * El mapa es una matriz formada por cuadrados llamados Tiles.
 * Al instanciar esta clase hay que
 *
 * @author Gaspar
 */
public class Map {
    // Dimensiones de cada Tile en pixeles
    public static final int TILE_SIZE = 16;
    // Dimensiones del mapa en Tiles
    public static final int MAP_WIDTH = 164;
    public static final int MAP_HEIGHT = 40;

    public final Tile[][] mapTiles;
    private final Tile.TileType[] tileTypes = Tile.TileType.values();
    // Tipos que puede haber
    private final String[] tilePaths = {"bush", "dirt", "grass", "mud", "rock", "tree", "water", "pavement", "wall", "roof"};
    // Total de imágenes de cada tipo
    private final int[] tileAmounts = {1, 1, 11, 1, 1, 1, 1, 1, 1, 1};
    // Porcentaje total de apariciones de cada tipo. Deben sumar 100
    // Si no suman 100 no dibuja el fondo.
    // Estos datos son utilizados en generateCountryMap()
    private final int[] percentages = {20, 2, 70, 2, 2, 2, 2, 0, 0, 0};

    private final Random random;

    /**
     * Constructor para el mapa
     */
    public Map(long seed, int level) {
        this.random = new Random(seed);
        this.mapTiles = new Tile[MAP_WIDTH][MAP_HEIGHT];

        prepare(level);
    }

    /**
     * Llena el mapa de acuerdo al nivel
     *
     * @param level Nivel del juego.
     */
    private void prepare(int level) {
        if (level == 1) {
            generateCityMap();
        } else if (level == 2 || level == 3) {
            try {
                generateCountryMap();
            } catch (WrongGenerationPercentagesException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("El nivel debe ser de 1 a 3");
        }
    }

    /**
     * Genera un fondo aleatorio para la ciudad
     *
     * @author Gaspar, Mau
     */
    private void generateCityMap() {
        int blockWidth = 12;
        int blockHeight = 8;
        int streetWidth = 4;

        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                int imageType;

                if (x % (streetWidth + blockWidth) < streetWidth || y % (streetWidth + blockHeight) < streetWidth) {
                    imageType = Tile.TileType.PAVEMENT.ordinal();
                } else if (y % (streetWidth + blockHeight) > streetWidth + blockHeight - 2) {
                    imageType = Tile.TileType.WALL.ordinal();
                } else {
                    imageType = Tile.TileType.WALL.ordinal();
                }

                int imageIndex = random.nextInt(tileAmounts[imageType]);
                int i = x % (streetWidth + blockWidth) - streetWidth + 1;
                int j = y % (streetWidth + blockHeight) - streetWidth + 1;
                GreenfootImage image;
                if (i >= 1 && j >= 1) {
                    image = new GreenfootImage("MapTiles/House/house" + "-" + j + "," + i + ".png");
                } else {
                    image = new GreenfootImage("MapTiles/" + tilePaths[imageType] + "-" + imageIndex + ".png");
                }

                mapTiles[x][y] = new Tile(image, Tile.TileType.values()[imageType]);
            }
        }
    }

    /**
     * Genera un fondo aleatorio para el campo
     *
     * @author Gaspar
     */
    private void generateCountryMap() throws WrongGenerationPercentagesException {
        // Calcular la sumatoria de los porcentajes
        for (int i = 0; i < percentages.length - 1; i++)
            percentages[i + 1] += percentages[i];

        // Los porcentajes deben sumar 100
        // JVM deshabilita los assert y no hay manera de habilitarlos en greenfoot !&#*"#
        // Entonces en lugar de detener el programa simplemente no dibujará ningún fondo
        // Si no está dibujando el fondo deberías checar cuánto suman los porcentajes
        // assert (percentages[percentages.length -1] == 100);
        if (percentages[percentages.length - 1] != 100)
            throw new WrongGenerationPercentagesException("Percentages don't add 100. Please check Map.percentages");

        // Llenar el mapa con valores aleatorios
        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                int imageType = getRandomType();
                int imageIndex = random.nextInt(tileAmounts[imageType]);

                GreenfootImage image = new GreenfootImage("MapTiles/" + tilePaths[imageType] + "-" + imageIndex + ".png");

                mapTiles[x][y] = new Tile(image, tileTypes[imageType]);
            }
        }

        // Poner un cuadro de pasto 4 x 4 en el centro donde el jugador va a aparecer
        int startX = MyWorld.WORLD_WIDTH / (TILE_SIZE * 2) - 2;
        int startY = MyWorld.WORLD_HEIGHT / (TILE_SIZE * 2) - 2;
        int endX = MyWorld.WORLD_WIDTH / (TILE_SIZE * 2) + 2;
        int endY = MyWorld.WORLD_HEIGHT / (TILE_SIZE * 2) + 2;
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY + 2; y++) {
                int imageType = Tile.TileType.GRASS.ordinal();
                int imageIndex = random.nextInt(tileAmounts[imageType]);

                GreenfootImage image = new GreenfootImage("MapTiles/" + tilePaths[imageType] + "-" + imageIndex + ".png");

                mapTiles[x][y] = new Tile(image, tileTypes[imageType]);
            }
        }
    }

    /**
     * Dibuja el mapa en el fondo dependiendo de la posición del jugador
     *
     * @author Gaspar
     */
    public void drawMap(MyWorld world) {
        // Quita todos los anteriores porque los va a volver a dibujar

        // Guarda todos los objetos del tipo Tile en una lista
        List<Tile> tiles = world.getObjects(Tile.class);
        // Quita todos los tiles del mapa
        world.removeObjects(tiles);
        // Calcula el índice de la matriz a partir de donde comenzará a dibujar los tiles
        // La posición del jugador está dada en píxeles, entonces debemos dividir entre el tamaño
        // en píxeles de cada Tile para saber la posición de la matriz.

        // Esto es, si por ejemplo el jugador se encuentra en (20, 40)
        // Dividimos entre el tamaño de cada Tile (16) j queda que deberemos comenzar a dibujar
        // a partir del Tile (1, 2)
        int startTileX = world.getPlayer().getPosX() / TILE_SIZE;
        int startTileY = world.getPlayer().getPosY() / TILE_SIZE;

        // Para calcular en qué tile de la matriz vamos a terminar de dibujar
        // simplemente calculamos cuántos tiles caben a lo largo y lo ancho de la ventana
        // obteniendo su tamaño en píxeles y dividiendo entre el tamaño de cada tile.
        int endTileX = startTileX + world.getWidth() / TILE_SIZE;
        int endTileY = startTileY + world.getHeight() / TILE_SIZE;

        // Volviendo a startTileX j startTileY. Podemos mirar que en el ejemplo sobran 4 j 8 píxeles
        // Debemos tomarlos en cuenta porque si no se va mirar como si fuera saltando cada 16 píxeles
        // en lugar de como si se fuera recorriendo
        int offSetX = world.getPlayer().getPosX() % TILE_SIZE;
        int offSetY = world.getPlayer().getPosY() % TILE_SIZE;

        // Variables donde vamos a guardar dónde debe ir cada tile
        // Su posición debe ir guardada en píxeles.
        // Sus posiciones las vamos a ir moviendo junto a 'i', 'j'
        // Comenzamos con TILE_SIZE - 8 porque si comenzamos con 0 se tapa la mitad del tile
        int tilePosX = TILE_SIZE / 2;
        int tilePosY;

        // Iteramos por la matriz desde startTileX hasta endTileX (Renglones)
        for (int x = startTileX; x < endTileX; tilePosX += TILE_SIZE, x++) {
            tilePosY = TILE_SIZE / 2;
            // Iteramos por la matriz desde startTileY hasta endTileY (Columnas)
            for (int y = startTileY; y < endTileY; tilePosY += TILE_SIZE, y++) {
                // Checamos que 'i', 'j' se encuentren dentro de los límites de la matriz
                if (x < 0 || x >= MAP_WIDTH || y < 0 || y >= MAP_HEIGHT)
                    continue;

                // Agregamos al tile en la posición correspondiente
                // Aquí se puede observar cómo le restamos el residuo que calculamos anteriormente
                // (No me preguntes por qué hay que restarlo en lugar de sumarlo)
                world.addObject(mapTiles[x][y], tilePosX - offSetX, tilePosY - offSetY);
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

    /**
     * Esta excepción se lanza cuando los porcentajes de cuántos tiles deberían ser mostrados
     * están mal establecidos j no suman 100.
     */
    static class WrongGenerationPercentagesException extends Exception {
        public WrongGenerationPercentagesException(String message) {
            super(message);
        }
    }
}
