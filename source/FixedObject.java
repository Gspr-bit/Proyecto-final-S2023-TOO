import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Esta superclase se utiliza para los objetos que deben estar fijos en el mapa.
 *
 * @author Montse
 */
public abstract class FixedObject extends Actor {

    protected int posX;
    protected int posY;

    protected FixedObject(int x, int y) {
        this.posX = x;
        this.posY = y;
        setLocation(posX, posY);
    }

    /**
     * Regresa la posición en Tiles en X del personaje.
     * La posición (x, y) comienza en (0, 0). X se incrementa hacia la derecha.
     * Esta no es la posición en la ventana, sino en el mapa.
     *
     * @return La posición en X del personaje
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Regresa la posición en Tiles en Y del personaje.
     * La posición (x, y) comienza en (0, 0). Y se incrementa hacia abajo.
     * Esta no es la posición en la ventana, sino en el mapa.
     *
     * @return La posición en X del personaje
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Función que hace que los items desaparezcan cuando el jugador los toca.
     * Los objetos están guardados dentro en MyWorld FixedObjects
     * Entonces si queremos eliminar un objeto, no solo lo borramos del mundo,
     * también del ArrayList.
     *
     * @author Montse
     */
    public void remove() {
        ((MyWorld) getWorld()).getFixedObjects().remove(this);
        getWorld().removeObject(this);
    }
}
