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
    /**
    *Constructir clase Thief
    *@author Montse
    */
    public Thief(int x, int y){
        
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
        hacerQuePaseTiempo();
        setImage("THIEFF/"+ getTipoLadron() +"-"+ this.direction + "-" + 1 + ".png");            
        hacerQuePaseTiempo();*/    
        
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
        // Este método mueve al ratero, y cambia su sprite para que apunte a la dirección correspondiente
        if (direccion==0) {
            if (canMoveTowards(Direction.RIGHT)) {
                //this.posX += v;
                this.posX=getPosX()+v;
            }
            this.direction = Direction.RIGHT;
            setLocation(getPosX(),getPosY());
        }

        if (direccion==1) {
            if (canMoveTowards(Direction.LEFT)) {
                //this.posX -= v;
                this.posX=getPosX()-v;
            }
            this.direction = Direction.LEFT;
            setLocation(getPosX(),getPosY());
        }
        if (direccion==2) {
            if (canMoveTowards(Direction.UP)) {
                //this.posY -= v;
                this.posY=getPosY()-v;
            }
            this.direction = Direction.UP;
            setLocation(getPosX(),getPosY());
        }
        if (direccion==3) {
            if (canMoveTowards(Direction.DOWN)) {
                //this.posY += v;
                this.posY=getPosY()-v;
            }
            this.direction = Direction.DOWN;
            setLocation(getPosX(),getPosY());
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
    
    /**
     * No se si el fallo en la funcion mover ratero se deba a q se ejecuta a la misma vez tantas veces  que es muy rapido el movimiento, así q pa probar si es eso, cada q se ejecuta esta funcion debería haver 1 seg de pausa
     */
    public void hacerQuePaseTiempo(){
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdownStarter = 10;
            public void run() {
                countdownStarter--;
                if (countdownStarter < 0) {
                     scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 2000, MILLISECONDS);
            
    }
    
    /*FIXME
     * Se supone q tal cual debe hacer q el ratero se mueva, pero algo no cuadra jajaj
     * @Montse
     */
    public void moverActor(){    
            //hacerQuePaseTiempo();
            
            Random random = new Random();
            int numero = random.nextInt(5);
            if(numero == 1){//DERECHA
                this.changeDirectionAndMove(0);
            }else if(numero ==2){//IZQUIERDA
                this.changeDirectionAndMove(1);
            }else if(numero ==3){//ARRIBA
                this.changeDirectionAndMove(2);                        
            }else{//ABAJO
                this.changeDirectionAndMove(3);
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

