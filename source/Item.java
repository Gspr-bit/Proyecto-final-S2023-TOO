import java.util.Random;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Item extends ObjetoFijo {
    private Effect effect;
    private int effectDuration;

    public Item(Effect effect, int effectDuration/*, int x, int y*/) {
        //super(x,y);//constructor de objeto fijo
        
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
    
    /*public void act(){//CON ESTA FUNCION SE ARREGLA EL HECHO DE QUE SI SE LLEGA A LAS ESQUINAS SE QUEDA FLOTANDO, PERO NOSE PQ, CUANDO LA UTILIZO, GREENFOOT SE ATONTA Y NO SIRVE, PRUEBENLO USTDS EN SUS MAQUINAS PQ NO HALLO PQ NO DAR+IA
        //si se sale del mapa, nimodo, se acabo la posion, la perdiste
        if(getX()==-300 || getX()==300 || getY()==-200 || getY()==200){
            this.effectDuration=0;//si eso no funciona cambio la imgn a null pa q desaparesta
            //this.setImage(null);
        }
    }*/
    
            
    
}
