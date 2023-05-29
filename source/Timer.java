import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer extends Actor 
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
