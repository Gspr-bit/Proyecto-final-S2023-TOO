import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shadow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shadow extends Actor
{
    public Shadow(int size) throws InvalidShadowSizeExceptions {
        setSize(size);
    }

    public void setSize(int size) throws InvalidShadowSizeExceptions {
        if (size < 0 || size > 14 || size % 2 != 0) {
            throw new InvalidShadowSizeExceptions("El tamaño de la sombra sólo puede ser un número par entre 0 y 14");
        }

        if (size > 0) {
            setImage("Shadows/shadow-" + size + ".png");
        }
    }


}

class InvalidShadowSizeExceptions extends Exception {
    public InvalidShadowSizeExceptions(String message) {
        super(message);
    }
}
