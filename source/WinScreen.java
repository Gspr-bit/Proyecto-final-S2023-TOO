import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ventana que se muestra cuando el jugador gana.
 * 
 * @author Mau
 */
public class WinScreen extends World
{

    /**
     * Constructor for objects of class WinScreen.
     * 
     */
    public WinScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        prepare();
    }

    /**
     * Lee las opciones del jugador.
     * 'Y' para jugar de nuevo.
     * 'N' para salir del juego.
     */
    public void act() {
        if (Greenfoot.isKeyDown("Y")) {
            Level1Intro intro = new Level1Intro();
            Greenfoot.delay(10);
            Greenfoot.setWorld(intro);
        }
        if (Greenfoot.isKeyDown("N")) {
            System.exit(0);
        }
    }

    /**
     * Hace el resto de cosas necesarias para inicializar la clase.
     */
    private void prepare() {
        setBackground("Screens/win-screen.jpeg");
    }
}
