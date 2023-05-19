import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Clase Jugador. Esta clase es instanciada desde el mundo.
 *
 * @author Mauricio, Montse, Gaspar
 * @version (a version number or a date)
 */

public class Player extends Character {
    // Tipo de efecto
    private Effect effect;
    // Duración del efecto
    private int effectDuration;
    // Visibilidad del jugador. El jugador podrá ver en el radio especificado
    private int visibility;
    /*// Posición del jugador en el mapa
    private int posX;
    private int posY;*/

    public Player() {
        this.effect = Effect.NONE;
        this.effectDuration = 0;
        this.visibility = 600;
        this.v = 2;
        this.direction = Direction.RIGHT;
        this.posX = this.posY = 0;

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

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    /**
     * Método para cambiar la dirección y posición del jugador.
     * @author Mauricio, Montse
     */
    public void changeDirection() {
        // Este método no mueve al jugador, solo cambia su sprite para que apunte a la dirección correspondiente
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) {
            if (canMoveTowards(Direction.RIGHT)) {
                this.posX += v;
            }
            this.direction = Direction.RIGHT;
        }

        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) {
            if (canMoveTowards(Direction.LEFT)) {
                this.posX -= v;
            }
            this.direction = Direction.LEFT;
        }
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W")) {
            if (canMoveTowards(Direction.UP)) {
                this.posY -= v;
            }
            this.direction = Direction.UP;
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S")) {
            if (canMoveTowards(Direction.DOWN)) {
                this.posY += v;
            }
            this.direction = Direction.DOWN;
        }
    }

    /**
     * Método para saber si el jugador puede moverse hacia la posición dada.
     * @author Mauricio, Gaspar
     * @param direction Dirección hacia donde se quiere mover el jugador.
     * @return true si el jugador se puede mover hacia allá
     */
    private boolean canMoveTowards(Direction direction) {
        boolean sePuedeMover=false;//lo agrego esto pa checar q no choque con los tiefs
        
        int dx = this.getImage().getWidth() / 2 + v;
        int dy = this.getImage().getHeight() / 2 + v;

        // UP, DOWN, LEFT, RIGHT
        int[] dxs = {0, 0, -dx, dx};
        int[] dys = {-dy, dy, 0, 0};

        Tile nextTile = (Tile) this.getOneObjectAtOffset(dxs[direction.ordinal()],
                dys[direction.ordinal()], Tile.class);
                        
        //return nextTile != null && !nextTile.isCollidable();
        sePuedeMover=(nextTile != null && !nextTile.isCollidable());
        
        //Checar q no choque con los thiefs
        Thief p=null;
        int x=0,y=0;
        if(direction ==Direction.UP){
            y=-4;
        }
        if(direction ==Direction.DOWN){
            y=4;
        }
        if(direction ==Direction.RIGHT){
            x=5;
        }
        if(direction ==Direction.LEFT){
            x=-5;
        }
        p=(Thief)this.getOneObjectAtOffset(x,y,Thief.class);
        if(p!=null){
            sePuedeMover=false;
        }
        
        return sePuedeMover;
    }

    /**
     * Cambiar la imagen del jugador para que parezca como si se estuviera moviendo
     * TODO Agregar las animaciones para cuando el jugador está quiero
     * @author Gaspar
     */
    public void updateImage() {
        setImage(images.get(this.direction).get(imageTimer / UPDATE_RATE));

        this.imageTimer++;
        if (this.imageTimer / UPDATE_RATE >= images.get(this.direction).size())
            this.imageTimer = 0;
    }
}
