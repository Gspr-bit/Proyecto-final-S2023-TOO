import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShadowCar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShadowCar extends Actor
{
    int v;
    int posX;
    int posY;
    Direction direction;
    public ShadowCar(int x, int y){
        this.v = 4;
        this.posX=x;
        this.posY=y;
        this.direction = Direction.LEFT;
        //setImage("Car/car-a.png");
    }
    
    
    /**
     * Act - do whatever the ShadowCar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    public boolean puedeCarIniciarAqui() {
        Direction direction=Direction.LEFT;
        int dx = this.getImage().getWidth() / 2 + v;
        int dy = this.getImage().getHeight() / 2 + v;
        
        int dx1=dx-Map.TILE_SIZE*1;
        int dx2=dx-Map.TILE_SIZE*2;
        int dx3=dx-Map.TILE_SIZE*3;
        int dx4=dx-Map.TILE_SIZE*4;

        // UP, DOWN, LEFT, RIGHT
        int[] dxs = {0, 0, -dx, dx};
        int[] dys = {-dy, dy, 0, 0}; 
        
        int[] dxs1 = {0, 0, -dx, dx1};
        int[] dxs2 = {0, 0, -dx, dx2};
        int[] dxs3 = {0, 0, -dx, dx3};
        int[] dxs4 = {0, 0, -dx, dx4};

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

        return nextTile != null && !nextTile.isCollidable() && nextTile1!=null && !nextTile1.isCollidable() && nextTile2!=null && !nextTile2.isCollidable() && nextTile3!=null && !nextTile3.isCollidable() && nextTile4!=null && !nextTile4.isCollidable();
    }
    
}
