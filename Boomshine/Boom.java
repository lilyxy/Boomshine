
import java.awt.Color;

class Boom{
    private Color colour;
    private int x, y, radius, delay;
    private boolean grow, gone;
    private int type; 
    private int max; 
    
  
    public Boom(){
        
        //constructor for a random Boom object
        int rx=(int)(Math.random()*640);
        int ry=(int)(Math.random()*480);
        int red=(int)(Math.random()*256);
        int green=(int)(Math.random()*256);
        int blue=(int)(Math.random()*256);
        
        Color random=new Color(red, green, blue);
        
        //set some default parameters
        type = 0; 
        x=rx;
        y=ry;
        colour=random;
        radius=5;
        grow=true;
        max = 50;
        
    }
    
    public Boom(int x, int y, int m, Color colour){
        this.x=x;
        this.y=y;
        this.colour=colour;
        radius=5;
	max = m;
        grow=true;
	type = 0;  
    }
    
    public void update(){
        //if it's growing, add to radius
        //once it gets to max size, stop growing
        //delay when at max
        if (grow){
            radius+=1;
            if (radius==max)
                grow=false;
                delay=max;
        }
        else if (delay>0){
            delay-=1;
           
        }
        else{
            radius-=2;
        }
    } 
    //accessor methods
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getRadius(){
        return radius;
    }
    public Color getColour(){
        return colour;
    }
}