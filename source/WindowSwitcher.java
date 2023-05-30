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
                GameOverScreen gameOver = new GameOverScreen();
                Greenfoot.setWorld(gameOver);
            }
        }
    }
}
