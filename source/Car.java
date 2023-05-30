import java.util.*;

/**
 * Write a description of class Car here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Car extends Character {
    public int xOriginal;

    /**
     * Constructor de la clase actor
     *
     * @author Montse
     */
    public Car(int x, int y) {
        this.v = 4;
        this.posX = x;
        this.posY = y;
        this.direction = Direction.LEFT;

        prepare();
    }

    /**
     * Hace el resto de cosas necesarias para inicializar la clase.
     */
    private void prepare() {
        String[] carType = {"a", "b", "c"};
        Random random = new Random();
        String chosenCarType = carType[random.nextInt(carType.length)];
        setImage("Cars/car-" + chosenCarType + ".png");
    }

    /**
     * Act - do whatever the Car wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        move();
    }

    /**
     * El carro no necesita cambiar su dirección
     */
    public void changeDirection() {
    }

    /**
     * @return dice si el carro puede iniciar ahí o no
     * @author MONTSE
     */
    public boolean canStartHere() {
        Direction direction = Direction.LEFT;
        int dx = this.getImage().getWidth() / 2 + v;
        int dy = this.getImage().getHeight() / 2 + v;

        int dx1 = dx + Map.TILE_SIZE * 1;
        int dx2 = dx + Map.TILE_SIZE * 2;
        int dx3 = dx + Map.TILE_SIZE * 3;
        int dx4 = dx + Map.TILE_SIZE * 4;
        int dx5 = dx + Map.TILE_SIZE * 5;
        int dx6 = dx + Map.TILE_SIZE * 6;
        int dx7 = dx + Map.TILE_SIZE * 7;
        //int dx8=dx-Map.TILE_SIZE*8;

        int dya = dy + Map.TILE_SIZE * 1;

        // UP, DOWN, LEFT, RIGHT
        int[] dxs = {0, 0, -dx, dx};
        int[] dys = {-dy, dy, 0, 0};

        int[] dxs1 = {0, 0, -dx, dx1};
        int[] dxs2 = {0, 0, -dx, dx2};
        int[] dxs3 = {0, 0, -dx, dx3};
        int[] dxs4 = {0, 0, -dx, dx4};
        int[] dxs5 = {0, 0, -dx, dx5};
        int[] dxs6 = {0, 0, -dx, dx6};
        int[] dxs7 = {0, 0, -dx, dx7};

        int[] dysa = {-dya, dya, 0, 0};

        Tile nextTile = (Tile) this.getOneObjectAtOffset(dxs[direction.ordinal()],
                dys[direction.ordinal()], Tile.class);
        Tile nextTile1 = (Tile) this.getOneObjectAtOffset(dxs1[direction.ordinal()],
                dys[direction.ordinal()], Tile.class);
        Tile nextTile2 = (Tile) this.getOneObjectAtOffset(dxs2[direction.ordinal()],
                dys[direction.ordinal()], Tile.class);
        Tile nextTile3 = (Tile) this.getOneObjectAtOffset(dxs3[direction.ordinal()],
                dys[direction.ordinal()], Tile.class);
        Tile nextTile4 = (Tile) this.getOneObjectAtOffset(dxs4[direction.ordinal()],
                dys[direction.ordinal()], Tile.class);
        Tile nextTile5 = (Tile) this.getOneObjectAtOffset(dxs5[direction.ordinal()],
                dys[direction.ordinal()], Tile.class);
        Tile nextTile6 = (Tile) this.getOneObjectAtOffset(dxs6[direction.ordinal()],
                dys[direction.ordinal()], Tile.class);
        Tile nextTile7 = (Tile) this.getOneObjectAtOffset(dxs7[direction.ordinal()],
                dys[direction.ordinal()], Tile.class);
        Tile nextTilea = (Tile) this.getOneObjectAtOffset(dxs[direction.ordinal()],
                dysa[direction.ordinal()], Tile.class);
        Tile nextTile1a = (Tile) this.getOneObjectAtOffset(dxs1[direction.ordinal()],
                dysa[direction.ordinal()], Tile.class);
        Tile nextTile2a = (Tile) this.getOneObjectAtOffset(dxs2[direction.ordinal()],
                dysa[direction.ordinal()], Tile.class);
        Tile nextTile3a = (Tile) this.getOneObjectAtOffset(dxs3[direction.ordinal()],
                dysa[direction.ordinal()], Tile.class);
        Tile nextTile4a = (Tile) this.getOneObjectAtOffset(dxs4[direction.ordinal()],
                dysa[direction.ordinal()], Tile.class);
        Tile nextTile5a = (Tile) this.getOneObjectAtOffset(dxs5[direction.ordinal()],
                dysa[direction.ordinal()], Tile.class);
        Tile nextTile6a = (Tile) this.getOneObjectAtOffset(dxs6[direction.ordinal()],
                dysa[direction.ordinal()], Tile.class);
        Tile nextTile7a = (Tile) this.getOneObjectAtOffset(dxs7[direction.ordinal()],
                dysa[direction.ordinal()], Tile.class);

        if (nextTile == null || nextTile1 == null || nextTile2 == null) {
            //posX=xOriginal;
            posX = posX + Map.TILE_SIZE * 37;
        }

        //return nextTile != null && !nextTile.isCollidable() && nextTile1!=null && !nextTile1.isCollidable() && nextTile2!=null && !nextTile2.isCollidable() && nextTile3!=null && !nextTile3.isCollidable() && nextTile4!=null && !nextTile4.isCollidable();
        return nextTile != null && !nextTile.isCollidable() && !nextTile1.isCollidable() && !nextTile2.isCollidable() && !nextTile3.isCollidable() && !nextTile4.isCollidable() && !nextTile5.isCollidable() && !nextTile6.isCollidable() && !nextTile7.isCollidable() && nextTilea != null && !nextTilea.isCollidable() && !nextTile1a.isCollidable() && !nextTile2a.isCollidable() && !nextTile3a.isCollidable() && !nextTile4a.isCollidable() && !nextTile5a.isCollidable() && !nextTile6a.isCollidable() && !nextTile7a.isCollidable();
    }
}
