import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Objects;

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends Character {
    // Tipo de efecto
    private Effect effect;
    // Duración del efecto
    private int effectDuration;
    // Visibilidad del jugador. El jugador podrá ver en el radio especificado
    private int visibility;

    public Player() {
        this.effect = Effect.NONE;
        this.effectDuration = 0;
        this.visibility = 600;
        this.v = 2;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public int getEffectDuration() {
        return effectDuration;
    }

    public void setEffectDuration(int effectDuration) {
        this.effectDuration = effectDuration;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public void changeDirection() {
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) {
            move(v);
        }
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) {
            move(-v);
        }
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W")) {
            setLocation(this.getX(), this.getY() - v);
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S")) {
            setLocation(this.getX(), this.getY() + v);
        }
    }

    public void collide() {
        if (isTouching(Item.class)) {
            Item item = (Item) getOneIntersectingObject(Item.class);
            this.effect = item.getEffect();
            this.effectDuration = item.getEffectDuration();

            /*
             * TODO: Esto deberá ser reemplazado por un switch cuando implementemos
             *  todos los efectos
             */
            if (Objects.requireNonNull(item.getEffect()) == Effect.SLOW) {
                this.v /= 2;
            }

            // Eliminar el item
            removeTouching(Item.class);
        }
    }
}
