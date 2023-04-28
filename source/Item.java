import java.util.Random;

import greenfoot.Actor;

public class Item extends Actor {
    private Effect effect;
    private int effectDuration;

    public Item(Effect effect, int effectDuration) {
        this.effect = effect;
        this.effectDuration = effectDuration;
        
        String [] colors = {"green", "purple", "yellow"};

        Random random = new Random();
        String chosenColor = colors[random.nextInt(colors.length)];
        setImage("Items/potion-" + chosenColor + ".png");
    }

    public Effect getEffect() {
        return effect;
    }

    public int getEffectDuration() {
        return effectDuration;
    }
}
