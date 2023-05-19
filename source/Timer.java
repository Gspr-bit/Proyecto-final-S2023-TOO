/**
 * Write a description of class Timer here.
 * 
 * @author Gaspar
 */
public class Timer  
{
    private static final int FPS = 60;

    private static int time = 0;

    /**
     * Regresa el tiempo en segundos
     * transcurrido desde que se inició el juego
     * @author Gaspar
     * @return tiempo en segundos
     */
    public static int getTime() {
        return time/FPS;
    }

    /**
     * Actualiza el temporizador
     * Este método debe ser llamado dentro de act() de un solo objeto en el juego
     * @author Gaspar
     */
    public static void update() {
        time ++;
    }
}
