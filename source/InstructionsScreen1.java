import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstructionsScreen1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionsScreen1 extends World
{
    /**
     * Constructor for objects of class InstructionsScreen1.
     * 
     */
    public InstructionsScreen1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
    }
    public void act(){
       
        if(Greenfoot.isKeyDown("ENTER")){
     
      //  if(Greenfoot.isKeyDown("ENTER") && Greenfoot.isKeyDown("ENTER"))
       /* { */InstructionsSreen2 world1 = new InstructionsSreen2();
           
        Greenfoot.delay(10); 
         Greenfoot.setWorld(world1);
       // }

     }   
        }
    }

