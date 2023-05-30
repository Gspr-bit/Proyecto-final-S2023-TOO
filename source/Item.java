import java.util.Random;

/**
 * Clase que representa una poción.
 *
 * @author Gaspar
 */
public class Item extends FixedObject {
    private final Effect effect;
    private final int effectDuration;

    public Item(Effect effect, int effectDuration, int x, int y) {
        super(x, y);//constructor de objeto fijo

        this.effect = effect;
        this.effectDuration = effectDuration;

        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        String[] colors = {"green", "purple", "yellow"};

        Random random = new Random();
        String chosenColor = colors[random.nextInt(colors.length)];
        setImage("Items/potion-" + chosenColor + ".png");
    }

    /**
     * @return El tipo de efecto.
     */
    public Effect getEffect() {
        return effect;
    }

    /**
     * @return La duración del efecto.
     */
    public int getEffectDuration() {
        return effectDuration;
    }
}
