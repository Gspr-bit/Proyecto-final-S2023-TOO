import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TimerImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TimerImage extends Actor
{
    /**
     * Act - do whatever the TimerImage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public TimerImage(){
            GreenfootImage imagen = new GreenfootImage("SLOW.png");
            setImage(imagen);
    }
    public void act()
    {
        // Add your action code here.
        mostrarTiempo();
    }
     public void mostrarTiempo() {
   // GreenfootImage imagen = new GreenfootImage("Tiempo: " + Timer.getTime() + " s", 20, Color.WHITE, null);
   // GreenfootImage imagen = new GreenfootImage("SLOW.png");
    //setImage(imagen);
    String s = "SLOW" + Timer.getTime();
        getWorld().showText(s,60,30);
}

}
