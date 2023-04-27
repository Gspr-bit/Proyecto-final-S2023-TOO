import greenfoot.Actor;

public class Item extends Actor {
    private Effect effect;
    private int effectDuration;

    public Item(Effect effect, int effectDuration) {
        this.effect = effect;
        this.effectDuration = effectDuration;
    }

    public Effect getEffect() {
        return effect;
    }

    public int getEffectDuration() {
        return effectDuration;
    }
}
