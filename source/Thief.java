import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.Random;


/**
 * Write a description of class Thief here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Thief extends Character
{
    char tipoLadron;
    int delayActual;
    int delayMAX;
    int iteradorEnVezDeWhile;
    int iteradorEnVezDeWhileMAX;
    /**
    *Constructir clase Thief
    *@author Montse
    */
    public Thief(int x, int y){
        this.delayActual=0;
        this.delayMAX=10;
        this.iteradorEnVezDeWhile=0;
        this.iteradorEnVezDeWhileMAX=5;
        
        this.v = 2;
        this.direction = Direction.RIGHT;
        this.posX=x;
        this.posY=y;
        setLocation(posX, posY);
        
        setImage("Thief/thieff.png");

        Direction[] d = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
        String[] f = {"up", "down", "left", "right"};

        this.images = new HashMap<>(4);
        
        //Para q haya varios tipos de ladrones, ahorita puse esos 2 unicamente como ejemplo
        String [] tipo = {"a", "b"};
        
        Random random = new Random();
        if(random.nextInt(2)==0){
            this.tipoLadron='a';
        }else{
            this.tipoLadron='b';
        }
        
        //Establecer las imágenes
        for (int i = 0; i < 4; i++) {
            images.put(d[i], new ArrayList<>(2));
            
            String chosenTipo = tipo[random.nextInt(tipo.length)];
            for (int j = 0; j < 2; j++) {
                
                //String chosenTipo = tipo[random.nextInt(tipo.length)];
                
                images.get(d[i]).add("Thief/"+ chosenTipo +"-"+ f[i] + "-" + j + ".png");
                
                
            }
        }

        this.imageTimer = 0;
        
    }
    
    /**
     * Act - do whatever the Thief wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        updateImage();
        moverActor(); //está comentada esta linea pq ese metodo tiene un FIXME
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
        /*setImage("THIEFF/"+ getTipoLadron() +"-"+ this.direction + "-" + 0 + ".png");
        setImage("THIEFF/"+ getTipoLadron() +"-"+ this.direction + "-" + 1 + ".png");
        */
        
    }
    
    /**
     * Método para saber si el jugador puede moverse hacia la posición dada.
     * @author Mauricio, Gaspar
     * @param direction Dirección hacia donde se quiere mover el jugador.
     * @return true si el jugador se puede mover hacia allá
     */
    private boolean canMoveTowards(Direction direction) {
        int dx = this.getImage().getWidth() / 2 + v;
        int dy = this.getImage().getHeight() / 2 + v;

        // UP, DOWN, LEFT, RIGHT
        int[] dxs = {0, 0, -dx, dx};
        int[] dys = {-dy, dy, 0, 0};

        Tile nextTile = (Tile) this.getOneObjectAtOffset(dxs[direction.ordinal()],
                dys[direction.ordinal()], Tile.class);

        return nextTile != null && !nextTile.isCollidable();
    }
    
    /**
     * Método para cambiar la dirección y posición del ratero.
     * @author Montse
     */
    public void changeDirectionAndMove(int direccion) {
        World mundo=getWorld();
        Background1 mundoActual= (Background1)mundo;

        int objectPosX = this.getPosX() * Map.TILE_SIZE - mundoActual.player.getPosX();
        int objectPosY = this.getPosY() * Map.TILE_SIZE - mundoActual.player.getPosY();

        int vPorTileSize=v*Map.TILE_SIZE/4;
        // Este método mueve al ratero, y cambia su sprite para que apunte a la dirección correspondiente

        if (direccion==0) {
            if (canMoveTowards(Direction.RIGHT)) {
            //while(canMoveTowards(Direction.RIGHT)){
                if(this.delayActual==this.delayMAX){
                    this.direction = Direction.RIGHT;
                    this.posX += v;  
                    this.delayActual=0;
                }else{
                    this.delayActual++;
                }

                
            }
            setLocation(objectPosX+vPorTileSize,objectPosY);
        }
        if (direccion==1) {
            if (canMoveTowards(Direction.LEFT)) {
            //while (canMoveTowards(Direction.LEFT)) {
                if(this.delayActual==this.delayMAX){
                    this.direction = Direction.LEFT;
                    this.posX -= v;
                    this.delayActual=0;
                }else{
                    this.delayActual++;
                }
            }
            setLocation(objectPosX-vPorTileSize,objectPosY);
        }
        if (direccion==2) {
            if (canMoveTowards(Direction.UP)) {
            //while(canMoveTowards(Direction.UP)) {
                if(this.delayActual==this.delayMAX){
                    this.direction = Direction.UP;
                    this.posY -= v;
                    this.delayActual=0;
                }else{
                    this.delayActual++;
                }
            }
            setLocation(objectPosX,objectPosY-vPorTileSize);
        }
        if (direccion==3) {
            //if (canMoveTowards(Direction.DOWN)) {
            while(canMoveTowards(Direction.DOWN)) {
                if(this.delayActual==this.delayMAX){
                    this.direction = Direction.DOWN;
                    this.posY += v;
                    this.delayActual=0;
                }else{
                    this.delayActual++;
                }
            }
            setLocation(objectPosX,objectPosY+vPorTileSize);

        }
    }
    public void changeDirection(){
        return;
    }
    
    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }
    public char getTipoLadron(){
        return this.tipoLadron;
    }

    
    /*FIXME
     * Se supone q tal cual debe hacer q el ratero se mueva, pero algo no cuadra jajaj
     * @Montse
     */
    public void moverActor(){    
            
            Random random = new Random();
            int numero = random.nextInt(5);
            if(numero == 1){//DERECHA
                //if(this.iteradorEnVezDeWhile==this.iteradorEnVezDeWhileMAX){
                    this.changeDirectionAndMove(0);
                    //this.iteradorEnVezDeWhile=0;
                //}else{
                //    this.iteradorEnVezDeWhile++;
                //}
                
            }else if(numero ==2){//IZQUIERDA
                //if(this.iteradorEnVezDeWhile==this.iteradorEnVezDeWhileMAX){
                    this.changeDirectionAndMove(1);
                //    this.iteradorEnVezDeWhile=0;
                //}else{
                //    this.iteradorEnVezDeWhile++;
                //}
            }else if(numero ==3){//ARRIBA
                //if(this.iteradorEnVezDeWhile==this.iteradorEnVezDeWhileMAX){
                    this.changeDirectionAndMove(2);
                //    this.iteradorEnVezDeWhile=0;
                //}else{
                //  this.iteradorEnVezDeWhile++;
                //}                       
            }else{//ABAJO
                //if(this.iteradorEnVezDeWhile==this.iteradorEnVezDeWhileMAX){
                    this.changeDirectionAndMove(3);
                //    this.iteradorEnVezDeWhile=0;
                //}else{
                //    this.iteradorEnVezDeWhile++;
                //}
            }
    }
    
    /* FIXME
     * Método para saber si el ratero puede instanciarse ahí, es decir, solo puede instanciarse en el pastito, no en los otros lados (q x cierto, tmb podríamos usar esto en los items pa q se instancien solo en lugares donde el player pueda ir)
     * @Montse
     * 
     * public boolean puedoIrAqui(){
        Tile t = this.getOneIntersectingObject(Tile.class);//Lo q falla en esta funcion es esta linea, no se pq no me deja usar esta funcion
        if(t.isCollidable() == false){
            return true;
        }else{
            return false;
        }
    }*/
}

