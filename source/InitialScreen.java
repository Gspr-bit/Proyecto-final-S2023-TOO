import greenfoot.*;

/**
 * Ventana que se muestra al iniciar el juego.
 * 
 * @author Mau
 */
public class InitialScreen extends World
{

    /**
     * Constructor for objects of class InitialScreen.
     * 
     */
    public InitialScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600,400,1);
        prepare();
    }

    /**
     * El jugador debe presionar 'enter' para entrar al juego.
     */
    public void act(){
        if(Greenfoot.isKeyDown("ENTER")){
            InstructionsScreen1 world = new InstructionsScreen1();
            // Esto es necesario porque Greenfoot no soporta
            // keyRelease() como cualquier framework decente haría.
            Greenfoot.delay(10);
            Greenfoot.setWorld(world);
        }

    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        setBackground("Screens/initial-screen.png");
    }
}
