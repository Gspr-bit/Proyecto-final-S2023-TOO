import java.util.Date;
import java.util.List;
import java.util.Random;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta superclase se utiliza para los objetos que deben estar fijos en el mapa.
 * 
 * @author Montse
 */
public abstract class FixedObject extends Actor
{

    protected int posX;
    protected int posY;
    //CONSTRUCTOR DEL ACT0R
    protected FixedObject(int x, int y){//Comento aquí pq ahorita yo solo lo estuve probando así tal cual poniendo items desde el panel de greenfoot, pero pues una idea sería pasarle la posicion desde el generador del mapa y así tal cual para que funcione automaticamente,pero pues por ahora namas quedó así jeje
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
