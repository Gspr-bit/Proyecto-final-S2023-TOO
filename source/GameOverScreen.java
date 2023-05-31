import greenfoot.*;

/**
 * Ventana que se muestra cuando el jugador pierde.
 *
 * @author (your name)
 * @version Mau
 */
public class GameOverScreen extends World {

    /**
     * Constructor for objects of class GameOverScreen.
     */
    public GameOverScreen() {
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
            Greenfoot.delay(10);
            WindowSwitcher.showLevel(1);
        }
        if (Greenfoot.isKeyDown("N")) {
            System.exit(0);
        }
    }

    /**
     * Hace el resto de cosas necesarias para inicializar la clase.
     */
    private void prepare() {
        setBackground("Screens/game-over-screen.png");
    }
}
