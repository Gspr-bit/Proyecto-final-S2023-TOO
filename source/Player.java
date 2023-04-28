import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.ArrayList;
import java.util.HashMap;
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
        this.images = new HashMap<>();
        this.direction = Direction.RIGHT;

        this.images.put(Direction.UP, new ArrayList<>() {
            {
                add("Player/up-0.png");
                add("Player/up-1.png");
                add("Player/up-2.png");
                add("Player/up-3.png");
                add("Player/up-4.png");
                add("Player/up-5.png");
            }
        });
        this.images.put(Direction.DOWN, new ArrayList<>() {
            {
                add("Player/down-0.png");
                add("Player/down-1.png");
                add("Player/down-2.png");
                add("Player/down-3.png");
                add("Player/down-4.png");
                add("Player/down-5.png");
            }
        });
        this.images.put(Direction.LEFT, new ArrayList<>() {
            {
                add("Player/down-0.png");
                add("Player/down-1.png");
                add("Player/down-2.png");
                add("Player/down-3.png");
                add("Player/down-4.png");
                add("Player/down-5.png");
            }
        });
        this.images.put(Direction.RIGHT, new ArrayList<>() {
            {
                add("Player/down-0.png");
                add("Player/down-1.png");
                add("Player/down-2.png");
                add("Player/down-3.png");
                add("Player/down-4.png");
                add("Player/down-5.png");
            }
        });

        this.imageTimer = 0;
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
            this.direction = Direction.RIGHT;

        }
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) {
            move(-v);
            this.direction = Direction.LEFT;
        }
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W")) {
            setLocation(this.getX(), this.getY() - v);
            this.direction = Direction.UP;
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S")) {
            setLocation(this.getX(), this.getY() + v);
            this.direction = Direction.DOWN;
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

    public void updateImage() {
        setImage(images.get(this.direction).get(imageTimer / UPDATE_RATE));

        this.imageTimer++;
        if (this.imageTimer / UPDATE_RATE >= images.get(this.direction).size())
            this.imageTimer = 0;
    }
}
