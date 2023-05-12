import java.util.Random;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Item extends FixedObject {
    private Effect effect;
    private int effectDuration;

    public Item(Effect effect, int effectDuration, int x, int y) {
        super(x,y);//constructor de objeto fijo
        
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
