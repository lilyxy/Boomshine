
import java.awt.Color;
import java.util.ArrayList;

class Particle{
    static int speed=2;
    protected double angle;
    protected Color colour;
    protected int red,green,blue;
    protected int x,y;
    protected int radius; 
    
	//empty constructor
    public Particle(){
		radius = 5;
		
		//generate random colour for particle
        red=(int)(Math.random()*256);
        green=(int)(Math.random()*256);
        blue=(int)(Math.random()*256);
        colour=new Color(red, green, blue);
		
		//generate random position and angle
        do{
            x=(int)(Math.random()*630)+5;
            y=(int)(Math.random()*470)+5;
        }while(!(((x>=5)&&(x<=620))&&((y>=5)&&(y<=435))));
        
        do{
            angle=Math.random()*Math.PI*2;
        }while (((int)(angle/Math.PI/2*360)%90)<15);
    }
	
	//constructor 
    public Particle(Color tint, int r){
        this();
		radius = r;
        colour=new Color((tint.getRed()*2+red)/3,(tint.getGreen()*2+green)/3, (tint.getBlue()*2+blue)/3);
    }
	
	//collision function
    public void update(){
        x=(int)Math.round((x+speed*Math.cos(angle)));
        y=(int)Math.round((y-speed*Math.sin(angle)));
        if ((x<5)||(x>620)){
            angle=Math.PI-angle;
        }
        if ((y<5)||(y>435)){
            angle*=-1;
        }
        angle+=Math.PI*2;
        angle%=Math.PI*2;
        
    }  
    public static void setSpeed(int speed){
        Particle.speed=speed;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
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
    public int getType(){
        return 1;
    }
}
