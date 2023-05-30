import greenfoot.Greenfoot;

/**
 * Función que cambia de nivel.
 * 
 * @author Gaspar
 */
public class WindowSwitcher
{
    public static void nextLevel(int currentLevel) {
        switch (currentLevel) {
            case 1:
            {
                showLevel(2);
                break;
            }
            case 2:
            {
                showLevel(3);
                break;
            }
            case 3:
            {
                win();
            }
        }
    }

    public static void showLevel(int level) {
        switch (level) {
            case 1:
            {
                Level1Intro intro = new Level1Intro();
                Greenfoot.setWorld(intro);
                break;
            }
            case 2:
            {
                Level2Intro intro = new Level2Intro();
                Greenfoot.setWorld(intro);
                break;
            }
            case 3:
            {
                Level3Intro intro = new Level3Intro();
                Greenfoot.setWorld(intro);
                break;
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
