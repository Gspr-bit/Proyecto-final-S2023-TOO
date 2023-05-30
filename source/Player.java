import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Clase Jugador. Esta clase es instanciada desde el mundo.
 *
 * @author Mauricio, Montse, Gaspar
 * @version (a version number or a date)
 */

public class Player extends Character {
    // Tipo de efecto
    private Effect effect;
    // Momento en que se dejará de aplicar el efecto en segundos
    private int effectEnd;
    // Visibilidad del jugador. El jugador podrá ver en el radio especificado
    private int visibility;
    // Aquí guardamos el valor que debe tener `v` cuando no tiene ningún efecto
    private final int defaultV;

    public Player() {
        this.effect = Effect.NONE;
        // Duración del efecto
        this.visibility = 0;
        this.v = this.defaultV = 2;
        this.direction = Direction.RIGHT;
        this.posX = this.posY = 0;

        prepare();
    }

    /**
     * Prepara las imágenes del jugador.
     */
    private void prepare() {
        Direction[] d = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
        String[] f = {"up", "down", "left", "right"};

        this.images = new HashMap<>(4);

        // Establecer las imágenes
        for (int i = 0; i < 4; i++) {
            images.put(d[i], new ArrayList<>(6));

            for (int j = 0; j < 6; j++) {
                images.get(d[i]).add("Player/" + f[i] + "-" + j + ".png");
            }
        }

        this.imageTimer = 0;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
    public int getEffectEnd(){
        return this.effectEnd;
    }

    /**
     * Método para cambiar la dirección y posición del jugador.
     * @author Mauricio, Montse
     */
    public void changeDirection() {
        applyEffect();

        // Este método no mueve al jugador, solo cambia su sprite para que apunte a la dirección correspondiente
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) {
            this.direction = Direction.RIGHT;
            if (this.v < 0) {
                this.direction = Direction.LEFT;
            }
            if (canMoveTowards(this.direction)) {
                this.posX += v;
            }
        }

        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) {
            this.direction = Direction.LEFT;
            if (this.v < 0) {
                this.direction = Direction.RIGHT;
            }
            if (canMoveTowards(this.direction)) {
                this.posX -= v;
            }
        }
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W")) {
            this.direction = Direction.UP;
            if (this.v < 0) {
                this.direction = Direction.DOWN;
            }
            if (canMoveTowards(this.direction)) {
                this.posY -= v;
            }
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S")) {
            this.direction = Direction.DOWN;
            if (this.v < 0) {
                this.direction = Direction.UP;
            }
            if (canMoveTowards(this.direction)) {
                this.posY += v;
            }
        }
    }

    public void applyEffect() {
        Item item = (Item) this.getOneIntersectingObject(Item.class);
        if (item != null) {
            // Momento en que se comenzó a aplicar el efecto en segundos
            int effectStart = Timer.getTime();
            this.effectEnd = effectStart + item.getEffectDuration();
            this.effect = item.getEffect();
            item.remove();
            if (this.effect == Effect.BLIND) {
                try {
                    ((MyWorld) getWorld()).getShadow().setSize((new Random().nextInt(6) * 2) + 2);
                } catch (InvalidShadowSizeExceptions e) {
                    throw new RuntimeException(e);
                }
            }
        } else if (Timer.getTime() < effectEnd) {
            switch (this.effect) {
                case SLOW: {
                    this.v = 1;
                    break;
                }
                case DIZZY: {
                    this.v = -defaultV;
                    break;
                }
                case FREEZE: {
                    this.v = 0;
                    break;
                }
                case FAST: {
                    this.v = 4;
                }
                case BLIND: {
                    // Ya lo hicimos arriba
                    break;
                }
                case NONE: {
                    // No hacer nada
                }
            }
        } else {
            // Regresar las cosas a la normalidad
            this.v = this.defaultV;
            ((MyWorld) getWorld()).getShadow().reset();
            this.effect=Effect.NONE;
        }
    }
    
    public void act(){
        changeDirection();
        updateImage();
    }
}
