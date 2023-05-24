import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Car here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Car extends Character
{
    //char carType;
    
    /**
     * Act - do whatever the Car wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move();
    }
    
    /**
     * Constructor de la clase actor
     * @Montse
     */
    public Car(int x, int y){
        this.v = 3;
        this.posX=x;
        this.posY=y;
        this.direction = Direction.LEFT;
        
        //String [] carType = {"a", "b", "c", "d", "e"};
        String [] carType = {"a", "b", "c"};

        Random random = new Random();
        String chosenCarType = carType[random.nextInt(carType.length)];
        setImage("Car/car-" + chosenCarType + ".png");
    }
    
    /**
    *Método para que los carros corran siempre de derecha izquierda
    *@Montse
    */
    public void move(){
        switch (this.direction) {
            case UP: {
                this.posY -= v;
                break;
            }
            case DOWN: {
                this.posY += v;
                break;
            }
            case LEFT: {
                this.posX -= v;
                break;
            }
            case RIGHT: {
                this.posX += v;
                break;
            }
        }
    }
   
   
    public void changeDirection(){
        return;
    }
    public void updateImage(){
        return;
    }
    public boolean puedoIrAqui(int y){
        return ((y>4 && y<Map.TILE_SIZE*8) ||(y>(Map.TILE_SIZE*16) && y<Map.TILE_SIZE*20) ||(y<(-4) && y>-(Map.TILE_SIZE*8)) ||(y<(-Map.TILE_SIZE*16) && y>(-Map.TILE_SIZE*20)));
    }
}
