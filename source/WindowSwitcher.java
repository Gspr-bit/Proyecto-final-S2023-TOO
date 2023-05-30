import greenfoot.Greenfoot;

/**
 * Función que cambia de nivel.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WindowSwitcher
{
    public static void nextLevel(int currentLevel) {
        switch (currentLevel) {
            case 1:
            {
                Level2Intro intro = new Level2Intro();
                Greenfoot.setWorld(intro);
                break;
            }
            case 2:
            {
                Level3Intro intro = new Level3Intro();
                Greenfoot.setWorld(intro);
                break;
            }
            case 3:
            {
                lose();
            }
        }
    }

    /**
     * Muestra la ventana de victoria.
     */
    public static void win() {
        WinScreen winScreen = new WinScreen();
        Greenfoot.setWorld(winScreen);
    }

    /**
     * Muestra la ventana de derrota.
     */
    public static void lose() {
        GameOverScreen gameOver = new GameOverScreen();
        Greenfoot.setWorld(gameOver);
    }


}
