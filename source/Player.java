import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
//jaja ese wey
public class Player extends Character {
    // Tipo de efecto
    private Effect effect;
    // Duración del efecto
    private int effectDuration;
    // Visibilidad del jugador. El jugador podrá ver en el radio especificado
    private int visibility;
    // Posición del jugador en el mapa
    private int posX;
    private int posY;

    public Player() {
        this.effect = Effect.NONE;
        this.effectDuration = 0;
        this.visibility = 600;
        this.v = 2;
        this.direction = Direction.RIGHT;
        this.posX = this.posY = 0;

        Direction [] d = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
        String [] f = {"up", "down", "left", "right"};

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

    public void changeDirection() {
        // Este método no mueve al jugador, solo cambia su sprite para que apunte a la dirección correspondiente
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) {
            if(this.getOneObjectAtOffset(this.getImage().getWidth()/2 + v,0,Tile.class) != null &&
            ((Tile)this.getOneObjectAtOffset(this.getImage().getWidth()/2 + v,0,Tile.class)).chocable == false){
                this.direction = Direction.RIGHT;
            this.posX += v;
            
            }
        }
        
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) {
            if(this.getOneObjectAtOffset(-this.getImage().getWidth()/2 +v,0,Tile.class) != null &&
            ((Tile)this.getOneObjectAtOffset(-this.getImage().getWidth()/2 +v,0,Tile.class)).chocable == false){
                this.direction = Direction.LEFT;
            this.posX -= v;
            }
        }
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W")) {
            if(this.getOneObjectAtOffset(0,-this.getImage().getHeight()/2 -v ,Tile.class) != null &&
            ((Tile)this.getOneObjectAtOffset(0,-this.getImage().getHeight()/2 -v ,Tile.class)).chocable == false){
                           this.direction = Direction.UP;
            this.posY -= v; 
            }
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S")) {
            if(this.getOneObjectAtOffset(0,this.getImage().getHeight()/2 +v,Tile.class) != null &&
            ((Tile)this.getOneObjectAtOffset(0,this.getImage().getHeight()/2 +v,Tile.class)).chocable == false){
                this.direction = Direction.DOWN;
            this.posY += v;
            }
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
                this.v = 1;
            }

            // Eliminar el item
            removeTouching(Item.class);
            int i=1;
            int front = getImage().getWidth()/2;
            while(i<=v){
                List<Actor> a = getObjectsAtOffset(front+i,0,Actor.class);
                Object[] toArray= a.toArray();  
                if(a.size() >0){
                    for(int j=0; j<a.size() && a.get(j) instanceof Tile;j++){
                        if(((Tile)toArray[j]).getType() != Tile.TileType.TREE ){
                            changeDirection();
                        }
                    }
                }
            }
        }
    }

    public void updateImage() {
        setImage(images.get(this.direction).get(imageTimer / UPDATE_RATE));

        this.imageTimer++;
        if (this.imageTimer / UPDATE_RATE >= images.get(this.direction).size())
            this.imageTimer = 0;
    }
    
    
}
