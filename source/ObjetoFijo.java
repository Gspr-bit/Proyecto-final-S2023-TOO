import java.util.Date;
import java.util.List;
import java.util.Random;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ObjetoFijo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ObjetoFijo extends Actor
{
    
    int posX;
    int posY;
    //CONSTRUCTOR DEL ACT0R
    public ObjetoFijo(/*int x, int y*/){//Comento aquí pq ahorita yo solo lo estube probando así tal cual poniendo items desde el panel de greenfoot, pero pues una idea sería pasarle la posicion desde el generador del mapa y así tal cual para que funcione automaticamente,pero pues por ahora namas quedó así jeje
        /*this.posX=x;
        this.posY=y;
        setLocation(posX, posY);*/
    
    }
    
    
    // Dimensiones de cada Objeto en pixeles
    private static final int OBJECT_SIZE = 16;
    
    // Dimensiones del mapa en Tiles
    private static final int MAP_WIDTH = 160;
    private static final int MAP_HEIGHT = 30;
    
    //cuanto voy a avanzar
    protected int pixelTam=2;
    
    public void act()
    {
        acomodaObjeto();
    }
        
    public void acomodaObjeto(){
    
        // Reacomodo el item en su posicion para que siga al mapa(que son las direcciones contrarias a las que se mueve el mono)
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) {
            super.setLocation(getX()-pixelTam, getY());  
        }
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) {
            super.setLocation(getX()+pixelTam, getY());  
        }
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W")) {
            super.setLocation(getX(), getY()+pixelTam);  
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S")) {
            super.setLocation(getX(), getY()-pixelTam); 
        }
        
    }
}

