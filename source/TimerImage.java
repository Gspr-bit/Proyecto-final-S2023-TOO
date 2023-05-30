import greenfoot.*;

/**
 * Muestra el tiempo en la pantalla.
 *
 * @author Mau
 */
public class TimerImage extends Actor {
    /**
     * Act - do whatever the TimerImage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public TimerImage() {}

    public void act() {
        // Add your action code here.
        showEffectTime();
    }

    public void showEffectTime() {
        String s = MyWorld.player.getEffect() + " " + (MyWorld.player.getEffectEnd() - Timer.getTime());
        if (MyWorld.player.getEffectEnd() - Timer.getTime() == 0) {
            s = "";
        }
        if (MyWorld.player.getEffect() != Effect.NONE) {
            getWorld().showText(s, MyWorld.WORLD_WIDTH / 2, 60);
        }
        getWorld().showText("Tiempo: " + Timer.getTime(), MyWorld.WORLD_WIDTH / 2, 30);
    }

}
