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
public class FixedObject extends Actor
{
    
    int posX;
    int posY;
    //CONSTRUCTOR DEL ACT0R
    public FixedObject(int x, int y){//Comento aquí pq ahorita yo solo lo estube probando así tal cual poniendo items desde el panel de greenfoot, pero pues una idea sería pasarle la posicion desde el generador del mapa y así tal cual para que funcione automaticamente,pero pues por ahora namas quedó así jeje
        this.posX=x;
        this.posY=y;
        setLocation(posX, posY);
    }

    public void act() {
        applyEffect();
    }

    /**
     * Función que hace que los items desaparezcan cuando el jugador los toca
     * y le aplica el efecto al jugador.
     *
     * @author Montse
     */
    public void applyEffect() {
        if (isTouching(Player.class)) {
            // TODO
            // Llamar al método que va aplicar al efecto

            // Hacer que el item desaparezca al chocar con el jugador
            ((Background1) getWorld()).getFixedObjects().remove(this);
            getWorld().removeObject(this);
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
